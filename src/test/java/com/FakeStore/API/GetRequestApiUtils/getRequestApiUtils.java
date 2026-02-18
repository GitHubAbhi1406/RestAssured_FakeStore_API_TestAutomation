package com.FakeStore.API.GetRequestApiUtils;

import io.restassured.response.Response;
import static io.restassured.RestAssured.*;

public class getRequestApiUtils{
	
	public static Response getResponseByGET(String endpoint) {
		
		return given().
				log().all().
				when().
				get(endpoint);
	}
	
	public static Response getResponseById(String endpoint, int id) {
		
		return given().
				log().all().
					when().
					pathParam("id", id)
						.get(endpoint);
	}
}