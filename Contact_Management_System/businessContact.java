package Contact_Management_System;

public class businessContact extends contact
{
	private String company;
	private String jobTitle;
	
	public businessContact(String name,String phoneNum,String email,String company, String jobTitle)
	{
		super(name,phoneNum,email);
		this.company=company;
		this.jobTitle=jobTitle;
	}
	
	@Override
	public String getDetails() 
	{
        return "[Business] Name: " + getName() +" | Phone: " + getPhoneNum() +" | Email: " + getEmail() +" | Company: " + company +" | Job Title: " + jobTitle;
	}
}
