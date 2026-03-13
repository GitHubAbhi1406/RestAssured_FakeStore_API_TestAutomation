package com.FakeStore.API.GetRequestTests;

import org.testng.Assert;
import org.testng.annotations.Test;
import com.FakeStore.API.BaseFile.BaseTest;
import com.FakeStore.API.GetRequestApiUtils.getRequestApiUtils;
import com.FakeStore.API.Routes.Endpoints;
import io.restassured.response.Response;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;

public class getSingleProductTest extends BaseTest{
	
	Response res;
	
	@Test(dataProvider = "oddIds",
			dataProviderClass = getAllProductsTest.class)
	public void getResponse(int id) {
		
		res = getRequestApiUtils.getResponseById(Endpoints.single_product, id);
	}
	
	@Test
	public void logResponse() {
		res.then().log().all();
		}
	
	@Test
	public void validateResponseCode() {
		Assert.assertTrue(res.getStatusCode() == 200, "Response COde does not match");
	}
	
	@Test
	public void validateJsonSchema() {
		res.then().assertThat().body(matchesJsonSchemaInClasspath("SchemaValidation/singleProductSchema.json"));
	}
	
	@Test
	public void validateResponseTime() {
		Assert.assertTrue(res.getTime()<3000, "Response is not within acceptable limits");
	}
	
}