package com.FakeStore.API.POSTRequestTests;

import java.io.File;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import com.FakeStore.API.BaseFile.BaseTest;
import com.FakeStore.API.POSTAPIUtils.postRequestApiUtils;
import com.FakeStore.API.Routes.Endpoints;
import io.restassured.response.Response;

public class postCartInfoTest extends BaseTest{
	
	Response res;
	
	@BeforeClass
	public void getResponse() {
		res = postRequestApiUtils.postRequest(Endpoints.post_cart, new File("src/test/resources/PostCartJson/addCart.json"));
	}
	
	@Test(priority = 1)
	public void logResponse() {
		System.out.println("----------------Response is as below-----------------");
		res.then().log().all();
	}
	
	@Test(priority = 2)
	public void assertResponseCode() {
		Assert.assertEquals(res.getStatusCode(), 201);
	}
	
	
}