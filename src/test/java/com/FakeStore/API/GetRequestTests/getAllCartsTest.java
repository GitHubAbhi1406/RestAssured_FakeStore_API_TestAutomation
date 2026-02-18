package com.FakeStore.API.GetRequestTests;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import com.FakeStore.API.BaseFile.BaseTest;
import com.FakeStore.API.GetRequestApiUtils.getRequestApiUtils;
import com.FakeStore.API.Routes.Endpoints;
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
	public void extractResponse() {
		
	}
}