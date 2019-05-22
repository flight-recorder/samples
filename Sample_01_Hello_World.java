import jdk.jfr.Event;
import jdk.jfr.Label;
import jdk.jfr.Name;

// Description
// -----------
//
// The sample shows how to create an event, start a recording on command line
// and print the event to standard out.
//
// The Label annotation provides a human-readable name and the Name annotation
// provides a symbolic name that can be used to identify the event.
//
// A stable and easy to use symbolic name is usually on the form:
//
// [org|com|net].[organization|product].EventName
//
// How to run
// ----------
// 
// $ java -XX:StartFlightRecording:filename=01.jfr Sample_01_Hello_World.java 
// $ jfr print --events HelloWorld 01.jfr
//
//
public class Sample_01_Hello_World {
	
	@Name("com.sample.HelloWorld")
	@Label("Hello World")
	static class HelloWorld extends Event {
		@Label("Message")
		String message;
	}

	public static void main(String... args) {
		HelloWorld event = new HelloWorld();
		event.message = "hello, world!";
		event.commit();
	}
}