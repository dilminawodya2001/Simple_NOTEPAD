# Mini Project: Simple Notepad


A simple text editor built using **Java Swing**, inspired by the classic Windows Notepad. This mini project demonstrates core GUI concepts like menus, file operations, document listeners, and UI components.

> ✅ Built with **Java 8+** | 🖥️ Desktop Application | 💡 Great for learning Java GUI programming

---

## 📌 Features

- ✍️ **Text editing area** with scroll support
- 📂 **File operations**: Open, Save, Exit
- ✂️ **Edit menu**: Cut, Copy, Paste
- 🎨 **Font customization**: Change font type (Arial, Courier New) and size (12, 16)
- 📊 **Real-time word count** displayed in status bar
- 💬 **About dialog** with version info

---

## 🔧 Components Used

| Component         | Purpose |
|-------------------|--------|
| `JFrame`          | Main window container |
| `JTextArea`       | Text input area |
| `JScrollPane`     | Scrollable view for long texts |
| `JMenuBar`, `JMenu`, `JMenuItem` | Menu system (File, Edit, Font, Help) |
| `JFileChooser`    | Open and save files |
| `DocumentListener`| Track text changes for word count |

---

## 🚀 How to Run

### Prerequisites
- Java JDK 8 or higher installed
- A basic IDE (e.g., IntelliJ IDEA, Eclipse) or command-line tools

### Steps

1. **Save the code** as `SimpleNotepad.java`
2. **Compile**:
   ```bash
   javac SimpleNotepad.java
   ```