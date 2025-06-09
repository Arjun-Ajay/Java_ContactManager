package Contact_Management_System;

public class contact 
{
	private String name;
	private String phoneNum;
	private String email;
	
	public contact(String name, String phoneNum,String email)
	{
		this.name=name;
		this.phoneNum=phoneNum;
		this.email=email;
	}
	
	public String getName()
	{
		return name;
	}
	
	public String getPhoneNum()
	{
		return phoneNum;
	}
	
	public String getEmail()
	{
		return email;
	}
}