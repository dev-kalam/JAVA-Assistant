# 🧠 Glitch - Java Desktop Assistant

*Glitch* is a voice-enabled desktop assistant built in **Java**. It can open desktop applications, execute system commands, perform Google/YouTube/Wikipedia searches, and solve basic math problems — all using natural language input via text or voice!



## 🔧 Features

- 🎤 Voice & text command support
- 🖥️ Launch desktop apps like Notepad, Paint, CMD, Explorer
- 🌐 Search Google, YouTube, Wikipedia
- 🔐 System commands: shutdown, reboot, lock, etc.
- ➕ Basic calculator for math operations
- 🖼️ Beautiful Java Swing-based GUI



## 📁 Project Structure



Glitch-Assistant/
│
├── src/
│   ├── Main.java              # Entry point of the application
│   ├── AssistantGUI.java      # User interface
│   ├── CommandProcessor.java  # Command analysis and processing
│   └── AppLauncher.java       # Application launcher logic
│
├── resources/
│   └── background.gif         # Animated background for GUI
│
└── README.md





## 🚀 How to Run

1. **Clone the Repository**
   git clone https://github.com/yourusername/glitch-desktop-assistant.git
   cd glitch-desktop-assistant


2. Compile the Code

   
   javac src/*.java
  

3. Run the Application

   
   java src/Main
   

## 💬 Example Commands

### 📂 Open Applications

* open notepad
* launch calculator
* open task manager

### 🌍 Web Search

* search google who is Alan Turing
* search youtube java tutorials
* search wikipedia artificial intelligence

### ⚙️ System Commands

* shutdown pc
* restart computer
* lock pc
* open downloads folder

### ➗ Calculator Commands

* add 5 and 6
* multiply 9 by 3
* divide 20 by 4



## 📌 Requirements

* Java JDK 8 or above
* Internet (for search-related features)
* Windows OS (for Runtime.getRuntime().exec() compatibility)

## 🌱 Future Enhancements

* Add Text-to-Speech (TTS) for assistant replies
* Better NLP command parsing
* Auto-suggestions
* Reminders and alarms



## 🧑‍💻 Developer

Abul Kalam
📧 abulkalamripon09@gmail.com
🏫 Metropolitan University

---

## 📄 License

This project is licensed under the Apache License 2.0 - feel free to use and modify!

⭐ *Give a star* if you liked the project!

