package Contact_Management_System;

public class businessContact extends contact
{
	private String company;
	private String jobTitle;
	
	//Constructor
	public businessContact(String name,String phoneNum,String email,String company, String jobTitle)
	{
		super(name,phoneNum,email);
		this.company=company;
		this.jobTitle=jobTitle;
	}
	
	public String getCompany()
	{
        return company;
    }

    public String getJobTitle()
    {
        return jobTitle;
    }
}
