package com.testleaf.makaia.api.design;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.response.Response;

public interface ApiClient {

	public Response get(RequestSpecBuilder requestSpecBuilder, String endPoint);

	public Response post(RequestSpecBuilder requestSpecBuilder, String endPoint, Object requestPayload);

	public Response put(RequestSpecBuilder requestSpecBuilder, String endPoint, Object requestPayload);

	public Response patch(RequestSpecBuilder requestSpecBuilder, String endPoint, Object requestPayload);

	public Response delete(RequestSpecBuilder requestSpecBuilder, String endPoint);

}