package Contact_Management_System;

public class personalContact extends contact
{
	private String bday;
	private String relation;
	
	public personalContact(String name,String phoneNum,String email,String bday,String relation)
	{
		super(name,phoneNum,email);
		this.bday=bday;
		this.relation=relation;
	}
	
	@Override
	public String getDetails()
	{
		return "[Personal] Name: " + getName() +" | Phone: " + getPhoneNum() +" | Email: " + getEmail() +" | Birthday: " + bday +" | Relationship: " + relation;
	}
}
