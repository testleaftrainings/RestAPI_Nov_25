package com.testleaf.makaia.rest.assured.api.client;

import java.util.logging.Logger;

import org.json.JSONObject;

import io.restassured.filter.Filter;
import io.restassured.filter.FilterContext;
import io.restassured.filter.log.LogDetail;
import io.restassured.response.Response;
import io.restassured.specification.FilterableRequestSpecification;
import io.restassured.specification.FilterableResponseSpecification;

public class RestAssuredListener implements Filter {

	@Override
	public Response filter(FilterableRequestSpecification requestSpec, FilterableResponseSpecification responseSpec,
			FilterContext ctx) {
		
		Logger logger = Logger.getLogger("RestAssuredListener");
		logger.info("");
		logger.warning("");
		logger.fine("");
		
		Response response = ctx.next(requestSpec, responseSpec);
		System.out.println("============ Request Log ============");
		System.out.printf("""
				    HTTP Method: %s
				    URI: %s
				    Base URI: %s
				    Base Path: %s
				    Headers: %s
				    Request Payload: %s
				    """, requestSpec.getMethod(), 
				         requestSpec.getURI(),
				         requestSpec.getBaseUri(), 
				         requestSpec.getBasePath(), 
				         requestSpec.getHeaders().asList().toString(), 
				         isRequestPayLoadNull(requestSpec.getBody()));
		System.out.println("=====================================");
		response.then().log().ifValidationFails(LogDetail.ALL, true);
		return response;
	}
	
	private Object isRequestPayLoadNull(Object body) {
		if(body != null) {
			return new JSONObject(body.toString());
		} else {
			return "NULL";
		}
	}

}