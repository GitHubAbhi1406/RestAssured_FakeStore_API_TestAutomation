package com.FakeStore.API.POSTRequestTests;

import java.io.File;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import com.FakeStore.API.BaseFile.BaseTest;
import com.FakeStore.API.POSTAPIUtils.postRequestApiUtils;
import com.FakeStore.API.Routes.Endpoints;
import io.restassured.response.Response;

public class checkAuthenticationTest extends BaseTest{
	
	Response res;
	
	@BeforeClass
	public void logResponse() {
		
		res = postRequestApiUtils.postRequest(Endpoints.authentication, new File("src/test/resources/AuthDetails/authDetails.json"));
	}
	
	@Test(priority = 1)
	public void logResponseBody() {
		res.then().log().all();
	}
	
	@Test(priority = 2)
	public void assertResponseCode() {
		 Assert.assertEquals(res.getStatusCode(), 201);
	}
	
	@Test(priority = 3)
	public void checkResponseHeaders() {
		Assert.assertEquals(res.getHeader("Content-Type"), "application/json; charset=utf-8");
	}
	
	@Test(priority = 4)
	public void validateResponsebody() {
		String token = res.jsonPath().getString("token");
		Assert.assertTrue(token.length() > 0, "Unable to extract token");
	}
	
	@Test(priority = 5)
	public void validateResponseTime() {
		Assert.assertTrue(res.getTime() < 2000, "Response time is not within accetable limits");
	}
	
	@Test(priority = 6)
	public void validateSchema() {
		Assert.assertTrue(res.jsonPath().getMap("$").containsKey("token"));
	}
}