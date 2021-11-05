package com.example;

import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.profile.GCProfiler;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.options.ChainedOptionsBuilder;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import java.util.concurrent.TimeUnit;

@State(Scope.Thread)
@BenchmarkMode(Mode.AverageTime)
@Fork(1)
@Measurement(iterations = 5, time = 1000, timeUnit = TimeUnit.MILLISECONDS)
@Warmup(iterations = 5, time = 1000, timeUnit = TimeUnit.MILLISECONDS)
@OutputTimeUnit(TimeUnit.NANOSECONDS)
public class TestBenchmark {

    private volatile long field = 0;

    @Setup(Level.Iteration)
    public void setUp() {

    }

    @Benchmark
    public long readVolatile() {
        return field;
    }

    public static void main(String[] args) throws Exception {

        ChainedOptionsBuilder optionsBuilder = new OptionsBuilder()
                .include(TestBenchmark.class.getName())
                .addProfiler(GCProfiler.class);
        new Runner(optionsBuilder.build()).run();
    }
}
