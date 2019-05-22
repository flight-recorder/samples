import jdk.jfr.Event;
import jdk.jfr.Label;
import jdk.jfr.Name;
import jdk.jfr.StackTrace;

// Description
// -----------
//
// The sample shows how to create events with and without stack traces using
// the StackTrace annotation. It also shows how the recorded stack depth can be 
// changed using -XX:FlightRecorder:stackdepth=<depth> and 
// increased in print out using --stack-depth <depth>.
// 
// The cost of recording one stack frame is about as expensive as emitting
// one additional event, so the stack depth should only be increased in
// situation where it is really needed.

// How to run
// ----------
//
// $ java -XX:StartFlightRecording:filename=03.jfr 
//        -XX:FlightRecorderOptions:stackdepth=128
//        Sample_03_Stack_Traces.java 
// $ jfr print --events WithStackTrace,WithouttackTrace --stack-depth 64 03.jfr
//
//
public class Sample_03_Stack_Traces {

    @Name("com.sample.WithStackTrace")
    @Label("With Stack Trace")
    @StackTrace(true)
    static class WithStackTrace extends Event {
    }

    @Name("com.sample.WithouttackTrace")
    @Label("Without Stack Trace")
    @StackTrace(false)
    static class WithoutStackTrace extends Event {
    }

    public static void main(String... args) throws Exception {
        foo(50);
    }

    static void foo(int n) {
        if (n > 0) {
            bar(n - 1);
        }
        WithStackTrace event1 = new WithStackTrace();
        event1.commit();

        WithStackTrace event2 = new WithStackTrace();
        event2.commit();
    }
    
    static void bar(int n) {
        foo(n);
    }
}