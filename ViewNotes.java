import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.sql.*;

public class ViewNotes extends JFrame {
    public ViewNotes() {
        setTitle("View Notes");
        setSize(500, 400);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        String[] columnNames = {"ID", "Title", "Content", "Timestamp"};
        DefaultTableModel model = new DefaultTableModel(columnNames, 0);
        JTable table = new JTable(model);

        try (Connection conn = DBConnection.connect();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT * FROM notes")) {

             while (rs.next()) {
                int id = rs.getInt("note_id");
                String title = rs.getString("title");
                String content = rs.getString("content");
                String timestamp = rs.getString("timestamp");
                model.addRow(new Object[]{id, title, content, timestamp});
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        add(new JScrollPane(table));
        setVisible(true);
    }
}
