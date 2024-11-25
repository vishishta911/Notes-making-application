import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class DeleteNote extends JFrame {
    public DeleteNote() {
        setTitle("Delete Note");
        setSize(300, 200);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JLabel idLabel = new JLabel("Note ID:");
        JTextField idField = new JTextField(15);
        JButton deleteButton = new JButton("Delete");
        deleteButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e){
                int id = Integer.parseInt(idField.getText());

                try (Connection con = DBConnection.connect();
                     PreparedStatement stmt = con.prepareStatement("DELETE FROM notes WHERE note_id = ?")) {
                    stmt.setInt(1, id);
                    int rows = stmt.executeUpdate();

                    if (rows > 0) {
                        JOptionPane.showMessageDialog(null, "Note deleted successfully!");
                        dispose();
                    } else {
                        JOptionPane.showMessageDialog(null, "Note not found!");
                    }
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        });

        setLayout(new BorderLayout());
        JPanel p = new JPanel();
        p.add(idLabel);
        p.add(idField);

        add(p, BorderLayout.CENTER);
        add(deleteButton,BorderLayout.SOUTH);
        
        setVisible(true);
    }
}
