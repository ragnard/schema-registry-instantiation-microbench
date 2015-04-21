package net.blahonga;

import org.apache.avro.Schema;
import org.apache.avro.io.DatumWriter;

/**
 * Created by ragge on 21/04/15.
 */
public interface DatumWriterProvider {

    DatumWriter getDatumWriter(Schema schema, Object object);

}
