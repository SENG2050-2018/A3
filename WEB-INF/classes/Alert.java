package beans;

import java.io.Serializable;
public class Alert implements Serializable
{
	private String id, title, description;
	
	public Alert()
	{
		this.id = id;
		this.title = "";
		this.description = "";
	}
	
	// Setters.
	public void setId(String id){
		this.id = id;
	}
	
	public void setTitle(String title)
	{
		this.title = title;
	}
	
	public void setDescription(String description)
	{
		this.description = description;
	}
	
	// Getters.
	public String getId()
	{
		return id;
	}
	
	public String getTitle()
	{
		return title;
	}
	
	public String getDescription()
	{
		return description;
	}
	
}