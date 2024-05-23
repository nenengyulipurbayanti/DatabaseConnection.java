// PersonGUI.java
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.List;

public class PersonGUI extends JFrame {
    private JTextField nameField;
    private JTextField ageField;
    private JTextArea personListArea;

    public PersonGUI() {
        setTitle("Person Database");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 400);
        setLocationRelativeTo(null);

        // Panel untuk input data
        JPanel inputPanel = new JPanel(new GridLayout(3, 2));
        inputPanel.add(new JLabel("Name:"));
        nameField = new JTextField();
        inputPanel.add(nameField);
        inputPanel.add(new JLabel("Age:"));
        ageField = new JTextField();
        inputPanel.add(ageField);
        JButton saveButton = new JButton("Save");
        saveButton.addActionListener(new SaveButtonListener());
        inputPanel.add(saveButton);

        // Panel untuk menampilkan daftar data
        JPanel listPanel = new JPanel(new BorderLayout());
        listPanel.add(new JLabel("Person List"), BorderLayout.NORTH);
        personListArea = new JTextArea();
        personListArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(personListArea);
        listPanel.add(scrollPane, BorderLayout.CENTER);

        // Membuat layout utama
        Container container = getContentPane();
        container.setLayout(new BorderLayout());
        container.add(inputPanel, BorderLayout.NORTH);
        container.add(listPanel, BorderLayout.CENTER);

        // Memuat data awal dari database
        loadPersonList();
    }

    private void loadPersonList() {
        try {
            List<Person> persons = PersonDAO.readPersons();
            StringBuilder sb = new StringBuilder();
            for (Person person : persons) {
                sb.append("ID: ").append(person.getId())
                        .append(", Name: ").append(person.getName())
                        .append(", Age: ").append(person.getAge())
                        .append("\n");
            }
            personListArea.setText(sb.toString());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private class SaveButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String name = nameField.getText();
            int age = Integer.parseInt(ageField.getText());
            Person person = new Person(0, name, age);

            try {
                PersonDAO.createPerson(person);
                nameField.setText("");
                ageField.setText("");
                loadPersonList();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }
}