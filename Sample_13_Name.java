
import jdk.jfr.Event;
import jdk.jfr.Label;
import jdk.jfr.Name;

//Description
//-----------
//
// The sample shows how the Name annotation can be used to give a symbolic name
// to events and fields.
//
// By default, events get their name from the fully qualified class name. This
// works well for experimentation, but should be avoided for production.
//
// For example, there may be a need to refactor the source code and move the 
// event class to a different package. Doing so will break code, or
// settings files, that tries to configure the event. It will also break 
// code that parses a recording file and uses the name to identify an event.
// 
// The fully qualified class name may also contain redundant or unnecessary 
// information such as "jfr", "internal", "events", or "Event" that
// is best left out.
//
// The name should be short, but not so short that it clashes with other 
// organizations or products. The name should be easy to understand for users 
// that want to configure the event. This is especially true if the event is 
// part of a framework or library that is meant to be used by others. It is 
// usually sufficient to put all the events for a library or product in the 
// same namespace. For example, all the events for OpenJDK are in the "jdk" 
// namespace. There are no sub-namespaces for "hotspot", "gc" or 
// "compiler" as this would just complicate things. It is however possible to 
// divide events into categories using the Category annotation, which can be 
// changed freely without causing disruption. 
// 
// A stable and easy to use event name is on the form:
//
// [org|com|net].[organization|product].EventName
//
// How to run
// ----------
//
// $ java -XX:StartFlightRecording:filename=13.jfr Sample_13_Name.java 
// $ jfr print --events Example 13.jfr
//
//
public class Sample_13_Name {

    @Name("com.sample.Example")
    @Label("Example")
    static class Lousy_name_Event extends Event {
        @Label("Message")
        @Name("message")
        String _lousy_fieldName;
    }

    public static void main(String... args) throws Exception {
        Lousy_name_Event event = new Lousy_name_Event();
        event._lousy_fieldName = "hello!";
        event.commit();
    }
}
