package week6.day2;

import java.io.File;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.mapper.ObjectMapperType;
import week6.day2.jira.pojo.CreateJiraIssue;
import week6.day2.jira.pojo.Fields;
import week6.day2.jira.pojo.IssueType;
import week6.day2.jira.pojo.Project;

public class CreateBugIssueWithAttachment {
	
	CreateJiraIssue createIssue = new CreateJiraIssue();
	Fields fields = new Fields();
	Project project = new Project();
	IssueType issueType = new IssueType();
	String issueId;
	
	@Test(priority = 1)
	public void createNewBugTicket() {
		project.setId("10001");
		issueType.setId("10008");
		fields.setProject(project);
		fields.setIssueType(issueType);
		fields.setSummary("Bug ticket from restassured with attachement");
		createIssue.setFields(fields);
		issueId = RestAssured.given()
        .baseUri("https://karthistestlab.atlassian.net")
        .basePath("/rest/api/3")
        .log().all()
        .auth()
        .preemptive()
        .basic("karthis.testlab@gmail.com", "<API-TOKEN>")
        .contentType(ContentType.JSON)
        .when()
        .body(createIssue, ObjectMapperType.GSON)
        .post("/issue")
        .then()
        .log().ifValidationFails(LogDetail.ALL)
        .assertThat()
        .statusCode(201)
        .contentType(ContentType.JSON)
        .extract()
        .jsonPath()
        .getString("id");
		System.out.println(issueId);
	}
	
	@Test(priority = 2)
	public void attachFileIntoIssue() {
		RestAssured.given()
        .baseUri("https://karthistestlab.atlassian.net")
        .basePath("/rest/api/3")
        .log().all()
        .auth()
        .preemptive()
        .basic("karthis.testlab@gmail.com", "<API-TOKEN>")
        .contentType(ContentType.MULTIPART)
        .header("X-Atlassian-Token", "no-check")
        .pathParam("issueIdOrKey", issueId)
        .when()
        .multiPart(new File("./json-schema/response/fakerapi-response.json"))
        .post("/issue/{issueIdOrKey}/attachments")
        .then()
        .log().ifValidationFails(LogDetail.ALL)
        .assertThat()
        .statusCode(200)
        .contentType(ContentType.JSON);
	}

}