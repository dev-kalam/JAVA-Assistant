package testassistant;

import javax.swing.*;
import java.awt.*;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class AssistantGUI {

    private final JFrame frame;
    private final JTextArea conversationArea;
    private final JTextField inputField;
    private final AssistantEngine assistantEngine;
    private final JButton voiceButton;
    private final ScheduledExecutorService executor;
    private boolean isListening = false;

    public AssistantGUI(AssistantEngine engine) {
        this.assistantEngine = engine;
        this.executor = Executors.newScheduledThreadPool(2);

        // Initialize components
        this.frame = new JFrame("Glitch");
        this.conversationArea = new JTextArea();
        this.inputField = new JTextField();
        this.voiceButton = new JButton("🎤");

        initializeGUI();
    }

    private void initializeGUI() {
        configureMainFrame();
        setupBackground();
        setupConversationArea();
        setupInputComponents();
        createMenuBar();
    }

    private void configureMainFrame() {
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);
        frame.setLayout(null);
        frame.setLocationRelativeTo(null);
    }

    private void setupBackground() {
        try {
            ImageIcon backgroundIcon = new ImageIcon(getClass().getResource("animated.gif"));
            if (backgroundIcon.getImage() != null) {
                JLabel backgroundLabel = new JLabel(backgroundIcon);
                backgroundLabel.setBounds(0, 0, frame.getWidth(), frame.getHeight());
                frame.setContentPane(backgroundLabel);
            } else {
                setSolidBackground();
            }
        } catch (Exception e) {
            setSolidBackground();
        }
        frame.getContentPane().setLayout(null);
    }

    private void setSolidBackground() {
        JLabel backgroundLabel = new JLabel();
        backgroundLabel.setBackground(new Color(30, 30, 40));
        backgroundLabel.setOpaque(true);
        frame.setContentPane(backgroundLabel);
    }

    private void setupConversationArea() {
        conversationArea.setEditable(false);
        conversationArea.setLineWrap(true);
        conversationArea.setWrapStyleWord(true);
        conversationArea.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        conversationArea.setOpaque(false);
        conversationArea.setForeground(Color.WHITE);

        JScrollPane scrollPane = new JScrollPane(conversationArea);
        scrollPane.setBounds(20, 20, 740, 300);
        scrollPane.setOpaque(false);
        scrollPane.getViewport().setOpaque(false);
        scrollPane.setBorder(BorderFactory.createLineBorder(new Color(200, 200, 200, 100)));
        frame.getContentPane().add(scrollPane);
    }

    private void setupInputComponents() {
        // Input Field
        inputField.setBounds(20, 340, 540, 40);
        inputField.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        inputField.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(100, 100, 100)),
                BorderFactory.createEmptyBorder(5, 10, 5, 10)
        ));
        inputField.addActionListener(e -> processUserInput());
        frame.getContentPane().add(inputField);

        // Voice Button
        voiceButton.setFont(new Font("Segoe UI", Font.PLAIN, 20));
        voiceButton.setBounds(570, 340, 60, 40);
        voiceButton.setFocusPainted(false);
        voiceButton.setBorder(BorderFactory.createEmptyBorder());
        voiceButton.setContentAreaFilled(false);
        voiceButton.addActionListener(e -> toggleVoiceInput());
        frame.getContentPane().add(voiceButton);

        // Send Button
        JButton sendButton = new JButton("Send");
        sendButton.setBounds(640, 340, 120, 40);
        sendButton.setFont(new Font("Segoe UI", Font.BOLD, 14));
        sendButton.setBackground(new Color(70, 130, 180));
        sendButton.setForeground(Color.WHITE);
        sendButton.addActionListener(e -> processUserInput());
        frame.getContentPane().add(sendButton);
    }

    private void createMenuBar() {
        JMenuBar menuBar = new JMenuBar();
        menuBar.setBackground(new Color(50, 50, 50));
        menuBar.setForeground(Color.WHITE);

        // File Menu
        JMenu fileMenu = new JMenu("File");
        fileMenu.setForeground(Color.WHITE);
        JMenuItem exitItem = new JMenuItem("Exit");
        exitItem.addActionListener(e -> close());
        fileMenu.add(exitItem);

        // Apps Menu
        JMenu appsMenu = new JMenu("Apps");
        appsMenu.setForeground(Color.WHITE);
        String[] apps = {"Calculator", "Notepad", "Chrome", "Firefox", "VS Code", "Spotify"};
        for (String app : apps) {
            JMenuItem item = new JMenuItem(app);
            item.addActionListener(e -> launchApp(app));
            appsMenu.add(item);
        }

        // Help Menu
        JMenu helpMenu = new JMenu("Help");
        helpMenu.setForeground(Color.WHITE);
        JMenuItem commandsItem = new JMenuItem("Available Commands");
        commandsItem.addActionListener(e -> showAvailableCommands());
        helpMenu.add(commandsItem);

        menuBar.add(fileMenu);
        menuBar.add(appsMenu);
        menuBar.add(helpMenu);
        frame.setJMenuBar(menuBar);
    }

    private void processUserInput() {
        String input = inputField.getText().trim();
        if (!input.isEmpty()) {
            addToConversation("You: " + input);
            inputField.setText("");
            showProcessing(true);

            executor.execute(() -> {
                try {
                    String response = assistantEngine.processCommand(input);
                    SwingUtilities.invokeLater(() -> {
                        addToConversation("Glitch: " + response);
                        showProcessing(false);
                    });
                } catch (Exception e) {
                    SwingUtilities.invokeLater(() -> {
                        addToConversation("Glitch: ❌ Error processing command");
                        showProcessing(false);
                    });
                }
            });
        }
    }

    private void toggleVoiceInput() {
        isListening = !isListening;
        voiceButton.setText(isListening ? "🔴" : "🎤");

        if (isListening && assistantEngine != null) {
            assistantEngine.startListening();
            executor.schedule(() -> {
                String voiceCommand = assistantEngine.getVoiceInput();
                SwingUtilities.invokeLater(() -> {
                    if (voiceCommand != null && !voiceCommand.isEmpty()) {
                        inputField.setText(voiceCommand);
                        processUserInput();
                    }
                    isListening = false;
                    voiceButton.setText("🎤");
                });
            }, 2, TimeUnit.SECONDS);
        } else if (assistantEngine != null) {
            assistantEngine.stopListening();
        }
    }

    private void showProcessing(boolean isProcessing) {
        voiceButton.setEnabled(!isProcessing);
        inputField.setEnabled(!isProcessing);
    }

    private void launchApp(String appName) {
        executor.execute(() -> {
            String result = AppLauncher.openApp(appName);
            SwingUtilities.invokeLater(() -> addToConversation("Glitch: " + result));
        });
    }

    private void showAvailableCommands() {
        String commands = "Available Commands:\n\n"
                + "• System:\n"
                + "  - What time is it?\n"
                + "  - What's today's date?\n"
                + "  - Shutdown/Restart/Lock\n\n"
                + "• Applications:\n"
                + "  - Open [app name]\n"
                + "  - Start Chrome/Firefox/Word\n\n"
                + "• Media:\n"
                + "  - Play music\n"
                + "  - Volume up/down\n\n"
                + "• Fun:\n"
                + "  - Tell me a joke\n"
                + "  - Random quote\n\n"
                + "• Web:\n"
                + "  - Search [query]\n"
                + "  - Open YouTube/Facebook";

        JOptionPane.showMessageDialog(frame, commands, "Command Help", 
            JOptionPane.INFORMATION_MESSAGE);
    }

    public void addToConversation(String text) {
        SwingUtilities.invokeLater(() -> {
            conversationArea.append(text + "\n\n");
            conversationArea.setCaretPosition(conversationArea.getDocument().getLength());
        });
    }

    public void show() {
        frame.setVisible(true);
        SwingUtilities.invokeLater(() -> inputField.requestFocusInWindow());
    }

    public void close() {
        if (isListening && assistantEngine != null) {
            assistantEngine.stopListening();
        }

        executor.shutdown();
        try {
            if (!executor.awaitTermination(500, TimeUnit.MILLISECONDS)) {
                executor.shutdownNow();
            }
        } catch (InterruptedException e) {
            executor.shutdownNow();
            Thread.currentThread().interrupt();
        }

        frame.dispose();
    }
}
