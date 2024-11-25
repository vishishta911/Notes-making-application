import javax.swing.*;
import java.awt.event.*;
import java.sql.*;

public class AddNote extends JFrame {
    public AddNote() {
        setTitle("Add Note");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JLabel titleLabel = new JLabel("Title:");
        JTextField titleField = new JTextField(1);
        JLabel contentLabel = new JLabel("Content:");
        JTextArea contentArea = new JTextArea(15, 5);
        JButton saveButton = new JButton("Save");

        saveButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String title = titleField.getText();
                String content = contentArea.getText();
                try (Connection conn = DBConnection.connect();
                     PreparedStatement stmt = conn.prepareStatement("INSERT INTO notes (title, content) VALUES (?, ?)")) {
                    stmt.setString(1, title);
                    stmt.setString(2, content);
                    stmt.executeUpdate();
                    JOptionPane.showMessageDialog(null, "Note added successfully!");
                    //dispose();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(null, "Error saving note!");
                }
            }
        });

        setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));
        add(titleLabel);
        add(titleField);
        add(contentLabel);
        add(new JScrollPane(contentArea));
        add(saveButton);
        setVisible(true);
    }
}


