package Contact_Management_System;
import java.util.List;
import java.util.ArrayList;

public class contactManager
{
	private List<contact> myContacts=new ArrayList<>();
	
	public void addContact(contact newContact)
	{
		myContacts.add(newContact);
		System.out.println("Contact has been added");
	}
	
	public void removeContact(String name)
	{
		myContacts.removeIf(givenContact-> givenContact.getName().equals(name));
	}
	
	public void showContacts()
	{
		if (myContacts.isEmpty())
		{
			System.out.println("No contacts available!!!");
		}
		else
		{
			System.out.println("Here are the available contacts:\n");
			for (contact i:myContacts)
			{
				System.out.println(i);
			}
		}
	}
}
