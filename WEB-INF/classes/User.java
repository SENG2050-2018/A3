/**
	*	@FILE_NAME:			
	*	@DEVELOPERS:		
	*	@BRIEF_DESCRIPTION:	
	*/
package beans;

import java.io.Serializable;
public class User implements Serializable
{
	private String userName, firstName, surname, email, contactNumber;
	
	public User()
	{
		this.userName = "";
		this.firstName = "";
		this.surname = "";
		this.email = "";
		this.contactNumber = "";
	}
	
	//setters
	public void setUserName(String userName)
	{
		this.userName = userName;
	}
	
	public void setFirstName(String firstName)
	{
		this.firstName = firstName;
	}
	
	public void setSurname(String surname)
	{
		this.surname = surname;
	}
	
	public void setEmail(String email)
	{
		this.email = email;
	}
	
	public void setContactNumber(String contactNumber)
	{
		this.contactNumber = contactNumber;
	}
	
	
	//getters
	public String getUserName()
	{
		return userName;
	}
	
	public String getFirstName()
	{
		return firstName;
	}
	
	public String getSurname()
	{
		return surname;
	}
	
	public String getEmail()
	{
		return email;
	}
	
	public String getContactNumber()
	{
		return contactNumber;
	}
	
}