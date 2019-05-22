
import jdk.jfr.Event;
import jdk.jfr.Label;
import jdk.jfr.Name;

// Description
// -----------
//
// The sample shows how the Label annotation can be used to give 
// human-readable names to events and fields. A label will typically
// occur in column headers or to describe the axis of a graph. 
//
// Labels should use headline-style capitalization, capitalize the first 
// and last words, and all nouns, pronouns, adjectives, verbs and adverbs. 
// Do not include ending punctuation.
//
// Avoid abbreviations such as "Info", "Stats" and "Alloc", instead use
// the full name, for example "Statistics" or "Information". Don't include
// the word "Event" in the label, it's implicit. 
// 
// The Label annotation should not be used to identify an event, instead use the 
// Name annotation.
//
// How to run
// ----------
//
// $ java -XX:StartFlightRecording:filename=14.jfr Sample_14_Label.java 
// $ jfr print --events Example 14.jfr
//
//
public class Sample_14_Label {

    @Name("com.sample.AccountUpdate")
    @Label("Account Update")
    static class AccountUpdate extends Event {
        @Label("Amount")
        long amount;

        @Label("User Name")
        String userName;
    }

    public static void main(String... args) throws Exception {
        AccountUpdate event = new AccountUpdate();
        event.amount = 1012;
        event.userName = "john";
        event.commit();
    }
}
