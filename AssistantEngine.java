
package testassistant;

public class AssistantEngine {
    private final CommandProcessor commandProcessor;
    private final VoiceOutput voiceOutput;
    private final VoiceInput voiceInput;
    private boolean isListening = false;

    public AssistantEngine() {
        this.commandProcessor = new CommandProcessor();
        this.voiceOutput = new VoiceOutput();
        this.voiceInput = new VoiceInput();
    }

    public String processCommand(String text) {
        String response = commandProcessor.process(text);
        voiceOutput.speak(response);
        return response;
    }

    public void startListening() {
        isListening = true;
        voiceOutput.speak("I'm listening...");
    }

    public void stopListening() {
        isListening = false;
        voiceOutput.speak("Stopped listening");
    }

    public String getVoiceInput() {
        if (isListening) {
            voiceOutput.speak("Please speak now");
            String input = voiceInput.listen();
            voiceOutput.speak("You said: " + input);
            return input;
        }
        return "";
    }

    public void startConversation() {
        voiceOutput.speak("Hello! I'm Glitch. How can I help you today?");
    }
}
