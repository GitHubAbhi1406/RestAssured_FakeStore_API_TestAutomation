package com.FakeStore.API.POSTRequestTests;

import java.io.File;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import com.FakeStore.API.BaseFile.BaseTest;
import com.FakeStore.API.POSTAPIUtils.postRequestApiUtils;
import com.FakeStore.API.Routes.Endpoints;
import io.restassured.response.Response;

public class postProductTest extends BaseTest{
	
	Response res;
	
	@BeforeClass
	public void postRequestFunction() {
		res = postRequestApiUtils.postRequest(Endpoints.add_product, new File("src/test/resources/PostProductJson/postProduct.json"));
	}
	
	@Test(priority = 1)
	public void logResponseBody() {
		System.out.println("-------------------Response body after posting a product is as below---------------------------");
		res.then().log().all();
	}
	
	@Test(priority = 2)
	public void assertResponseCode() {
		Assert.assertEquals(res.getStatusCode(), 201);
	}
}