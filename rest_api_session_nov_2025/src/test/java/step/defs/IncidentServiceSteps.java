package step.defs;

import org.hamcrest.Matchers;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class IncidentServiceSteps {
	
	// Set all your precondition value
	RequestSpecBuilder requestSpecBuilder = new RequestSpecBuilder();
	Response response;

	@Given("User should set base uri {string} of the service now table api")
	public void user_should_set_base_uri_of_the_service_now_table_api(String baseUri) {
		requestSpecBuilder.setBaseUri(baseUri);
	}

	@Given("user should set the base path {string} of the service now table api")
	public void user_should_set_the_base_path_of_the_service_now_table_api(String basePath) {
		requestSpecBuilder.setBasePath(basePath);
	}

	@Given("user should set username as {string} and password as {string} for the service now api")
	public void user_should_set_username_as_and_password_as_for_the_service_now_api(String username, String password) {
		requestSpecBuilder.setAuth(RestAssured.basic(username, password));
	}

	@When("user should hit the get method of the incident table api")
	public void user_should_hit_the_get_method_of_the_incident_table_api() {
		response = RestAssured.given()
		           .spec(requestSpecBuilder.build())
		           .get("/incident");		 
	}

	@Then("user should see the success status code as a {int}")
	public void user_should_see_the_success_status_code_as_a(Integer statusCode) {
		response.then().assertThat().statusCode(statusCode);
	}

	@Then("user should see the success line as a {string}")
	public void user_should_see_the_success_line_as_a(String statusLine) {
		response.then().assertThat().statusLine(Matchers.containsString(statusLine));
	}

	@Then("user should see the respone as a {string} format")
	public void user_should_see_the_respone_as_a_format(String responseFormat) {
		if(responseFormat.equalsIgnoreCase("JSON")) {
			response.then().assertThat().contentType(ContentType.JSON);
		} else if(responseFormat.equalsIgnoreCase("XML")) {
			response.then().assertThat().contentType(ContentType.XML);
		} else {
			throw new RuntimeException("Servicenow table api response format should be JSON or XML");
		}
	}
	
	@Given("user should set header key as {string} and value as {string}")
	public void user_should_set_header_key_as_and_value_as(String key, String value) {
	   requestSpecBuilder.addHeader(key, value);
	}
	
	@Given("user should give the record sysId {string} to fetch single record")
	public void user_should_give_the_record_sys_id_to_fetch_single_record(String sysId) {
	    requestSpecBuilder.addPathParam("sys_id", sysId);
	}
	
	@When("user should hit the get method of the incident table api to fetch single record")
	public void user_should_hit_the_get_method_of_the_incident_table_api_to_fetch_single_record() {
	    response = RestAssured.given()
	    		              .spec(requestSpecBuilder.build())
	    		              .get("/incident/{sys_id}");
	}

}