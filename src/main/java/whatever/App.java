package whatever;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class App {
    static List<Group> groups = new ArrayList<Group>() {
        {
            add(new Group(1,"Red Hot Chili Peppers", "USA"));
            add(new Group(2,"Nirvana", "USA"));
            add(new Group(3,"Beatles", "UK"));
        }
    };

    public static void main(String[] args) {
        JFrame frame = new JFrame("Askisi3");
        JPanel panel = new JPanel(new GridLayout(25,5));

        final JTextField nameField = new JTextField();
        final JTextField countryField = new JTextField();

        panel.add(nameField);
        panel.add(countryField);

        JButton saveButton = new JButton("SAVE");
        saveButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String name = nameField.getText();
                String country = countryField.getText();
                System.out.println("Name: " + name + " Country: " + country);

                // Briskei to epomeno id
                int highestId = 0;
                for (Group g : groups) {
                    if (highestId < g.getId()) {
                        highestId = g.getId();
                    }
                }
                // Bazei to neo group sti lista
                groups.add(new Group(highestId + 1, name, country));
                System.out.println(groups);
                nameField.setText("");
                countryField.setText("");
            }
        });
        panel.add(saveButton);
        panel.add(new JSeparator());

        final JTextField deleteField = new JTextField();
        panel.add(deleteField);
        JButton deleteButton = new JButton("DELETE");
        deleteButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String groupName = deleteField.getText();
                for(int i = 0; i < groups.size(); i++) {
                    if (groups.get(i).getName().equals(groupName)) {
                        groups.remove(groups.get(i));
                    }
                }

                deleteField.setText("");
                System.out.println(groups);
            }
        });
        panel.add(deleteButton);
        panel.add(new JSeparator());

        final JTextField searchField = new JTextField();
        panel.add(searchField);
        JButton searchButton = new JButton("SEARCH");
        searchButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                String groupName = searchField.getText();

                for(Group g : groups) {
                    if (g.getName().equals(groupName)) {
                        System.out.println("Group found: " + g);
                    }
                }
                searchField.setText("");
            }
        });
        panel.add(searchButton);

        frame.add(panel);
        frame.setSize(800, 600);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}

class Group {
    int id;
    String name;
    String country;

    public Group(int id, String name, String country) {
        this.id = id;
        this.name = name;
        this.country = country;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "Group{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", country='" + country + '\'' +
                '}';
    }
}
