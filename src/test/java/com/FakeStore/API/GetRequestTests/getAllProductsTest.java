package com.FakeStore.API.GetRequestTests;

import java.util.ArrayList;
import java.util.List;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import com.FakeStore.API.BaseFile.BaseTest;
import com.FakeStore.API.GetRequestApiUtils.getRequestApiUtils;
import com.FakeStore.API.Routes.Endpoints;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class getAllProductsTest extends BaseTest{
	
	Response res;
	
	@BeforeClass
	public void getProductsResponse() {
		res = getRequestApiUtils.getResponseByGET(Endpoints.products);
	}
	
	@Test(priority = 1)
	public void logResponseBody() {
		System.out.println("---------Response for all products is as below--------------");
		res.then().log().all();
	}
	 
	@Test(priority = 2, groups = {"smoke"})
	public void verifyStatusCode() {
		Assert.assertEquals(res.getStatusCode(), 200);
	}
	
	@Test(priority = 3)
	public void extractResponseData() {
		JsonPath json = res.jsonPath();
		
		List<String> products_title = new ArrayList<>();
		
		products_title = json.getList("findAll {it.price >= 100}.title");
		
		System.out.println("Below are the list products title whose price is more than 100 ");
		for(int i=0;i<products_title.size();i++) {
			System.out.println(products_title.get(i));
		}
		
		List<String> categories = new ArrayList<>();
		categories = json.getList("category.unique()");
		
		System.out.println(categories);
	}
	
	@Test(priority = 4)
	public void assertResponseTime() {
		Assert.assertTrue(res.getTime() < 3000, "Response is not within accepted time limit");
	}
	
	@Test(priority = 5)
	public void validateResponseParam() {
		JsonPath json = res.jsonPath();
		List<Object> data = json.getList("$");
		Assert.assertTrue(data.size() > 0, "No data is captured");
	}
	
	@Test(priority = 6, groups = {"smoke"})
	public void validateResponseSchema() {
		res.then().assertThat().body(matchesJsonSchemaInClasspath("SchemaValidation/productsSchema.json"));
	}
}