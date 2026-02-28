package com.FakeStore.API.BaseFile;

import org.testng.annotations.BeforeSuite;

import io.restassured.RestAssured;

public class BaseTest{
	
	@BeforeSuite
	public void setUp() {
		RestAssured.baseURI = "https://fakestoreapi.com";
	}
}