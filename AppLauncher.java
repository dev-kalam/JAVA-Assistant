package testassistant;

import java.io.IOException;

public class AppLauncher {

    public static String openApp(String appName) {
        try {
            appName = appName.toLowerCase().trim();

            switch (appName) {
                // System Applications
                case "calculator":
                case "calc":
                    Runtime.getRuntime().exec("calc.exe");
                    return "🧮 Calculator activated!";

                case "notepad":
                    Runtime.getRuntime().exec("notepad.exe");
                    return "📝 Notepad ready for your notes!";

                case "paint":
                case "mspaint":
                    Runtime.getRuntime().exec("mspaint.exe");
                    return "🎨 Paint canvas loading...";

                case "wordpad":
                    Runtime.getRuntime().exec("write.exe");
                    return "📓 WordPad is ready!";

                case "task manager":
                case "taskmgr":
                    Runtime.getRuntime().exec("taskmgr.exe");
                    return "❗ Task Manager activated!";

                case "explorer":
                case "file explorer":
                case "this pc":
                    Runtime.getRuntime().exec("explorer.exe");
                    return "🗂️ File Explorer opened!";

                // Browsers
                case "chrome":
                case "google chrome":
                case "browser":
                    Runtime.getRuntime().exec(new String[]{"cmd", "/c", "start", "chrome"});
                    return "🌐 Launching Google Chrome...";

                case "firefox":
                case "mozilla":
                    Runtime.getRuntime().exec(new String[]{"cmd", "/c", "start", "firefox"});
                    return "🦊 Firefox is on the run!";

                case "edge":
                case "microsoft edge":
                    Runtime.getRuntime().exec(new String[]{"cmd", "/c", "start", "msedge"});
                    return "📘 Microsoft Edge launching...";

                // System Tools    
                case "cmd":
                case "command prompt":
                case "terminal":
                case "command panel":
                    Runtime.getRuntime().exec(new String[]{"cmd", "/c", "start", "cmd"});
                    return "💻 Command Prompt at your service!";

                case "control panel":
                case "control":
                    Runtime.getRuntime().exec(new String[]{"cmd", "/c", "start", "control"});
                    return "⚙️ Control Panel unlocked!";

                case "settings":
                case "windows settings":
                case "setting":
                    Runtime.getRuntime().exec(new String[]{"cmd", "/c", "start", "ms-settings:"});
                    return "⚙️ Opening Windows Settings...";

                // Office Apps
                case "word":
                case "microsoft word":
                case "ms word":
                    Runtime.getRuntime().exec(new String[]{"cmd", "/c", "start", "winword"});
                    return "📄 Microsoft Word opening...";

                case "excel":
                case "microsoft excel":
                case "ms excel":
                    Runtime.getRuntime().exec(new String[]{"cmd", "/c", "start", "excel"});
                    return "📊 Excel spreadsheet coming up!";

                case "powerpoint":
                case "microsoft powerpoint":
                case "ms powerpoint":
                    Runtime.getRuntime().exec(new String[]{"cmd", "/c", "start", "powerpnt"});
                    return "📽️ PowerPoint is ready to present!";

                // Web Apps
                case "youtube":
                case "yt":
                    Runtime.getRuntime().exec(new String[]{"cmd", "/c", "start", "https://www.youtube.com"});
                    return "🎬 Opening YouTube now...";

                case "google":
                case "gl":
                    Runtime.getRuntime().exec(new String[]{"cmd", "/c", "start", "https://www.google.com"});
                    return "🔎 Opening Google now...";

                case "gmail":
                case "gml":
                    Runtime.getRuntime().exec(new String[]{"cmd", "/c", "start", "https://www.gmail.com"});
                    return "📧 Opening Gmail now...";

                // System Utilities
                case "device manager":
                    Runtime.getRuntime().exec(new String[]{"cmd", "/c", "start", "devmgmt.msc"});
                    return "🔧 Opening Device Manager now...";

                case "disk management":
                case "management":
                    Runtime.getRuntime().exec(new String[]{"cmd", "/c", "start", "diskmgmt.msc"});
                    return "💽 Opening Disk Management now...";

                case "system configuration":
                    Runtime.getRuntime().exec(new String[]{"cmd", "/c", "start", "msconfig"});
                    return "🛠️ Opening System Configuration now...";
                case "downloads":
                    Runtime.getRuntime().exec("explorer.exe shell:Downloads");
                    return "⬇️ Opening Downloads...";
                case "registry editor":
                case "registry":
                    Runtime.getRuntime().exec(new String[]{"cmd", "/c", "start", "regedit"});
                    return "📚 Opening Registry Editor now...";
                case "downloads folder":
                case "download":
                    Runtime.getRuntime().exec("explorer.exe shell:Downloads");
                    return "⬇️ Opening Downloads folder...";

                case "documents folder":
                case "documents":
                    Runtime.getRuntime().exec("explorer.exe shell:Documents");
                    return "📄 Opening Documents folder...";

                case "pictures folder":
                case "pictures":
                    Runtime.getRuntime().exec("explorer.exe shell:Pictures");
                    return "🖼️ Opening Pictures folder...";

                case "videos folder":
                case "videos":
                    Runtime.getRuntime().exec("explorer.exe shell:Videos");
                    return "🎞️ Opening Videos folder...";

                case "recycle bin":
                    Runtime.getRuntime().exec("explorer.exe shell:RecycleBinFolder");
                    return "🗑️ Opening Recycle Bin...";

                case "snipping tool":
                case "screenshot":
                    Runtime.getRuntime().exec("snippingtool.exe");
                    return "✂️ Snipping Tool launched...";

                case "magnifier":
                    Runtime.getRuntime().exec("magnify.exe");
                    return "🔍 Magnifier is on...";

                case "on-screen keyboard":
                case "osk":
                    Runtime.getRuntime().exec("osk.exe");
                    return "⌨️ On-Screen Keyboard launched...";
                case "ans":
                    return "I'm an assistant. I'm here to assist you.";
                // Default case
                default:
                    return "⚠️ Glitch says: '" + appName + "'? I don't know that one yet!";
            }
        } catch (IOException e) {
            return "❌ Whoops! Glitch failed to open '" + appName + "'. Make sure it's installed and try again!";
        }
    }
}
