/**
	*	@FILE_NAME: Java Bean			
	*	@DEVELOPERS: Dean		
	*	@BRIEF_DESCRIPTION: Java Bean for all the comments created
	*/
	
package beans;

import java.io.Serializable;
public class Comment implements Serializable
{
	private String issueId, commenterUserName, userComment;
	
	public Comment()
	{
		this.issueId = "";
		this.commenterUserName = "";
		this.userComment = "";
	}
	

	
	public void setIssueId(String issueid)
	{
		this.issueId = issueId;
	}
	
	public void setCommenterUserName(String commenterUserName)
	{
		this.commenterUserName = commenterUserName;
	}
	
	public void setUserComment(String userComment)
	{
		this.userComment = userComment;
	}
		
	
	//getters

	
	public String getIssueId()
	{
		return issueId;
	}
	
	public String getCommenterUserName()
	{
		return commenterUserName;
	}
	
	public String getUserComment()
	{
		return userComment;
	}
	
	
}