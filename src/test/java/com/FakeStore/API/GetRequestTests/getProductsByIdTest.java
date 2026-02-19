package com.FakeStore.API.GetRequestTests;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import com.FakeStore.API.BaseFile.BaseTest;
import com.FakeStore.API.GetRequestApiUtils.getRequestApiUtils;
import com.FakeStore.API.Routes.Endpoints;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class getProductsByIdTest extends BaseTest{
	
	Response res;
	
	@BeforeClass
	public void getResponse() {
		res = getRequestApiUtils.getResponseById(Endpoints.single_product, 2);
	}
	
	@Test(priority = 1)
	public void logResponse() {
		System.out.println("----------Response for id number "+res.jsonPath().getInt("id")+" is as below----------------------");
		res.then().log().all();
	}
	
	@Test(priority = 2)
	public void assertResponseCode(){
		Assert.assertEquals(res.getStatusCode(), 200);
	}
	
	@Test(priority = 3)
	public void assertResponseData() {
		JsonPath json = res.jsonPath();
		
		Assert.assertEquals(json.getString("category"), "men's clothing", "Wrong Data");

	}
}