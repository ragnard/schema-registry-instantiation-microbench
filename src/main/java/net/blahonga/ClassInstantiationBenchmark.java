package net.blahonga;

import org.apache.avro.generic.GenericDatumWriter;
import org.apache.avro.io.DatumWriter;
import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import java.util.HashMap;

public class ClassInstantiationBenchmark {

    // Via new

    @Benchmark
    @BenchmarkMode(Mode.Throughput)
    public DatumWriter instantiateDirectly() {
        return new GenericDatumWriter();
    }

    // Via Class.newInstance

    @Benchmark
    @BenchmarkMode(Mode.Throughput)
    public DatumWriter instantiateViaClass() throws IllegalAccessException, InstantiationException {
        return GenericDatumWriter.class.newInstance();
    }

    // Via DatumWriterProvider

    private final static DatumWriterProvider datumWriterProvider;

    @Benchmark
    @BenchmarkMode(Mode.Throughput)
    public DatumWriter instantiateViaDatumWriterProvider() {
        return datumWriterProvider.getDatumWriter(null, null);
    }

    // Via AbstractConfig

    private final static DummyConfig config;

    @Benchmark
    @BenchmarkMode(Mode.Throughput)
    public DatumWriter instantiateViaAbstractConfig() throws IllegalAccessException, InstantiationException {
        return config.getConfiguredInstance("class", DatumWriter.class);
    }

    // setup

    static {
        datumWriterProvider = new DatumWriterProviderImpl();

        HashMap props = new HashMap();
        props.put("class", "org.apache.avro.generic.GenericDatumWriter");
        config = new DummyConfig(props);
    }

    public static void main(String[] args) throws RunnerException {
        Options opt = new OptionsBuilder()
                .include(ClassInstantiationBenchmark.class.getSimpleName())
                .measurementIterations(20)
                .warmupIterations(20)
                .forks(1)
                .build();

        new Runner(opt).run();
    }

}
