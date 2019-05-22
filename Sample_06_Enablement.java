
import jdk.jfr.Enabled;
import jdk.jfr.Event;
import jdk.jfr.Label;
import jdk.jfr.Name;

// Description
// -----------
//
// The sample shows how events can be enabled and disabled using the Enabled
// annotation. Disabled events will typically be eliminated by the JIT compiler.
//
// The sample also demonstrates how wildcards can be used to filter out two
// events when printing the contents of a file.
//
// How to run
// ----------
//
// $ java -XX:StartFlightRecording:filename=06.jfr Sample_06_Enablement.java 
// $ jfr print --events com.sample.* 06.jfr
//
//
public class Sample_06_Enablement {

    @Name("com.sample.WontSeeMee")
    @Label("Wont See Mee")
    @Enabled(false)
    static class WontSeeMe extends Event {
    }

    @Name("com.sample.WillSeeMee")
    @Label("Will See Me")
    @Enabled(true)
    static class WillSeeMe extends Event {
    }

    public static void main(String... args) throws Exception {
        WontSeeMe event1 = new WontSeeMe();
        event1.commit();
        
        WillSeeMe event2 = new WillSeeMe();
        event2.commit();
    }
}