package testassistant;

import java.io.IOException;

public class SystemNarrator {
    
    public static void speak(String text) {
        try {
            // Clean text for command line
            String cleanText = text.replace("\"", "");
            
            // Windows PowerShell command for TTS
            String command = "powershell -Command \"Add-Type -AssemblyName System.Speech; "
                          + "$speak = New-Object System.Speech.Synthesis.SpeechSynthesizer; "
                          + "$speak.Speak('"+cleanText+"');\"";
            
            Runtime.getRuntime().exec(command);
            
        } catch (IOException e) {
            System.out.println("(Voice Error) " + text); // Fallback to text
        }
    }
}
