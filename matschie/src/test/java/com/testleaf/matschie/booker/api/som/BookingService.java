package com.testleaf.matschie.booker.api.som;

import com.testleaf.matchie.rest.assured.api.client.RestAssuredApiClient;
import com.testleaf.matschie.booker.serialization.pojos.Booking;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class BookingService {
	
	private static final String SERVICENAME = "/booking";
	private RestAssuredApiClient apiClient = new RestAssuredApiClient();
	private Response response;
	private RequestSpecBuilder requestSpecBuilder = new RequestSpecBuilder();
	
	public BookingService() {
		requestSpecBuilder.setBaseUri("https://restful-booker.herokuapp.com");
	}
	
	public void createNewBooking(Booking booking) {
		response = apiClient.post(requestSpecBuilder
				       .setContentType(ContentType.JSON)
				       , SERVICENAME, booking);
	}
	
	public void getBookingDetialsByBookingId(int id) {
		
	}
	
	public void updateExistingBookingDetials(int id) {
		
	}
	
	public void validateSuccessResponse() {
		
	}

}