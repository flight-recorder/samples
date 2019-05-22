import jdk.jfr.Category;
import jdk.jfr.Event;
import jdk.jfr.Label;
import jdk.jfr.Name;
import jdk.jfr.StackTrace;

// Description
// -----------
//
// The sample shows how an event class can be subclassed to inherit annotations, 
// settings and fields. Abstract event classes are not registered so their
// metadata is never available for inspection.
//
// Private fields are not inherited or annotation that lacks the 
// java.lang.Inherited meta annotation.
//
// How to run
// ----------
//
// $ java -XX:StartFlightRecording:filename=11.jfr Sample_11_Inheritance.java 
// $ jfr print --categories Animals 11.jfr
//
//
public class Sample_11_Inheritance {

    @Category("Animals")
    @StackTrace(false)
    abstract static class Animal extends Event {
        @Label("Hungry")
        boolean hungry;
    }

    @Label("Lion")
    @Name("com.sample.Lion")
    static class Lion extends Animal {
    }

    @Label("Cobra")
    @Name("com.sample.Cobra")
    static class Cobra extends Animal {
    }

    public static void main(String... args) {
        Lion l = new Lion();
        l.hungry = true;
        l.commit();
        
        Cobra c = new Cobra();
        c.hungry = false;
        c.commit();
    }
}