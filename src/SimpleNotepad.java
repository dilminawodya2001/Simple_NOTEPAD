import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.io.*;

public class SimpleNotepad extends JFrame {

    private JTextArea textArea;
    private JFileChooser fileChooser;
    private JLabel statusBar;

    public SimpleNotepad() {
        setTitle("Simple Notepad");
        setSize(600, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Create text area
        textArea = new JTextArea();
        textArea.setFont(new Font("Arial", Font.PLAIN, 14));
        add(new JScrollPane(textArea), BorderLayout.CENTER);

        // Create file chooser
        fileChooser = new JFileChooser();

        // Create status bar
        statusBar = new JLabel("Words: 0");
        add(statusBar, BorderLayout.SOUTH);

        // Add word count listener
        textArea.getDocument().addDocumentListener(new DocumentListener() {
            public void changedUpdate(DocumentEvent e) { updateWordCount(); }
            public void insertUpdate(DocumentEvent e) { updateWordCount(); }
            public void removeUpdate(DocumentEvent e) { updateWordCount(); }
        });

        createMenuBar();
        updateWordCount();
    }

    private void createMenuBar() {
        JMenuBar menuBar = new JMenuBar();

        // File Menu
        JMenu fileMenu = new JMenu("File");
        JMenuItem newItem = new JMenuItem("New");
        JMenuItem openItem = new JMenuItem("Open");
        JMenuItem saveItem = new JMenuItem("Save");
        JMenuItem exitItem = new JMenuItem("Exit");

        newItem.addActionListener(e -> textArea.setText(""));
        openItem.addActionListener(e -> openFile());
        saveItem.addActionListener(e -> saveFile());
        exitItem.addActionListener(e -> System.exit(0));

        fileMenu.add(newItem);
        fileMenu.add(openItem);
        fileMenu.add(saveItem);
        fileMenu.add(exitItem);

        // Edit Menu
        JMenu editMenu = new JMenu("Edit");
        JMenuItem cutItem = new JMenuItem("Cut");
        JMenuItem copyItem = new JMenuItem("Copy");
        JMenuItem pasteItem = new JMenuItem("Paste");

        cutItem.addActionListener(e -> textArea.cut());
        copyItem.addActionListener(e -> textArea.copy());
        pasteItem.addActionListener(e -> textArea.paste());

        editMenu.add(cutItem);
        editMenu.add(copyItem);
        editMenu.add(pasteItem);

        // Font Menu
        JMenu fontMenu = new JMenu("Font");


        JMenuItem arialFont = new JMenuItem("Arial");
        JMenuItem courierFont = new JMenuItem("Courier New");


        JMenuItem size12 = new JMenuItem("Size 12");
        JMenuItem size16 = new JMenuItem("Size 16");

        arialFont.addActionListener(e -> changeFont("Arial", textArea.getFont().getSize()));
        courierFont.addActionListener(e -> changeFont("Courier New", textArea.getFont().getSize()));
        size12.addActionListener(e -> changeFont(textArea.getFont().getName(), 12));
        size16.addActionListener(e -> changeFont(textArea.getFont().getName(), 16));

        fontMenu.add(arialFont);
        fontMenu.add(courierFont);
        fontMenu.addSeparator();
        fontMenu.add(size12);
        fontMenu.add(size16);

        // Help Menu
        JMenu helpMenu = new JMenu("Help");
        JMenuItem aboutItem = new JMenuItem("About");

        aboutItem.addActionListener(e -> showAbout());
        helpMenu.add(aboutItem);

        menuBar.add(fileMenu);
        menuBar.add(editMenu);
        menuBar.add(fontMenu);
        menuBar.add(helpMenu);
        setJMenuBar(menuBar);
    }

    private void updateWordCount() {
        String text = textArea.getText().trim();
        int words = text.isEmpty() ? 0 : text.split("\\s+").length;
        statusBar.setText("Words: " + words);
    }

    private void changeFont(String fontName, int fontSize) {
        textArea.setFont(new Font(fontName, Font.PLAIN, fontSize));
    }

    private void showAbout() {
        JOptionPane.showMessageDialog(this,
                "Simple Notepad v1.0\nA basic text editor\n\nName : B.G.D Nawodya\nID : s16629",
                "About", JOptionPane.INFORMATION_MESSAGE);
    }

    private void openFile() {
        int option = fileChooser.showOpenDialog(this);
        if (option == JFileChooser.APPROVE_OPTION) {
            File file = fileChooser.getSelectedFile();
            try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
                textArea.read(reader, null);
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(this, "Failed to open file: " + ex.getMessage(),
                        "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void saveFile() {
        int option = fileChooser.showSaveDialog(this);
        if (option == JFileChooser.APPROVE_OPTION) {
            File file = fileChooser.getSelectedFile();
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
                textArea.write(writer);
                JOptionPane.showMessageDialog(this, "File saved successfully!",
                        "Success", JOptionPane.INFORMATION_MESSAGE);
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(this, "Failed to save file: " + ex.getMessage(),
                        "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new SimpleNotepad().setVisible(true);
        });
    }
}