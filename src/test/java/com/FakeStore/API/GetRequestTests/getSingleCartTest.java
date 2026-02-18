package com.FakeStore.API.GetRequestTests;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import com.FakeStore.API.BaseFile.BaseTest;
import com.FakeStore.API.GetRequestApiUtils.getRequestApiUtils;
import com.FakeStore.API.Routes.Endpoints;
import io.restassured.response.Response;

public class getSingleCartTest extends BaseTest{
	
	Response res;
	
	@BeforeClass
	public void getResponseforCart() {
		res = getRequestApiUtils.getResponseById(Endpoints.single_cart, 5);
	}
	
	@Test(priority = 1)
	public void logResponseBody() {
		System.out.println("-----------------------Response Body is as below-------------------------");
		res.then().log().all();
	}
	
	@Test(priority = 2)
	public void assertResponseCode() {
		Assert.assertEquals(res.getStatusCode(), 200);
	}
}