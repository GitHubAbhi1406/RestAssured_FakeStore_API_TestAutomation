package com.FakeStore.API.GetRequestTests;

import java.util.List;
import java.util.Map;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import com.FakeStore.API.BaseFile.BaseTest;
import com.FakeStore.API.GetRequestApiUtils.getRequestApiUtils;
import com.FakeStore.API.Routes.Endpoints;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class getAllCartsTest extends BaseTest{
	
	Response res;
	
	
	@BeforeClass
	public void getResponse() {
		res = getRequestApiUtils.getResponseByGET(Endpoints.carts);
	}
	
	@Test(priority = 1)
	public void logResponse() {
		System.out.println("------------------------Response for response is as below-------------------------");
		res.then().log().all();
	}
	
	@Test(priority = 2)
	public void assertStatusCode() {
		Assert.assertEquals(res.getStatusCode(), 200);
	}
	
	@Test(priority = 3)
	public void validateResponseHeader() {
		Assert.assertEquals(res.getHeader("Content-Type"), "application/json; charset=utf-8");
	}
	
	@Test(priority = 4)
	public void extractResponse() {
		
		JsonPath json = res.jsonPath();
		List<Map<String, Object>> product = json.getList("find {it.id == 2}.products");
		
		System.out.println("Products Array : "+ product);
	}
	
	@Test(priority = 5)
	public void validateResponseTime() {
		Assert.assertTrue(res.getTime() < 3000, "Response Time is too long");
	}
	
	@Test(priority = 6)
	public void validateCartCount() {
		JsonPath json = res.jsonPath();
		List<Object> data = json.getList("$");
		Assert.assertTrue(data.size() > 0, "No data received in response!!!");
	}
}