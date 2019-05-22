import java.util.Random;

import jdk.jfr.Event;
import jdk.jfr.Label;
import jdk.jfr.Name;
import jdk.jfr.Threshold;

// Description
// -----------
//
// The sample shows how the shouldCommit method can reduce overhead of 
// expensive operations, so they are only executed if the duration of the
// event exceeds the threshold.
//
// How to run
// ----------
//
// $ java -XX:StartFlightRecording:filename=05.jfr Sample_05_Should_Commit.java 
// $ jfr print --events RandomSleep 05.jfr
//
//
public class Sample_05_Should_Commit {

    @Name("com.sample.RandomSleep")
    @Label("Random Sleep")
    @Threshold("20 ms")
    static class RandomSleep extends Event {
        @Label("Value Kind")
        String valueKind;
    }

    public static void main(String... args) throws Exception {
        Random r = new Random();
        for (int i = 0; i < 30; i++) {
            RandomSleep event = new RandomSleep();
            event.begin();
            int value = r.nextInt(40);
            Thread.sleep(value);
            event.end();
            if (event.shouldCommit()) {
                // Format message outside timing of event
                if (value < 10) {
                    event.valueKind = " It was a low value of " + value + "!";
                } else if (value < 20) {
                    event.valueKind = " It was a normal value of " + value + "!";
                } else {
                    event.valueKind = " It was a high value of " + value + "!";
                }
                event.commit();
            }
        }
    }

}
