
import java.io.IOException;
import java.nio.file.FileStore;
import java.nio.file.FileSystems;

import jdk.jfr.DataAmount;
import jdk.jfr.Event;
import jdk.jfr.FlightRecorder;
import jdk.jfr.Label;
import jdk.jfr.Name;
import jdk.jfr.Period;

// Description
// -----------
//
// The sample shows how to create an event that is emitted periodically and
// how the period can be set using the Period annotation.
//
// Valid units for a period are: ns, us, ms, s, m, h, and d.
//
// It is also possible to specify:
//
// - "everyChunk"
//   A periodic event will be emitted at least once in the recording
//
// - "beginChunk"
//   A periodic event will be emitted in the beginning of a recording
//
// - "endChunk", 
//   A periodic event will be emitted in the end of a recording
//
// - "infinity", 
//   A periodic event will never be emitted
//
// How to run
// ----------
//
// $ java -XX:StartFlightRecording:filename=07.jfr Sample_07_Periodic.java 
// $ jfr print --events FileStore 07.jfr
//
//
public class Sample_07_Periodic {

    @Period("2 s")
    @Label("File Store")
    @Name("com.sample.FileStore")
    static class FileStoreEvent extends Event {
        @Label("Total")
        @DataAmount(DataAmount.BYTES)
        long total;
        
        @Label("Used")
        @DataAmount(DataAmount.BYTES)
        long used;

        @Label("Available")
        @DataAmount(DataAmount.BYTES)
        long available;
    }

    public static void main(String... args) throws Exception {
        Runnable r = () -> {
            for (FileStore s : FileSystems.getDefault().getFileStores()) {
                try {
                    FileStoreEvent event = new FileStoreEvent();
                    event.total = s.getTotalSpace();
                    event.used = s.getTotalSpace() - s.getUnallocatedSpace();
                    event.available = s.getUsableSpace();
                    event.commit();
                } catch (IOException ioe) {
                    // ignore
                }
            }
        };
        FlightRecorder.addPeriodicEvent(FileStoreEvent.class, r);
        System.out.println("Wait for ten seconds...");
        Thread.sleep(10_000);
        FlightRecorder.removePeriodicEvent(r);
    }
}