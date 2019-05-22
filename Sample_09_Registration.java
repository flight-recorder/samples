import jdk.jfr.Event;
import jdk.jfr.FlightRecorder;
import jdk.jfr.Label;
import jdk.jfr.Name;
import jdk.jfr.Registered;

// Description
// -----------
//
// The sample shows how an event class can be registered and unregistered. 
// By default, events are registered when the event class is initialized. It is
// possible to override that behavior using the Registered annotation.
//
// One reason to register manually is to take control of the security context
// the event is being initialized in.
//
// The difference between the Enabled annotation and the Registered annotation, 
// is that when an event is unregistered, its metadata, such as the field layout
// , is not available for inspection.
//
// A call to FlightRecorder::register can ensure that an event class is visible
// for configuration, for example to a JMX client 

// How to run
// ----------
//
// $ java -XX:StartFlightRecording:filename=09.jfr Sample_08_Allowed_Fields.java 
// $ jfr print --events Message 09.jfr
//
//
public class Sample_09_Registration {

    @Name("com.sample.Message")
    @Label("Message")
    @Registered(false)
    static class Message extends Event {
        String message;
    }

    public static void main(String... args) {
        Message event1 = new Message();
        event1.message = "Not registered, so you won't see this";
        event1.commit();
        
        FlightRecorder.register(Message.class);
        Message event2 = new Message();
        event2.message = "Now registered, so you will see this!";
        event2.commit();
        
        FlightRecorder.unregister(Message.class);
        
        Message event3 = new Message();
        event3.message = "Not registered again, so you won't see this";
        event3.commit();        
    }
}