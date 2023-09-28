/*
 * Copyright Amazon.com Inc. or its affiliates. All Rights Reserved.
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS FILE HEADER.
 *
 * This code is free software; you can redistribute it and/or modify it
 * under the terms of the GNU General Public License version 2 only, as
 * published by the Free Software Foundation.  Oracle designates this
 * particular file as subject to the "Classpath" exception as provided
 * by Oracle in the LICENSE file that accompanied this code.
 *
 * This code is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE.  See the GNU General Public License
 * version 2 for more details (a copy is included in the LICENSE file that
 * accompanied this code).
 *
 * You should have received a copy of the GNU General Public License version
 * 2 along with this work; if not, write to the Free Software Foundation,
 * Inc., 51 Franklin St, Fifth Floor, Boston, MA 02110-1301 USA.
 *
 * Please contact Oracle, 500 Oracle Parkway, Redwood Shores, CA 94065 USA
 * or visit www.oracle.com if you need additional information or have any
 * questions.
 */
package org.openjdk.jmh.runner;

import org.junit.Test;
import org.openjdk.jmh.results.BenchmarkTaskResult;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import static org.junit.Assert.assertEquals;

public class BenchmarkResultTaskCoalesceTest {

    static void tryWith(int expected, int[][] pairs) {
        List<BenchmarkTaskResult> filtered = tryWith(pairs);
        assertEquals(expected, filtered.size());
    }

    static List<BenchmarkTaskResult> tryWith(int[][] pairs) {
        List<BenchmarkTaskResult> btrs = new ArrayList<>();
        for (int[] pair : pairs) {
            btrs.add(new BenchmarkTaskResult(1, 1, pair[0], pair[1]));
        }

        return BenchmarkHandler.selectIntersecting(btrs);
    }

    @Test
    public void random() {
        Random r = new Random();
        for (int t = 0; t < 100_000; t++) {
            int count = r.nextInt(256);
            int[][] pairs = new int[count][];
            for (int c = 0; c < count; c++) {
                int start = r.nextInt(10000);
                int end = start + r.nextInt(10000 - start);
                pairs[c] = new int[] { start, end };
            }

            tryWith(pairs);
        }
    }

    @Test
    public void empty() {
        tryWith(0, new int[][] {});
    }

    @Test
    public void single() {
        tryWith(1, new int[][] {
                {1, 10}
        });
    }

    @Test
    public void fullIntersect() {
        tryWith(3, new int[][] {
                {1, 10},
                {5, 9},
                {2, 6},
        });
    }

    @Test
    public void partialIntersect() {
        tryWith(2, new int[][] {
                {1, 10},
                {2, 4},
                {6, 9},
        });
    }

    @Test
    public void touching() {
        tryWith(1, new int[][] {
                {1, 3},
                {3, 6},
                {6, 9},
        });
    }

}
