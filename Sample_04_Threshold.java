import java.util.Random;

import jdk.jfr.Event;
import jdk.jfr.Label;
import jdk.jfr.Name;
import jdk.jfr.Threshold;

// Description
// -----------
//
// The sample shows how the Threshold annotation can be used to limit the number
// of events being recorded by imposing a threshold, for example 20 ms. 
//
// Valid units for a threshold are: ns, us, ms, s, m, h, and d.
//
// The sample also show that invoking the commit method ends timing of 
// the event without the need of an explicit call to the end method. 
// 
// By default events have a threshold of 0 ns, so it recommended to set a
// threshold if an operation occurs frequently and outliers are of greatest 
// concern.
//
// How to run
// ----------
//
// $ java -XX:StartFlightRecording:filename=04.jfr Sample_04_Threshold.java 
// $ jfr print --events RandomSleep 04.jfr
//
//
public class Sample_04_Threshold {

    @Name("com.sample.RandomSleep")
    @Label("Random Sleep")
    @Threshold("20 ms")
    static class RandomSleep extends Event {
        @Label("Random Value")
        int randomValue;
    }

    public static void main(String... args) throws Exception {
        Random r = new Random();
        for (int i = 0; i < 30; i++) {
            RandomSleep event = new RandomSleep();
            event.begin();
            event.randomValue = r.nextInt();
            Thread.sleep(event.randomValue);
            event.commit();
        }
    }
}
