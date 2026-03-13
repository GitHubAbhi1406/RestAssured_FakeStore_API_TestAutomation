package com.FakeStore.API.PutRequestTests;

import java.io.File;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import com.FakeStore.API.BaseFile.BaseTest;
import com.FakeStore.API.PUTRequestApiUtils.putRequestApiUtils;
import com.FakeStore.API.Routes.Endpoints;
import io.restassured.response.Response;

public class putProductTest extends BaseTest{
	
	Response res;
	
	@BeforeClass
	public void getPutRequest() {
		
		res = putRequestApiUtils.putRequestMethod(Endpoints.put_product, new File("src/test/resources/PutProduct/putProduct.json"), 19);
	}
	
	@Test(priority = 1)
	public void logResponse() {
		res.then().log().all();
	}
	
	@Test(priority = 2)
	public void validateResponseCode() {
		Assert.assertEquals(res.statusCode(), 200);
	}
	
	@Test(priority = 3)
	public void validateResponseTime() {
		Assert.assertEquals(res.getTime()<3000, "Response time is not within acceptable limits");
	}
}