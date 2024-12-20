/*
 * Copyright (c) 2005, 2013, Oracle and/or its affiliates. All rights reserved.
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
package org.openjdk.jmh.infra;

import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.runner.WorkloadParams;
import org.openjdk.jmh.runner.options.TimeValue;
import org.openjdk.jmh.util.Version;

import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.concurrent.TimeUnit;

/**
 * Benchmark parameters.
 *
 * <p>{@link BenchmarkParams} handles the parameters used in the current run.</p>
 * <p>This class is dual-purpose:</p>
 * <ol>
 *     <li>It acts as the interface between host JVM and forked JVM, so that the latter
 *     would not have to figure out the benchmark configuration again</li>
 *     <li>It can be injected into benchmark methods to access the runtime configuration
 *     info about the benchmark</li>
 * </ol>
 */
public final class BenchmarkParams extends BenchmarkParamsL2 {
    private static final long serialVersionUID = -53511295235994554L;

    byte b3_000, b3_001, b3_002, b3_003, b3_004, b3_005, b3_006, b3_007, b3_008, b3_009, b3_010, b3_011, b3_012, b3_013, b3_014, b3_015;
    byte b3_016, b3_017, b3_018, b3_019, b3_020, b3_021, b3_022, b3_023, b3_024, b3_025, b3_026, b3_027, b3_028, b3_029, b3_030, b3_031;
    byte b3_032, b3_033, b3_034, b3_035, b3_036, b3_037, b3_038, b3_039, b3_040, b3_041, b3_042, b3_043, b3_044, b3_045, b3_046, b3_047;
    byte b3_048, b3_049, b3_050, b3_051, b3_052, b3_053, b3_054, b3_055, b3_056, b3_057, b3_058, b3_059, b3_060, b3_061, b3_062, b3_063;
    byte b3_064, b3_065, b3_066, b3_067, b3_068, b3_069, b3_070, b3_071, b3_072, b3_073, b3_074, b3_075, b3_076, b3_077, b3_078, b3_079;
    byte b3_080, b3_081, b3_082, b3_083, b3_084, b3_085, b3_086, b3_087, b3_088, b3_089, b3_090, b3_091, b3_092, b3_093, b3_094, b3_095;
    byte b3_096, b3_097, b3_098, b3_099, b3_100, b3_101, b3_102, b3_103, b3_104, b3_105, b3_106, b3_107, b3_108, b3_109, b3_110, b3_111;
    byte b3_112, b3_113, b3_114, b3_115, b3_116, b3_117, b3_118, b3_119, b3_120, b3_121, b3_122, b3_123, b3_124, b3_125, b3_126, b3_127;
    byte b3_128, b3_129, b3_130, b3_131, b3_132, b3_133, b3_134, b3_135, b3_136, b3_137, b3_138, b3_139, b3_140, b3_141, b3_142, b3_143;
    byte b3_144, b3_145, b3_146, b3_147, b3_148, b3_149, b3_150, b3_151, b3_152, b3_153, b3_154, b3_155, b3_156, b3_157, b3_158, b3_159;
    byte b3_160, b3_161, b3_162, b3_163, b3_164, b3_165, b3_166, b3_167, b3_168, b3_169, b3_170, b3_171, b3_172, b3_173, b3_174, b3_175;
    byte b3_176, b3_177, b3_178, b3_179, b3_180, b3_181, b3_182, b3_183, b3_184, b3_185, b3_186, b3_187, b3_188, b3_189, b3_190, b3_191;
    byte b3_192, b3_193, b3_194, b3_195, b3_196, b3_197, b3_198, b3_199, b3_200, b3_201, b3_202, b3_203, b3_204, b3_205, b3_206, b3_207;
    byte b3_208, b3_209, b3_210, b3_211, b3_212, b3_213, b3_214, b3_215, b3_216, b3_217, b3_218, b3_219, b3_220, b3_221, b3_222, b3_223;
    byte b3_224, b3_225, b3_226, b3_227, b3_228, b3_229, b3_230, b3_231, b3_232, b3_233, b3_234, b3_235, b3_236, b3_237, b3_238, b3_239;
    byte b3_240, b3_241, b3_242, b3_243, b3_244, b3_245, b3_246, b3_247, b3_248, b3_249, b3_250, b3_251, b3_252, b3_253, b3_254, b3_255;

    public BenchmarkParams(String benchmark, String generatedTarget, boolean synchIterations,
                           int threads, int[] threadGroups, Collection<String> threadGroupLabels,
                           int forks, int warmupForks,
                           IterationParams warmup, IterationParams measurement,
                           Mode mode, WorkloadParams params,
                           TimeUnit timeUnit, int opsPerInvocation,
                           String jvm, Collection<String> jvmArgs,
                           String jdkVersion, String vmName, String vmVersion, String jmhVersion,
                           TimeValue timeout) {
        super(benchmark, generatedTarget, synchIterations,
                threads, threadGroups, threadGroupLabels,
                forks, warmupForks,
                warmup, measurement,
                mode, params,
                timeUnit, opsPerInvocation,
                jvm, jvmArgs,
                jdkVersion, vmName, vmVersion, jmhVersion,
                timeout);
    }
}

