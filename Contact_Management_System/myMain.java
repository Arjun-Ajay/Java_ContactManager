package Contact_Management_System;
import java.util.Scanner;
public class myMain 
{
	public static void main(String[] args)
	{
		Scanner sc=new Scanner(System.in);
		contactManager cm=new contactManager();
		int flag=1;
		
		while (flag==1)
		{
			System.out.printf("WHAT WOULD YOU LIKE TO DO (use 1-4):\n1 -> Add a new contact\n2 -> Delete a contact\n3 -> View all contacts\n4 -> EXIT\nEnter your choice: ");
			int choice=sc.nextInt();
			
			if(choice==1)
			{
		        System.out.print("Choose your contact type (1-Personal, 2-Business): ");
		        int type = sc.nextInt();
		        sc.nextLine();
		
		        System.out.print("Name: ");
		        String name = sc.nextLine();
		        System.out.print("Phone: ");
		        String phone = sc.nextLine();
		        System.out.print("Email: ");
		        String email = sc.nextLine();
		
		        if (type==1)
		        {
		            System.out.print("Birthday: ");
		            String birthday = sc.nextLine();
		            System.out.print("Relationship: ");
		            String rel = sc.nextLine();
		            cm.addContact(new personalContact(name, phone, email, birthday, rel));
		        } 
		        else 
		        {
		            System.out.print("Company: ");
		            String company = sc.nextLine();
		            System.out.print("Job Title: ");
		            String job = sc.nextLine();
		            cm.addContact(new businessContact(name, phone, email, company, job));
		        }
		   	}
			
			else if (choice==2)
			{
				System.out.println("Enter the name of contact to be deleted: ");
				sc.nextLine();
				String cName=sc.nextLine();
				cm.removeContact(cName);
				System.out.println(cName+" has been removed from contacts");
			}
			
			else if (choice==3)
			{
				cm.showContacts();
			}
			
			else if (choice==4)
			{
				flag=0;
			}
			
			System.out.printf("--------------------------------\n");
		}
		System.out.println("Program Stopping!!!");
		System.out.printf("--------------------------------\n");
	}

}
