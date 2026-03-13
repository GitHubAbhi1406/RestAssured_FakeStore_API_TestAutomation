package com.FakeStore.API.PUTRequestApiUtils;

import java.io.File;
import static io.restassured.RestAssured.*;
import io.restassured.response.Response;

public class putRequestApiUtils{
	
	public static Response putRequestMethod(String endpoint, File requestBody, int id) {
		
		return given().
				header("Content-Type", "application/json")
				.pathParam("id", id)
				.body(requestBody).
					when().
					put(endpoint);
	}
}