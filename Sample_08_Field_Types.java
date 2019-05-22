import jdk.jfr.Event;
import jdk.jfr.Label;
import jdk.jfr.Name;

// Description
// -----------
//
// The sample shows which field types that can be persisted in an event.
//
// Field types that can be used in Java:
// 
// - java.lang.String, may be null
// - java.lang.Thread, may be null
// - java.lang.Class, may be null
// - byte
// - short
// - int
// - long
// - float
// - double
// - char
// - boolean
//
// Arrays are not supported in Java. Events emitted  by the HotSpot JVM may 
// have additional types, for example StackTrace.
//
// How to run
// ----------
//
// $ java -XX:StartFlightRecording:filename=08.jfr Sample_08_Fields_Types.java 
// $ jfr print --events FieldTypes 08.jfr
//
//
public class Sample_08_Field_Types {

    @Name("com.sample.FieldTypes")
    @Label("Allowed Field Types")
    static class FieldTypes extends Event {
        @Label("Class Value")
        Class<?> classValue;

        @Label("Thread Value")
        Thread threadValue; // thread must be started
        
        @Label("String Value")
        String stringdValue;

        @Label("Byte Value")
        byte byteValue;

        @Label("Short Value")
        short shortValue;

        @Label("Int Value")
        int intValue;

        @Label("Long Value")
        long longValue;

        @Label("Float Value")
        float floatValue;

        @Label("Double Value")
        double doubleValue;

        @Label("Character Value")
        char characterValue;
        
        @Label("Boolean Value")
        boolean booleanValue;
    }

    public static void main(String... args) {
       FieldTypes event = new FieldTypes();
       event.classValue = Math.class;
       event.threadValue = Thread.currentThread();
       event.stringdValue = "Hello";
       event.byteValue = 42;
       event.shortValue = 4711;
       event.intValue = Integer.MAX_VALUE;
       event.longValue = Long.MAX_VALUE;
       event.doubleValue = Math.PI;
       event.floatValue = Float.NaN;
       event.characterValue = '!';
       event.booleanValue = true;
       event.commit();
    }
}