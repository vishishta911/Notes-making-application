import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class NotesApp extends JFrame {
    public NotesApp() {
        setTitle("Notes Making Application");
        setSize(300, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new FlowLayout());

        JButton addNoteButton = new JButton("Add Note");
        JButton viewNotesButton = new JButton("View Notes");
        JButton editNoteButton = new JButton("Edit Note");
        JButton deleteNoteButton = new JButton("Delete Note");

        add(addNoteButton);
        add(viewNotesButton);
        add(editNoteButton);
        add(deleteNoteButton);

 
        addNoteButton.addActionListener(new ActionListener() {
               public void actionPerformed(ActionEvent e) {
                     new AddNote();
               }
        });

        viewNotesButton.addActionListener(new ActionListener() {
              public void actionPerformed(ActionEvent e) {
                     new ViewNotes();
              }
        });

        editNoteButton.addActionListener(new ActionListener() {
             public void actionPerformed(ActionEvent e) {
                    new EditNote();
             }
        });

        deleteNoteButton.addActionListener(new ActionListener() {
             public void actionPerformed(ActionEvent e) {
                    new DeleteNote();
                    //new Delete();
             }
        });

        setVisible(true);
    }

    public static void main(String[] args) {
        new NotesApp();
    }
}
