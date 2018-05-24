package beans;

import java.io.Serializable;
public class Report implements Serializable
{
	private String id, title, description, reporter, category, state, reported, resolved, resolution, internalAccess, altBrowser, pcRestart; //Since there is no calculating these should all be strings
	
	public Report()
	{
		this.id = "";
		this.title = "";
		this.description = "";
		this.reporter = "";
		this.category = "";
		this.state = "";
		this.reported = "";
		this.resolved = "";
		this.resolution = "";
		this.internalAccess = "";
		this.altBrowser = "";
		this.pcRestart = "";
	}
	
	//setters
	public void setId(String id)
	{
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
	
	public void setReporter(String reporter)
	{
		this.reporter = reporter;
	}
	
	public void setCategory(String category)
	{
		this.category = category;
	}
	
	public void setState(String state)
	{
		this.state = state;
	}
	
	public void setReported(String reported)
	{
		this.reported = reported;
	}
	
	public void setResolved(String resolved)
	{
		this.resolved = resolved;
	}
	
	public void setResolution(String resolution)
	{
		this.resolution = resolution;
	}
	
	public void setInternalAccess(String internalAccess)
	{
		this.internalAccess = internalAccess;
	}
	
	public void setAltBrowser(String altBrowser)
	{
		this.altBrowser = altBrowser;
	}
	
	public void setPcRestart(String pcRestart)
	{
		this.pcRestart = pcRestart;
	}
	
	//Getters
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
	
	public String getReporter()
	{
		return reporter;
	}
	
	public String getCategory()
	{
		return category;
	}
	
	public String getState()
	{
		return state;
	}
	
	public String getReported()
	{
		return reported;
	}
	
	public String getResolved()
	{
		return resolved;
	}
	
	public String getResolution()
	{
		return resolution;
	}
	
	public String getInternalAccess()
	{
		return internalAccess;
	}
	
	public String getAltBrowser()
	{
		return altBrowser;
	}
	
	public String getPcRestart()
	{
		return pcRestart;
	}
}