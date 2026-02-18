package com.FakeStore.API.GetRequestTests;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import com.FakeStore.API.BaseFile.BaseTest;
import com.FakeStore.API.GetRequestApiUtils.getRequestApiUtils;
import com.FakeStore.API.Routes.Endpoints;
import io.restassured.response.Response;

public class getAllUsersTest extends BaseTest{
	
	Response res;
	
	@BeforeClass
	public void getUserResponse() {
		res = getRequestApiUtils.getResponseByGET(Endpoints.all_users);
	}
	
	@Test(priority = 1)
	public void logResponse() {
		System.out.println("--------------------------Response for all users----------------------");
		res.then().log().all();
	}
	
	@Test(priority = 2)
	public void assertResponseCode() {
		Assert.assertEquals(res.getStatusCode(), 200);
	}
	
	@Test(priority = 3)
	public void assertHeaders() {
		Assert.assertEquals(res.getHeader("Content-Type"), "application/json; charset=utf-8");
	}
}