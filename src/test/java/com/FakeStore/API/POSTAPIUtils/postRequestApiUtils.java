package com.FakeStore.API.POSTAPIUtils;

import java.io.File;
import static io.restassured.RestAssured.*;
import io.restassured.response.Response;

public class postRequestApiUtils{
	
	public static Response postRequest(String endpoint, File requestbody) {
		
		return given().
				log().all().
				contentType("application/json").
					when().
					body(requestbody).
						post(endpoint);
	}
}