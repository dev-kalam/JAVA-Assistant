package testassistant;

import java.io.IOException;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class CommandProcessor {

    private final Calculator calculator = new Calculator();

    public String process(String command) {
        command = command.toLowerCase().trim();

        try {
            // Time and Date commands
            if (command.contains("time")) {
                return "⏰ Current time is " + LocalTime.now().format(DateTimeFormatter.ofPattern("hh:mm a"));
            } else if (command.contains("date")) {
                return "📅 Today is " + java.time.LocalDate.now();
            } // Greetings
            else if (command.contains("hello") || command.contains("hi") || command.contains("hey")) {
                String[] greetings = {
                    "👋 Hello! How can I help you?",
                    "🤖 Hi there! What can I do for you?",
                    "💡 Hey! How can I assist you today?"
                };
                return greetings[(int) (Math.random() * greetings.length)];
            } // Calculator commands
            else if (command.contains("add") || command.contains("subtract")
                    || command.contains("multiply") || command.contains("divide")
                    || command.contains("calculate") || command.contains("math")) {
                return calculator.handleMathCommand(command);
            } // Application launching - comprehensive list matching AppLauncher
            else if (command.startsWith("open ")) {
                String appName = command.substring(5);
                return AppLauncher.openApp(appName);
            } else if (command.startsWith("launch ")) {
                String appName = command.substring(7);
                return AppLauncher.openApp(appName);
            } else if (command.toLowerCase().startsWith("what") && command.toLowerCase().contains("can you do")) {

                return AppLauncher.openApp("ans");

            } // System commands
            else if (command.contains("downloads folder") || command.contains("downloads")) {
                return AppLauncher.openApp("downloads");
            } else if (command.contains("documents folder") || command.contains("documents")) {
                return AppLauncher.openApp("documents");
            } else if (command.contains("pictures folder") || command.contains("pictures")) {
                return AppLauncher.openApp("pictures");
            } else if (command.contains("videos folder") || command.contains("videos")) {
                return AppLauncher.openApp("videos");
            } else if (command.contains("recycle bin")) {
                return AppLauncher.openApp("recycle bin");
            } else if (command.contains("snipping tool") || command.contains("screenshot")) {
                return AppLauncher.openApp("snipping tool");
            } else if (command.contains("magnifier")) {
                return AppLauncher.openApp("magnifier");
            } else if (command.contains("on-screen keyboard") || command.contains("osk")) {
                return AppLauncher.openApp("on-screen keyboard");
            } else if (command.contains("calculator") || command.contains("calc")) {
                return AppLauncher.openApp("calculator");
            } else if (command.contains("shutdown") || command.contains("shut down")) {
                Runtime.getRuntime().exec("shutdown -s -t 0");
                return "🔌 Shutting down the computer...";
            } else if (command.contains("restart") || command.contains("reboot")) {
                Runtime.getRuntime().exec("shutdown -r -t 0");
                return "🔄 Restarting the computer...";
            } else if (command.contains("lock screen") || command.contains("lock pc")) {
                Runtime.getRuntime().exec("rundll32.exe user32.dll,LockWorkStation");
                return "🔒 Locking your computer...";
            } else if (command.contains("sleep") || command.contains("hibernate")) {
                Runtime.getRuntime().exec("rundll32.exe powrprof.dll,SetSuspendState 0,1,0");
                return "💤 Putting computer to sleep...";
            } // Web searches and online services
            else if (command.contains("wikipedia") || command.contains("wiki")) {
                String topic = extractSearchQuery(command, "wikipedia", "wiki");
                Runtime.getRuntime().exec(new String[]{"cmd", "/c", "start", "https://en.wikipedia.org/wiki/" + topic});
                return "🔍 Searching Wikipedia for: " + topic;
            } else if (command.contains("youtube") || command.contains("yt")) {
                String query = extractSearchQuery(command, "youtube", "yt");
                Runtime.getRuntime().exec(new String[]{"cmd", "/c", "start", "https://www.youtube.com/results?search_query=" + query});
                return "🎥 Searching YouTube for: " + query;
            } else if (command.contains("google") || command.contains("search") || command.contains("gl")) {
                String query = extractSearchQuery(command, "google", "search", "gl");
                Runtime.getRuntime().exec(new String[]{"cmd", "/c", "start", "https://www.google.com/search?q=" + query});
                return "🌐 Searching Google for: " + query;
            } else if (command.contains("gmail") || command.contains("gml")) {
                Runtime.getRuntime().exec(new String[]{"cmd", "/c", "start", "https://mail.google.com"});
                return "📧 Opening Gmail...";
            } // Social media platforms
            else if (command.contains("facebook") || command.contains("fb")) {
                Runtime.getRuntime().exec(new String[]{"cmd", "/c", "start", "https://www.facebook.com"});
                return "📘 Opening Facebook...";
            } else if (command.contains("twitter") || command.contains("tw")) {
                Runtime.getRuntime().exec(new String[]{"cmd", "/c", "start", "https://twitter.com"});
                return "🐦 Opening Twitter...";
            } else if (command.contains("instagram") || command.contains("ig")) {
                Runtime.getRuntime().exec(new String[]{"cmd", "/c", "start", "https://www.instagram.com"});
                return "📸 Opening Instagram...";
            } else if (command.contains("linkedin") || command.contains("ln")) {
                Runtime.getRuntime().exec(new String[]{"cmd", "/c", "start", "https://www.linkedin.com"});
                return "💼 Opening LinkedIn...";
            } else if (command.contains("reddit") || command.contains("rdt")) {
                Runtime.getRuntime().exec(new String[]{"cmd", "/c", "start", "https://www.reddit.com"});
                return "👽 Opening Reddit...";
            } // Productivity tools
            else if (command.contains("chatgpt") || command.contains("gpt")) {
                Runtime.getRuntime().exec(new String[]{"cmd", "/c", "start", "https://chat.openai.com"});
                return "🤖 Opening ChatGPT...";
            } else if (command.contains("outlook") || command.contains("ol")) {
                Runtime.getRuntime().exec(new String[]{"cmd", "/c", "start", "https://outlook.live.com"});
                return "📨 Opening Outlook...";
            } // System utilities
            else if (command.contains("task manager")) {
                return AppLauncher.openApp("task manager");
            } else if (command.contains("control panel")) {
                return AppLauncher.openApp("control panel");
            } else if (command.contains("device manager")) {
                return AppLauncher.openApp("device manager");
            } else if (command.contains("disk management")) {
                return AppLauncher.openApp("disk management");
            } // Microsoft Office
            else if (command.contains("word") || command.contains("microsoft word")) {
                return AppLauncher.openApp("microsoft word");
            } else if (command.contains("excel") || command.contains("microsoft excel")) {
                return AppLauncher.openApp("microsoft excel");
            } else if (command.contains("powerpoint") || command.contains("microsoft powerpoint")) {
                return AppLauncher.openApp("microsoft powerpoint");
            } else if (command.contains("calculator") || command.contains("calc")) {
                return AppLauncher.openApp("calculator");
            } else if (command.contains("notepad")) {
                return AppLauncher.openApp("notepad");
            } else if (command.contains("paint") || command.contains("mspaint")) {
                return AppLauncher.openApp("paint");
            } else if (command.contains("wordpad")) {
                return AppLauncher.openApp("wordpad");
            } else if (command.contains("task manager") || command.contains("taskmgr")) {
                return AppLauncher.openApp("task manager");
            } else if (command.contains("explorer") || command.contains("file explorer") || command.contains("this pc")) {
                return AppLauncher.openApp("explorer");
            } // Browsers
            else if (command.contains("chrome") || command.contains("google chrome") || command.contains("browser")) {
                return AppLauncher.openApp("google chrome");
            } else if (command.contains("firefox") || command.contains("mozilla")) {
                return AppLauncher.openApp("firefox");
            } else if (command.contains("edge") || command.contains("microsoft edge")) {
                return AppLauncher.openApp("microsoft edge");
            } // System Tools
            else if (command.contains("cmd") || command.contains("command prompt") || command.contains("terminal") || command.contains("command panel")) {
                return AppLauncher.openApp("command prompt");
            } else if (command.contains("control panel") || command.contains("control")) {
                return AppLauncher.openApp("control panel");
            } else if (command.contains("settings") || command.contains("windows settings") || command.contains("setting")) {
                return AppLauncher.openApp("settings");
            } // Office Apps
            else if (command.contains("word") || command.contains("microsoft word") || command.contains("ms word")) {
                return AppLauncher.openApp("microsoft word");
            } else if (command.contains("excel") || command.contains("microsoft excel") || command.contains("ms excel")) {
                return AppLauncher.openApp("microsoft excel");
            } else if (command.contains("powerpoint") || command.contains("microsoft powerpoint") || command.contains("ms powerpoint")) {
                return AppLauncher.openApp("microsoft powerpoint");
            } // Web Apps
            else if (command.contains("youtube") || command.contains("yt")) {
                return AppLauncher.openApp("youtube");
            } else if (command.contains("google") || command.contains("gl")) {
                return AppLauncher.openApp("google");
            } else if (command.contains("gmail") || command.contains("gml")) {
                return AppLauncher.openApp("gmail");
            } // System Utilities
            else if (command.contains("device manager")) {
                return AppLauncher.openApp("device manager");
            } else if (command.contains("disk management") || command.contains("management")) {
                return AppLauncher.openApp("disk management");
            } else if (command.contains("system configuration")) {
                return AppLauncher.openApp("system configuration");
            } else if (command.contains("registry editor") || command.contains("registry")) {
                return AppLauncher.openApp("registry editor");
            } // Entertainment
            else if (command.contains("spotify")) {
                return AppLauncher.openApp("spotify");
            } else if (command.contains("vlc")) {
                return AppLauncher.openApp("vlc");
            } else if (command.contains("netflix")) {
                Runtime.getRuntime().exec(new String[]{"cmd", "/c", "start", "https://www.netflix.com"});
                return "🍿 Opening Netflix...";
            } // Entertainment
            else if (command.contains("spotify")) {
                return AppLauncher.openApp("spotify");
            } else if (command.contains("vlc")) {
                return AppLauncher.openApp("vlc");
            } else if (command.contains("netflix")) {
                Runtime.getRuntime().exec(new String[]{"cmd", "/c", "start", "https://www.netflix.com"});
                return "🍿 Opening Netflix...";
            } // Jokes and fun
            else if (command.contains("joke") || command.contains("funny")) {
                String[] jokes = {
                    "Why don't scientists trust atoms? Because they make up everything!",
                    "I told my computer I needed a break... it said 'No problem, I'll go to sleep.'",
                    "Why was the math book sad? It had too many problems.",
                    "What do you call a fake noodle? An impasta!",
                    "How do you organize a space party? You planet!"
                };
                return "😂 " + jokes[(int) (Math.random() * jokes.length)];
            } // Default response
            else {
                return "🤔 I didn't understand that command. Try:\n"
                        + "- 'Open [app]' to launch applications\n"
                        + "- 'What time is it?' for current time\n"
                        + "- 'Search [query]' to web search\n"
                        + "- 'Tell me a joke' for fun";
            }
        } catch (IOException e) {
            return "❌ Oops! Something went wrong: " + e.getMessage();
        }
    }

    private String extractSearchQuery(String command, String... keywords) {
        String query = command;
        for (String keyword : keywords) {
            query = query.replace(keyword, "");
        }
        query = query.replace("search", "")
                .replace("find", "")
                .replace("for", "")
                .replace("on", "")
                .trim()
                .replace(" ", "_");
        return query.isEmpty() ? "everything" : query;
    }

    String processCommand(String command) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
