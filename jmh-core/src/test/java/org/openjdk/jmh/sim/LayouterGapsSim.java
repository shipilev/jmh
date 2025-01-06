package org.openjdk.jmh.sim;

import java.io.PrintStream;
import java.util.*;

public class LayouterGapsSim {

    public static void main(String... args) {
        generate();
    }

    static final int MAX_ALLOC = 16;

    static final Set<State> STATES = new HashSet<>();
    static final Set<Edge> EDGES = new HashSet<>();

    public static void generate() {
        Set<State> prevStates = new HashSet<>();
        prevStates.add(new State(Collections.emptyList(), 0));

        PrintStream out = System.out;
        while (true) {
            Set<State> newStates = new HashSet<>();
            for (State from : prevStates) {
                for (int alloc = 1; alloc <= MAX_ALLOC; alloc *= 2) {
                    State to = from.allocate(alloc);
                    newStates.add(to);
                    EDGES.add(new Edge(from, to, alloc));
                }
            }

            if (!STATES.addAll(newStates)) {
                // No new states appeared. Nothing left to do.
                break;
            }

            prevStates = newStates;
        }

        out.println("digraph {");
        for (Edge s : EDGES) {
            out.println("  \"" + s.from + "\" -> \"" + s.to + "\" [label=\"alloc(" + s.alloc + ")\"];");
        }
        out.println("}");

        for (State s : STATES) {
            if (s.totalGaps() >= MAX_ALLOC) {
                throw new IllegalStateException("Found a state with too many gaps: " + s);
            }
        }
    }

    static class Edge {
        State from;
        State to;
        int alloc;

        public Edge(State from, State to, int alloc) {
            this.from = from;
            this.to = to;
            this.alloc = alloc;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Edge edge = (Edge) o;
            return from.equals(edge.from) && to.equals(edge.to);
        }

        @Override
        public int hashCode() {
            return Objects.hash(from, to);
        }
    }

    static class Gap {
        final int offset; // Offset from the MAX_ALLOC unit
        final int size;

        public Gap(int offset, int size) {
            this.offset = offset;
            this.size = size;
        }

        @Override
        public String toString() {
            return "@" + offset + ":" + size;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Gap gap = (Gap) o;
            return size == gap.size && offset == gap.offset;
        }

        @Override
        public int hashCode() {
            return Objects.hash(size, offset);
        }
    }

    static class State {
        final List<Gap> gaps = new ArrayList<>();
        int lastOffset;

        public State(List<Gap> gs, int offset) {
            gaps.addAll(gs);
            lastOffset = offset % MAX_ALLOC;
        }

        @Override
        public String toString() {
            return gaps + ", @*:" + lastOffset;
        }

        public State allocate(int alloc) {
            // Try to find a fitting gap.
            {
                int idx = -1;
                for (int i = 0; i < gaps.size(); i++) {
                    Gap g = gaps.get(i);
                    if (g.size >= alloc) {
                        idx = i;
                        break;
                    }
                }
                if (idx != -1) {
                    List<Gap> newGaps = new ArrayList<>(gaps);
                    Gap gap = newGaps.remove(idx);

                    if (gap.offset % alloc == 0) {
                        // Gap with good alignment, just take from the beginning
                        int leftover = gap.size - alloc;
                        if (leftover > 0) {
                            newGaps.add(idx, new Gap(gap.offset + alloc, leftover));
                        }
                    } else {
                        // Gap with bad alignment, we need to introduce new gaps.
                        int preGap = alloc - (gap.offset % alloc);
                        int postGap = gap.size - (preGap + alloc);
                        newGaps.add(idx, new Gap(gap.offset, preGap));
                        if (postGap > 0) {
                            newGaps.add(idx + 1, new Gap(gap.offset + preGap + alloc, postGap));
                        }
                    }
                    return new State(newGaps, lastOffset);
                }
            }

            // We have the tail that fits the alignment. Allocate there.
            if ((lastOffset % alloc) == 0) {
                return new State(gaps, lastOffset + alloc);
            }

            // There is no matching gap, and the tail offset is not fitting.
            // This means we need to introduce a gap and line up offset.
            {
                int gapSize = alloc - (lastOffset % alloc);
                List<Gap> newGaps = new ArrayList<>(gaps);
                newGaps.add(new Gap(lastOffset, gapSize));
                return new State(newGaps, lastOffset + gapSize);
            }
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            State state = (State) o;
            return lastOffset == state.lastOffset && Objects.equals(gaps, state.gaps);
        }

        @Override
        public int hashCode() {
            return Objects.hash(gaps, lastOffset);
        }

        public int totalGaps() {
            int s = 0;
            for (Gap g : gaps) {
                s += g.size;
            }
            return s;
        }
    }

}