abstract class BenchmarkParamsL2 extends BenchmarkParamsL1 implements Serializable, Comparable<BenchmarkParams> {
    private static final long serialVersionUID = -1068219503090299117L;

    protected final String benchmark;
    protected final String generatedTarget;
    protected final boolean synchIterations;
    protected final int threads;
    protected final int[] threadGroups;
    protected final Collection<String> threadGroupLabels;
    protected final int forks;
    protected final int warmupForks;
    protected final IterationParams warmup;
    protected final IterationParams measurement;
    protected final Mode mode;
    protected final WorkloadParams params;
    protected final TimeUnit timeUnit;
    protected final int opsPerInvocation;
    protected final String jvm;
    protected final Collection<String> jvmArgs;
    protected final String jdkVersion;
    protected final String jmhVersion;
    protected final String vmName;
    protected final String vmVersion;
    protected final TimeValue timeout;

    public BenchmarkParamsL2(String benchmark, String generatedTarget, boolean synchIterations,
                             int threads, int[] threadGroups, Collection<String> threadGroupLabels,
                             int forks, int warmupForks,
                             IterationParams warmup, IterationParams measurement,
                             Mode mode, WorkloadParams params,
                             TimeUnit timeUnit, int opsPerInvocation,
                             String jvm, Collection<String> jvmArgs,
                             String jdkVersion, String vmName, String vmVersion, String jmhVersion,
                             TimeValue timeout) {
        this.benchmark = benchmark;
        this.generatedTarget = generatedTarget;
        this.synchIterations = synchIterations;
        this.threads = threads;
        this.threadGroups = threadGroups;
        this.threadGroupLabels = threadGroupLabels;
        this.forks = forks;
        this.warmupForks = warmupForks;
        this.warmup = warmup;
        this.measurement = measurement;
        this.mode = mode;
        this.params = params;
        this.timeUnit = timeUnit;
        this.opsPerInvocation = opsPerInvocation;
        this.jvm = jvm;
        this.jvmArgs = jvmArgs;
        this.jdkVersion = jdkVersion;
        this.vmName = vmName;
        this.vmVersion = vmVersion;
        this.jmhVersion = jmhVersion;
        this.timeout = timeout;
    }

    /**
     * @return how long to wait for iteration to complete
     */
    public TimeValue getTimeout() {
        return timeout;
    }

    /**
     * @return do we synchronize iterations?
     */
    public boolean shouldSynchIterations() {
        return synchIterations;
    }

    /**
     * @return iteration parameters for warmup phase
     */
    public IterationParams getWarmup() {
        return warmup;
    }

    /**
     * @return iteration parameters for measurement phase
     */
    public IterationParams getMeasurement() {
        return measurement;
    }

    /**
     * @return total measurement thread count
     */
    public int getThreads() {
        return threads;
    }

    /**
     * @return thread distribution within the group
     * @see org.openjdk.jmh.runner.options.ChainedOptionsBuilder#threadGroups(int...)
     */
    public int[] getThreadGroups() {
        return Arrays.copyOf(threadGroups, threadGroups.length);
    }

    /**
     * @return subgroup thread labels
     * @see #getThreadGroups()
     */
    public Collection<String> getThreadGroupLabels() {
        return Collections.unmodifiableCollection(threadGroupLabels);
    }

    /**
     * @return number of forked VM runs, which we measure
     */
    public int getForks() {
        return forks;
    }

    /**
     * @return number of forked VM runs, which we discard from the result
     */
    public int getWarmupForks() {
        return warmupForks;
    }

    /**
     * @return benchmark mode
     */
    public Mode getMode() {
        return mode;
    }

    /**
     * @return benchmark name
     */
    public String getBenchmark() {
        return benchmark;
    }

    /**
     * @return timeUnit used in results
     */
    public TimeUnit getTimeUnit() {
        return timeUnit;
    }

    /**
     * @return operations per invocation used
     */
    public int getOpsPerInvocation() {
        return opsPerInvocation;
    }

    /**
     * @return all workload parameters
     */
    public Collection<String> getParamsKeys() {
        return params.keys();
    }

    /**
     * @param key parameter key; usually the field name
     * @return parameter value for given key
     */
    public String getParam(String key) {
        if (params != null) {
            return params.get(key);
        } else {
            return null;
        }
    }

    /**
     * @return generated benchmark name
     */
    public String generatedBenchmark() {
        return generatedTarget;
    }

    /**
     * @return JVM executable path
     */
    public String getJvm() {
        return jvm;
    }

    /**
     * @return JMH version identical to {@link Version#getPlainVersion()}, but output format should
     *          get there input via bean for testing purposes.
     */
    public String getJmhVersion() {
        return jmhVersion;
    }

    /**
     * @return JVM options
     */
    public Collection<String> getJvmArgs() {
        return Collections.unmodifiableCollection(jvmArgs);
    }

