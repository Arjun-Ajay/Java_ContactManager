package Contact_Management_System;

public class personalContact extends contact
{
	private String bday;
	private String relation;
	
	//Constructor
	public personalContact(String name,String phoneNum,String email,String bday,String relation)
	{
		super(name,phoneNum,email);
		this.bday=bday;
		this.relation=relation;
	}
	
	// Getter functions
	public String getBday()
	{
        return bday;
    }

    public String getRelation() 
    {
        return relation;
    }
}
