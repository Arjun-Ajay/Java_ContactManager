package Contact_Management_System;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import javax.swing.ImageIcon;
import java.awt.Color;

public class MainApp 
{
    private contactManager cm = new contactManager();
    private JTable contactTable;
    private DefaultTableModel tableModel;

    public MainApp()
    {
        // Load contacts from CSV file
        cm.loadFromCSV();

        // Creating the main frame of the application
        JFrame frame = new JFrame("Contact Management System");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 400);
        frame.setLayout(new BorderLayout(10, 10));

        ImageIcon img=new ImageIcon("C:\\Users\\hp\\Downloads\\phone.png");
        frame.setIconImage(img.getImage());
        
        frame.getContentPane().setBackground(Color.gray);
        
        // Create table model
        String[] columns = {"Type", "Name", "Phone", "Email", "Details"};
        tableModel = new DefaultTableModel(columns, 0);
        updateTableData();
        contactTable = new JTable(tableModel);
        JScrollPane tableScrollPane = new JScrollPane(contactTable);


        // Create buttons
        JButton addButton = new JButton("Add Contact");
        addButton.addActionListener(e -> showAddContactDialog(frame));
        
        JButton editButton = new JButton("Edit Contact");
        editButton.addActionListener(e -> showEditContactDialog(frame));

        JButton deleteButton = new JButton("Delete Contact");
        deleteButton.addActionListener(e -> showDeleteContactDialog(frame));

        JButton exitButton = new JButton("Exit");
        exitButton.addActionListener(e -> {
            cm.saveToCSV();
            System.exit(0);
        });

