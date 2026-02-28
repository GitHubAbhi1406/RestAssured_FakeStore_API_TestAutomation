package com.FakeStore.API.GetRequestTests;

import java.util.List;
import java.util.Map;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import com.FakeStore.API.BaseFile.BaseTest;
import com.FakeStore.API.GetRequestApiUtils.getRequestApiUtils;
import com.FakeStore.API.Routes.Endpoints;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;

public class getAllCartsTest extends BaseTest{
	
	Response res;
	
	
	@BeforeClass(alwaysRun = true)
	public void getResponse() {
		System.out.println("Response initiated");
		res = getRequestApiUtils.getResponseByGET(Endpoints.carts);
	}
	
	@Test
	public void logResponse() {
		System.out.println("------------------------Response for response is as below-------------------------");
		res.then().log().all();
	}
	
	@Test(groups = {"smoke"})
	public void assertStatusCode() {
		Assert.assertEquals(res.getStatusCode(), 200);
	}
	
	@Test(groups = {"smoke"})
	public void validateResponseHeader() {
		Assert.assertEquals(res.getHeader("Content-Type"), "application/json; charset=utf-8");
	}
	
	@Test
	public void extractResponse() {
		
		JsonPath json = res.jsonPath();
		List<Map<String, Object>> product = json.getList("find {it.id == 2}.products");
		
		System.out.println("Products Array : "+ product);
	}
	
	@Test
	public void validateResponseTime() {
		Assert.assertTrue(res.getTime() < 3000, "Response Time is too long");
	}
	
	@Test
	public void validateCartCount() {
		JsonPath json = res.jsonPath();
		List<Object> data = json.getList("$");
		Assert.assertTrue(data.size() > 0, "No data received in response!!!");
	}
	
	@Test(groups = {"smoke"})
	public void validateJsonSchema() {
		res.then().assertThat().body(matchesJsonSchemaInClasspath("SchemaValidation/cartsSchema.json"));
	}
	
	
	@DataProvider(name = "evenCartIds")
	public Object[] evenCartIdProvider() {
		Response res =
		        getRequestApiUtils.getResponseByGET(Endpoints.carts);
		
		List<Integer> id = res.jsonPath().getList("findAll {it.id % 2 == 0}.id");
		
		
		return id.toArray();
	}
}