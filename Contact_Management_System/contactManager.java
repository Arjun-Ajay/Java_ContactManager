package Contact_Management_System;

import java.util.List;
import java.util.ArrayList;
import java.io.*;

public class contactManager 
{
    private List<contact> myContacts = new ArrayList<>();
    private static final String CSV_FILE = "contacts.csv";

    // Used to add a new contact to the contact list
    public void addContact(contact newContact)
    {
        myContacts.add(newContact);
    }

    // Used to remove a contact from the contact list
    public void removeContact(String name)
    {
        myContacts.removeIf(givenContact -> givenContact.getName().equalsIgnoreCase(name));
    }

    // Used to return the contact list
    public List<contact> getContacts()
    {
        return myContacts;
    }
    
    // Used to update the contact already saved in the contact list
    public void updateContact(String oldName, contact updatedContact)
    {
        for (int i = 0; i < myContacts.size(); i++) 
        {
            if (myContacts.get(i).getName().equals(oldName)) 
            {
                myContacts.set(i, updatedContact);
                return;
            }
        }
    }
    
    // Used to save the contact list to the CSV file
    public void saveToCSV()
    {
        try (PrintWriter writer = new PrintWriter(new FileWriter(CSV_FILE))) 
        {
            for (contact c : myContacts)
            {
                if (c instanceof personalContact) 
                {
                    personalContact pc = (personalContact) c;
                    writer.println("Personal," + pc.getName() + "," + pc.getPhoneNum() + "," + pc.getEmail() + "," + pc.getBday() + "," + pc.getRelation());
                } 
                else if (c instanceof businessContact) 
                {
                    businessContact bc = (businessContact) c;
                    writer.println("Business," + bc.getName() + "," + bc.getPhoneNum() + "," + bc.getEmail() + "," + bc.getCompany() + "," + bc.getJobTitle());
                }
            }
        } catch (IOException e) {
            System.err.println("Error saving to CSV: " + e.getMessage());
        }
    }

    // Used to load contacts which are saved on the CSV file.
    public void loadFromCSV() 
    {
        myContacts.clear();
        try (BufferedReader reader = new BufferedReader(new FileReader(CSV_FILE)))
        {
            String line;
            while ((line = reader.readLine()) != null) 
            {
                String[] parts = line.split(",", -1);
                if (parts.length < 6) continue; // Skip invalid lines
                String type = parts[0];
                String name = parts[1];
                String phone = parts[2];
                String email = parts[3];
                String field1 = parts[4];
                String field2 = parts[5];
                if (type.equals("Personal"))
                {
                    myContacts.add(new personalContact(name, phone, email, field1, field2));
                }
                else if (type.equals("Business")) 
                {
                    myContacts.add(new businessContact(name, phone, email, field1, field2));
                }
            }
        } catch (FileNotFoundException e) 
        {} catch (IOException e) {
            System.err.println("Error loading from CSV: " + e.getMessage());
        }
    }
}