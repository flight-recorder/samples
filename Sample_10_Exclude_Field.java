import jdk.jfr.Event;
import jdk.jfr.Label;

// Description
// -----------
//
// The sample shows how the transient keyword can be used to exclude fields
// from being persisted.
//
// How to run
// ----------
//
// $ java -XX:StartFlightRecording:filename=10.jfr Sample_10_Exclude_Field.java 
// $ jfr print --events Message 10.jfr
//
//
public class Sample_10_Exclude_Field {

    @Label("Message")
    static class Message extends Event {
        String messageA;
        transient String messageB;
        String messageC;
    }

    public static void main(String... args) {

        Message event = new Message();
        event.messageA = "hello";
        event.messageB = "world"; // will not be persisted.
        event.messageC = "!";
        event.commit();
    }
}