import java.awt.*;
import javax.swing.*;
import java.awt.event.*;
import java.sql.*;


public class EditNote extends JFrame {
    public EditNote() {
        setTitle("Edit Note");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JLabel idLabel = new JLabel("Note ID:");
        JTextField idField = new JTextField(1);
        JLabel titleLabel = new JLabel("New Title:");
        JTextField titleField = new JTextField(5);
        JLabel contentLabel = new JLabel("New Content:");
        JTextArea contentArea = new JTextArea(5, 20);
        JButton updateButton = new JButton("Update");
                
        updateButton.addActionListener(new ActionListener() {
              public void actionPerformed(ActionEvent e) {
                   int id = Integer.parseInt(idField.getText());
                   String title = titleField.getText();
                   String content = contentArea.getText();

                   try (Connection con = DBConnection.connect();
                       PreparedStatement stmt = con.prepareStatement("UPDATE notes SET title = ?, content = ? WHERE note_id = ?")) {
                       stmt.setString(1, title);
                       stmt.setString(2, content);
                       stmt.setInt(3, id);
                       int rows = stmt.executeUpdate();

                       if (rows > 0) {
                           JOptionPane.showMessageDialog(null, "Note updated successfully!");
                           dispose();
                       } else {
                           JOptionPane.showMessageDialog(null, "Note ID not found!");
                       }
                   } catch (SQLException ex) {
                        ex.printStackTrace();
                   }
              }
        });
        setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS));
        //setLayout(new FlowLayout(FlowLayout.LEFT));
        add(idLabel);
        add(idField);
        add(titleLabel);
        add(titleField);
        add(contentLabel);
        add(new JScrollPane(contentArea));
        add(updateButton);
        setVisible(true);
    }
}
