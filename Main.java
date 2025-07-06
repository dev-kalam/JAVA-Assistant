
package testassistant;

public class Main {
    public static void main(String[] args) {
        // Create our assistant
        AssistantEngine assistant = new AssistantEngine();
        SystemNarrator.speak("Hello!I am GLITCH. How Can I Assist you?");
       
       
        // Create and show GUI
        AssistantGUI gui = new AssistantGUI(assistant);
        gui.show();
        
        // Start the conversation
        assistant.startConversation();
    }
}
