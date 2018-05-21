package beans;

import java.io.Serializable;
public class Alert implements Serializable
{
	private String title, description;
	
	public Alert()
	{
		this.title = "";
		this.description = "";
	}
	
	public void setTitle(String title)
	{
		this.title = title;
	}
	
	public void setDescription(String description)
	{
		this.description = description;
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