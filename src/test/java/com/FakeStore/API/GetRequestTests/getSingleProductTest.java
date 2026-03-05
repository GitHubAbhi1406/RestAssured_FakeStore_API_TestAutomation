package com.FakeStore.API.GetRequestTests;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import com.FakeStore.API.BaseFile.BaseTest;
import com.FakeStore.API.GetRequestApiUtils.getRequestApiUtils;
import com.FakeStore.API.Routes.Endpoints;
import io.restassured.response.Response;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;

public class getSingleProductTest extends BaseTest{
	
	Response res;
	
	@BeforeClass
	public void getResponse() {
		
		res = getRequestApiUtils.getResponseById(Endpoints.single_product, 5);
	}
	
	@Test(priority = 1)
	public void logResponse() {
		res.then().log().all();
		}
	
	@Test(priority = 2)
	public void validateResponseCode() {
		Assert.assertTrue(res.getStatusCode() == 200, "Response COde does not match");
	}
	
	@Test(priority = 3)
	public void validateJsonSchema() {
		res.then().assertThat().body(matchesJsonSchemaInClasspath("SchemaValidation/singleProductSchema.json"));
	
	}
}