import jdk.jfr.Event;
import jdk.jfr.Label;
import jdk.jfr.Name;
import jdk.jfr.Unsigned;

// Description
// -----------
//
// The sample shows how unsigned numeric values can be stored in an event 
// using the Unsigned annotation.
//
// How to run
// ----------
//
// $ java -XX:StartFlightRecording:filename=12.jfr Sample_12_Unsigned_Fields.java 
// $ jfr print --events UnsignedTypes 12.jfr
//
//
public class Sample_12_Unsigned_Fields {

    @Name("com.sample.UnsignedTypes")
    @Label("Allowed Field Types")
    static class UnsignedTypes extends Event {

        @Label("Unsigned Byte Value")
        @Unsigned
        byte unsignedByteValue;

        @Label("Unsigned Short Value")
        @Unsigned
        short unsignedShortValue;

        @Label("Unsigned Int Value")
        @Unsigned
        int unsignedIntValue;

        @Label("Unsigned Long Value")
        @Unsigned
        long unsignedLongValue;
    }

    public static void main(String... args) {
        UnsignedTypes event = new UnsignedTypes();
        event.unsignedByteValue = (byte) 255;
        event.unsignedShortValue = (short) 65535;
        event.unsignedIntValue = Integer.parseUnsignedInt("4294967295");
        event.unsignedLongValue = -1;
        event.commit();
    }
}