    /**
     * @return version information as returned by the effective target JVM,
     *         via system property {@code java.version} and {@code java.vm.version}
     */
    public String getJdkVersion() {
        return jdkVersion;
    }

    /**
     * @return version information as returned by the effective target JVM,
     *         via system property {@code java.vm.version}
     */
    public String getVmVersion() {
        return vmVersion;
    }

    /**
     * @return name information as returned by the effective target JVM,
     *         via system property {@code java.vm.name}
     */
    public String getVmName() {
        return vmName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        BenchmarkParams that = (BenchmarkParams) o;

        if (!benchmark.equals(that.benchmark)) return false;
        if (mode != that.mode) return false;
        if (!params.equals(that.params)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = benchmark.hashCode();
        result = 31 * result + mode.hashCode();
        result = 31 * result + params.hashCode();
        return result;
    }

    @Override
    public int compareTo(BenchmarkParams o) {
        int v = mode.compareTo(o.mode);
        if (v != 0) {
            return v;
        }

        int v1 = benchmark.compareTo(o.benchmark);
        if (v1 != 0) {
            return v1;
        }

        if (params == null || o.params == null) {
            return 0;
        }

        return params.compareTo(o.params);
    }

    public String id() {
        StringBuilder sb = new StringBuilder();
        appendSanitized(sb, benchmark);
        sb.append("-");
        sb.append(mode);
        for (String key : params.keys()) {
            sb.append("-");
            appendSanitized(sb, key);
            sb.append("-");
            appendSanitized(sb, params.get(key));
        }
        return sb.toString();
    }

    private static void appendSanitized(StringBuilder builder, String s) {
        try {
            builder.append(URLEncoder.encode(s, "UTF-8"));
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
    }

}

abstract class BenchmarkParamsL1 {
    byte b1_000, b1_001, b1_002, b1_003, b1_004, b1_005, b1_006, b1_007, b1_008, b1_009, b1_010, b1_011, b1_012, b1_013, b1_014, b1_015;
    byte b1_016, b1_017, b1_018, b1_019, b1_020, b1_021, b1_022, b1_023, b1_024, b1_025, b1_026, b1_027, b1_028, b1_029, b1_030, b1_031;
    byte b1_032, b1_033, b1_034, b1_035, b1_036, b1_037, b1_038, b1_039, b1_040, b1_041, b1_042, b1_043, b1_044, b1_045, b1_046, b1_047;
    byte b1_048, b1_049, b1_050, b1_051, b1_052, b1_053, b1_054, b1_055, b1_056, b1_057, b1_058, b1_059, b1_060, b1_061, b1_062, b1_063;
    byte b1_064, b1_065, b1_066, b1_067, b1_068, b1_069, b1_070, b1_071, b1_072, b1_073, b1_074, b1_075, b1_076, b1_077, b1_078, b1_079;
    byte b1_080, b1_081, b1_082, b1_083, b1_084, b1_085, b1_086, b1_087, b1_088, b1_089, b1_090, b1_091, b1_092, b1_093, b1_094, b1_095;
    byte b1_096, b1_097, b1_098, b1_099, b1_100, b1_101, b1_102, b1_103, b1_104, b1_105, b1_106, b1_107, b1_108, b1_109, b1_110, b1_111;
    byte b1_112, b1_113, b1_114, b1_115, b1_116, b1_117, b1_118, b1_119, b1_120, b1_121, b1_122, b1_123, b1_124, b1_125, b1_126, b1_127;
    byte b1_128, b1_129, b1_130, b1_131, b1_132, b1_133, b1_134, b1_135, b1_136, b1_137, b1_138, b1_139, b1_140, b1_141, b1_142, b1_143;
    byte b1_144, b1_145, b1_146, b1_147, b1_148, b1_149, b1_150, b1_151, b1_152, b1_153, b1_154, b1_155, b1_156, b1_157, b1_158, b1_159;
    byte b1_160, b1_161, b1_162, b1_163, b1_164, b1_165, b1_166, b1_167, b1_168, b1_169, b1_170, b1_171, b1_172, b1_173, b1_174, b1_175;
    byte b1_176, b1_177, b1_178, b1_179, b1_180, b1_181, b1_182, b1_183, b1_184, b1_185, b1_186, b1_187, b1_188, b1_189, b1_190, b1_191;
    byte b1_192, b1_193, b1_194, b1_195, b1_196, b1_197, b1_198, b1_199, b1_200, b1_201, b1_202, b1_203, b1_204, b1_205, b1_206, b1_207;
    byte b1_208, b1_209, b1_210, b1_211, b1_212, b1_213, b1_214, b1_215, b1_216, b1_217, b1_218, b1_219, b1_220, b1_221, b1_222, b1_223;
    byte b1_224, b1_225, b1_226, b1_227, b1_228, b1_229, b1_230, b1_231, b1_232, b1_233, b1_234, b1_235, b1_236, b1_237, b1_238, b1_239;
    byte b1_240, b1_241, b1_242, b1_243, b1_244, b1_245, b1_246, b1_247, b1_248, b1_249, b1_250, b1_251, b1_252, b1_253, b1_254, b1_255;
}
