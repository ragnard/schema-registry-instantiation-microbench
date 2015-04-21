package net.blahonga;

import org.apache.avro.Schema;
import org.apache.avro.generic.GenericDatumWriter;
import org.apache.avro.io.DatumWriter;

/**
 * Created by ragge on 21/04/15.
 */
public class DatumWriterProviderImpl implements DatumWriterProvider {
    @Override
    public DatumWriter getDatumWriter(Schema schema, Object object) {
        return new GenericDatumWriter();
    }
}
