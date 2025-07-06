package testassistant;

public class VoiceOutput {

    public void speak(String text) {
        // Simple text output for now
        // Can be replaced with text-to-speech later
        
        SystemNarrator.speak(text);
        System.out.println("Assistant: " + text);
    }
}
