package com.FakeStore.API.GetRequestTests;

import java.util.ArrayList;
import java.util.List;

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
	 
	@Test(priority = 2)
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
		
	}
}