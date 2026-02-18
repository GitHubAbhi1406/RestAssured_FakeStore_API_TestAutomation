package com.FakeStore.API.BaseFile;

import org.testng.annotations.BeforeClass;
import io.restassured.RestAssured;

public class BaseTest{
	
	@BeforeClass
	public void setUp() {
		RestAssured.baseURI = "https://fakestoreapi.com";
	}
}