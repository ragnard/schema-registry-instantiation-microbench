package net.blahonga;

import io.confluent.common.config.AbstractConfig;
import io.confluent.common.config.ConfigDef;
import org.apache.avro.generic.GenericDatumWriter;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by ragge on 20/04/15.
 */
class DummyConfig extends AbstractConfig {

    private static final ConfigDef CONFIG;

    static {
        CONFIG = new ConfigDef()
                .define("class", ConfigDef.Type.CLASS, null, "");
    }

    public DummyConfig(Map<?, ?> originals) {
        super(CONFIG, originals);
    }
}
