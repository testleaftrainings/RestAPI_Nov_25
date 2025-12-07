package steps.def.som;

import java.util.List;
import java.util.Map;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import servinow.services.IncidentServiceV2;
import week3.day2.CreateRecordPayload;

public class IncidentServiceSteps {
	
	// Set all your precondition value
	RequestSpecBuilder requestSpecBuilder = new RequestSpecBuilder();
	IncidentServiceV2 incidentService = new IncidentServiceV2();
	CreateRecordPayload payload = new CreateRecordPayload();

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
		incidentService.getRecords(requestSpecBuilder);		 
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
	    incidentService.getRecord(requestSpecBuilder);
	}
	
	@Then("user should see the success response with the expected value")
	public void user_should_see_the_success_response_with_the_expected_value(DataTable dataTable) {
		List<Map<String, String>> maps = dataTable.asMaps();
		for (Map<String, String> map : maps) {
			incidentService.validateResponse(Integer.parseInt(map.get("statusCode")), map.get("statusLine"), map.get("responseFormat"));			
		}
	}
	
	@When("user enters the short description value as {string} in the request body")
	public void user_enters_the_short_description_value_as_in_the_request_body(String shortDescription) {
		payload.setShortDescription(shortDescription);
	}
	
	@When("user enters the categoty value as {string} in the request payload")
	public void user_enters_the_categoty_value_as_in_the_request_payload(String category) {
	    payload.setCategory(category);
	}
	
	@When("user should hit the post method of incident table api to create new record")
	public void user_should_hit_the_post_method_of_incident_table_api_to_create_new_record() {
	    incidentService.createNewRecord(requestSpecBuilder, payload);    		              
	}
	
	@When("user should create request payload based on the given data")
	public void user_should_create_request_payload_based_on_the_given_data(DataTable dataTable) {
	    List<List<String>> lists = dataTable.asLists();
	    for (List<String> list : lists) {
	    	payload.setShortDescription(list.get(0));
			payload.setCategory(list.get(1));
		}
	}

}