package beans;

import java.io.Serializable;
public class Report implements Serializable
{
	private String title, description, reporter, category;
	
	public Report()
	{
		this.title = "";
		this.description = "";
		this.reporter = "";
		this.category = "";
	}
	
	//setters
	public void setTitle(String title)
	{
		this.title = title;
	}
	
	public void setDescription(String description)
	{
		this.description = description;
	}
	
	public void setReporter(String reporter)
	{
		this.reporter = reporter;
	}
	
	public void setCategory(String category)
	{
		this.category = category;
	}
	
	
	//Getters
	public String getTitle()
	{
		return title;
	}
	
	public String getDescription()
	{
		return description;
	}
	
	public String getReporter()
	{
		return reporter;
	}
	
	public String getCategory()
	{
		return category;
	}
}