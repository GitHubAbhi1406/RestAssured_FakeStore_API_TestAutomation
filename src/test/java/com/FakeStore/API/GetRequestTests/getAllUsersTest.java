package com.FakeStore.API.GetRequestTests;

import java.io.File;
import java.util.HashMap;
import java.util.Map;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import com.FakeStore.API.BaseFile.BaseTest;
import com.FakeStore.API.GetRequestApiUtils.getRequestApiUtils;
import com.FakeStore.API.Routes.Endpoints;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.response.Response;

public class getAllUsersTest extends BaseTest{
	
	Response res;
	String username;
	String password;
	
	@BeforeClass
	public void getUserResponse() {
		res = getRequestApiUtils.getResponseByGET(Endpoints.all_users);
	}
	
	@Test(priority = 1)
	public void logResponse() {
		System.out.println("--------------------------Response for all users----------------------");
		res.then().log().all();
	}
	
	@Test(priority = 2, groups = {"smoke"})
	public void assertResponseCode() {
		Assert.assertEquals(res.getStatusCode(), 200);
	}
	
	@Test(priority = 3, groups = {"smoke"})
	public void assertHeaders() {
		Assert.assertEquals(res.getHeader("Content-Type"), "application/json; charset=utf-8");
	}
	
	@Test(priority = 4, groups = {"smoke"})
	public void validateResponseTime() {
		Assert.assertTrue(res.getTime() < 3000, "response time is not within expected time limit");;
	}
	
	@Test(priority = 5)
	public void writeIntoFile() {
		extractCreds();
	}
	
	public void extractCreds() {
		
		username = res.jsonPath().getString("find {it.id == 8}.username");
		password = res.jsonPath().getString("find {it.id == 8}.password");
		
		try {
			Map<String, String> creds = new HashMap<>();
			
			creds.put("username", username);
			creds.put("password", password);
			
			File file = new File("src/test/resources/AuthDetails/authDetails.json");
			
			ObjectMapper mapper = new ObjectMapper();
			
			mapper.writerWithDefaultPrettyPrinter().writeValue(file, creds);
			
			System.out.println("Auth Details added to authDetails.json");
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
}