        // Button panel
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));
        buttonPanel.add(addButton);
        buttonPanel.add(editButton);
        buttonPanel.add(deleteButton);
        buttonPanel.add(exitButton);

        // Add components to frame
        frame.add(tableScrollPane, BorderLayout.CENTER);
        frame.add(buttonPanel, BorderLayout.SOUTH);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    private void updateTableData() 
    {
        tableModel.setRowCount(0); // Clear table
        for (contact c : cm.getContacts()) 
        {
            String type = c instanceof personalContact ? "Personal" : "Business";
            String details = c instanceof personalContact ?
                    ((personalContact) c).getBday() + ", " + ((personalContact) c).getRelation() :
                    ((businessContact) c).getCompany() + ", " + ((businessContact) c).getJobTitle();
            tableModel.addRow(new Object[]{type, c.getName(), c.getPhoneNum(), c.getEmail(), details});
        }
    }

    private void showAddContactDialog(JFrame parent) {
        JDialog dialog = new JDialog(parent, "Add Contact", true);
        dialog.setLayout(new GridLayout(7, 2, 10, 10));
        dialog.setSize(300, 300);
        dialog.setLocationRelativeTo(parent);

        JComboBox<String> typeCombo = new JComboBox<>(new String[]{"Personal", "Business"});
        JTextField nameField = new JTextField(20);
        JTextField phoneField = new JTextField(20);
        JTextField emailField = new JTextField(20);
        JTextField field1 = new JTextField(20);
        JTextField field2 = new JTextField(20);

        JLabel field1Label = new JLabel("Birthday:");
        JLabel field2Label = new JLabel("Relationship:");
        typeCombo.addActionListener(e -> {
            String type = (String) typeCombo.getSelectedItem();
            field1Label.setText(type.equals("Personal") ? "Birthday:" : "Company:");
            field2Label.setText(type.equals("Personal") ? "Relationship:" : "Job Title:");
        });

        JButton addButton = new JButton("Add");
        addButton.addActionListener(e -> {
            String name = nameField.getText().trim();
            String phone = phoneField.getText().trim();
            String email = emailField.getText().trim();
            String f1 = field1.getText().trim();
            String f2 = field2.getText().trim();
            if (!name.isEmpty() && !phone.isEmpty() && !email.isEmpty()) {
                if (typeCombo.getSelectedItem().equals("Personal")) {
                    cm.addContact(new personalContact(name, phone, email, f1, f2));
                } else {
                    cm.addContact(new businessContact(name, phone, email, f1, f2));
                }
                updateTableData();
                dialog.dispose();
            } else {
                JOptionPane.showMessageDialog(dialog, "Name, Phone, and Email are required!", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        dialog.add(new JLabel("Contact Type:"));
        dialog.add(typeCombo);
        dialog.add(new JLabel("Name:"));
        dialog.add(nameField);
        dialog.add(new JLabel("Phone:"));
        dialog.add(phoneField);
        dialog.add(new JLabel("Email:"));
        dialog.add(emailField);
        dialog.add(field1Label);
        dialog.add(field1);
        dialog.add(field2Label);
        dialog.add(field2);
        dialog.add(new JLabel(""));
        dialog.add(addButton);

        dialog.setVisible(true);
    }

    private void showDeleteContactDialog(JFrame parent) {
        JDialog dialog = new JDialog(parent, "Delete Contact", true);
        dialog.setLayout(new GridLayout(3, 2, 10, 10));
        dialog.setSize(200, 150);
        dialog.setLocationRelativeTo(parent);

        JTextField nameField = new JTextField(20);
        JButton deleteButton = new JButton("Delete");
        deleteButton.addActionListener(e -> {
            String name = nameField.getText().trim();
            if (!name.isEmpty()) {
                cm.removeContact(name);
                updateTableData();
                dialog.dispose();
            } else {
                JOptionPane.showMessageDialog(dialog, "Name is required!", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        dialog.add(new JLabel("Contact Name:"));
        dialog.add(nameField);
        dialog.add(new JLabel(""));
        dialog.add(deleteButton);

        dialog.setVisible(true);
    }
    
    private void showEditContactDialog(JFrame parent) {
        JDialog dialog = new JDialog(parent, "Edit Contact", true);
        dialog.setLayout(new GridLayout(9, 2, 10, 10));
        dialog.setSize(300, 340);
        dialog.setLocationRelativeTo(parent);

        JTextField nameField = new JTextField(20);
        JButton loadButton = new JButton("Load Contact");
        JComboBox<String> typeCombo = new JComboBox<>(new String[]{"Personal", "Business"});
        JTextField newNameField = new JTextField(20);
        JTextField phoneField = new JTextField(20);
        JTextField emailField = new JTextField(20);
        JTextField field1 = new JTextField(20);
        JTextField field2 = new JTextField(20);

        JLabel field1Label = new JLabel("Birthday:");
        JLabel field2Label = new JLabel("Relationship:");
        typeCombo.addActionListener(e -> {
            String type = (String) typeCombo.getSelectedItem();
            field1Label.setText(type.equals("Personal") ? "Birthday:" : "Company:");
            field2Label.setText(type.equals("Personal") ? "Relationship:" : "Job Title:");
        });

        loadButton.addActionListener(e -> {
            String name = nameField.getText().trim();
            if (name.isEmpty()) {
                JOptionPane.showMessageDialog(dialog, "Please enter a contact name!", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
            boolean found = false;
            for (contact c : cm.getContacts()) {
                if (c.getName().equals(name)) { // Case-sensitive
                    newNameField.setText(c.getName());
                    phoneField.setText(c.getPhoneNum());
                    emailField.setText(c.getEmail());
                    if (c instanceof personalContact) {
                        typeCombo.setSelectedItem("Personal");
                        field1Label.setText("Birthday:");
                        field2Label.setText("Relationship:");
                        field1.setText(((personalContact) c).getBday());
                        field2.setText(((personalContact) c).getRelation());
                    } else {
                        typeCombo.setSelectedItem("Business");
                        field1Label.setText("Company:");
                        field2Label.setText("Job Title:");
                        field1.setText(((businessContact) c).getCompany());
                        field2.setText(((businessContact) c).getJobTitle());
                    }
                    found = true;
                    break;
                }
            }
            if (!found) {
                JOptionPane.showMessageDialog(dialog, "Contact not found! Name is case-sensitive.", "Error", JOptionPane.ERROR_MESSAGE);
                newNameField.setText("");
                phoneField.setText("");
                emailField.setText("");
                field1.setText("");
                field2.setText("");
            }
        });

        JButton updateButton = new JButton("Update");
        updateButton.addActionListener(e -> {
            String oldName = nameField.getText().trim();
            String newName = newNameField.getText().trim();
            String phone = phoneField.getText().trim();
            String email = emailField.getText().trim();
            String f1 = field1.getText().trim();
            String f2 = field2.getText().trim();
            if (!oldName.isEmpty() && !newName.isEmpty() && !phone.isEmpty() && !email.isEmpty()) {
                boolean exists = cm.getContacts().stream().anyMatch(c -> c.getName().equals(oldName));
                if (exists) {
                    contact updatedContact = typeCombo.getSelectedItem().equals("Personal") ?
                            new personalContact(newName, phone, email, f1, f2) :
                            new businessContact(newName, phone, email, f1, f2);
                    cm.updateContact(oldName, updatedContact);
                    updateTableData();
                    dialog.dispose();
                } else {
                    JOptionPane.showMessageDialog(dialog, "Contact not found! Name is case-sensitive.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            } else {
                JOptionPane.showMessageDialog(dialog, "All fields (except optional ones) are required!", "Error", JOptionPane.ERROR_MESSAGE);
            }
        });

        dialog.add(new JLabel("Enter Contact Name:"));
        dialog.add(nameField);
        dialog.add(new JLabel(""));
        dialog.add(loadButton);
        dialog.add(new JLabel("Contact Type:"));
        dialog.add(typeCombo);
        dialog.add(new JLabel("Name:"));
        dialog.add(newNameField);
        dialog.add(new JLabel("Phone:"));
        dialog.add(phoneField);
        dialog.add(new JLabel("Email:"));
        dialog.add(emailField);
        dialog.add(field1Label);
        dialog.add(field1);
        dialog.add(field2Label);
        dialog.add(field2);
        dialog.add(new JLabel(""));
        dialog.add(updateButton);

        dialog.setVisible(true);
    }
    
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new MainApp());
    }
}