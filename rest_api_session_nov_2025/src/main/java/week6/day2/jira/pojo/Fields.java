package week6.day2.jira.pojo;

import com.google.gson.annotations.SerializedName;

public class Fields {

	private Project project;
	@SerializedName("issuetype")
	private IssueType issuetype;
	private String summary;

	public Project getProject() {
		return project;
	}

	public void setProject(Project project) {
		this.project = project;
	}

	public IssueType getIssueType() {
		return issuetype;
	}

	public void setIssueType(IssueType issuetype) {
		this.issuetype = issuetype;
	}

	public String getSummary() {
		return summary;
	}

	public void setSummary(String summary) {
		this.summary = summary;
	}

}