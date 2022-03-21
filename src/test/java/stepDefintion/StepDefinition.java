package stepDefintion;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

import static io.restassured.RestAssured.*;
import pojo.serilization.LatituteAndLongitude;
import pojo.serilization.Location;
import resources.APIResources;
import resources.TestDataBuilder;
import resources.Util;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

import io.cucumber.java.en.And;

import static org.junit.Assert.*;

public class StepDefinition extends Util {

	RequestSpecification requestSpec;
	RequestSpecification request;
	ResponseSpecification responseSpec;
	Response resp;
	static String place_id;
	JsonPath js;
	TestDataBuilder location = new TestDataBuilder();


	@Given("Add Place Payload with {string} ,{string} and {string}")
	public void add_place_payload_with_and(String name, String language, String address) throws IOException {

		// create an object of TestDataBuilder to use the method addplace which have
		// POJO classes methods to form request body
	

		request = given().spec(requestSpecification()).body(location.addPlaceApi(name, language, address));
		// RequestSpecification request is global variable
		// requestSpecification is a method in class util ,we have extend the parent
		// class in this child class to build the request

	}

	@When("User calls {string} with {string} http request")
	public void user_calls_with_http_request(String resource, String method) throws Throwable {
		// ResponseSpecification responseSpec is now global variable

		APIResources resourceAPI = APIResources.valueOf(resource);
		// It will send the valueOf resource to resourceclass ,below method
		// getResource() get the value of resource

		if (method.equalsIgnoreCase("POST"))
			// resourceAPI.getResource() we create object of resource class now we can get
			// the value by getResource method

			resp = request.when().post(resourceAPI.getResource());

		else if(method.equalsIgnoreCase("GET"))
			resp = request.when().get(resourceAPI.getResource());
		
		else if(method.equalsIgnoreCase("PUT"))
			resp = request.when().put(resourceAPI.getResource());
		
		else if(method.equalsIgnoreCase("DELETE"))
			resp = request.when().delete(resourceAPI.getResource());
	}
	

	@Then("API Response is generated successfully with status code {}")
	public void api_response_is_generated_successfully_with_status_code(int expectedstatuscode) throws Throwable  {

		responseSpec = new ResponseSpecBuilder().expectStatusCode(200).expectContentType(ContentType.JSON).build();
		int actualstatuscode = resp.getStatusCode();

		assertEquals(actualstatuscode, expectedstatuscode);
	}

	@And("{string} in response body is {string}")
	public void in_response_body_is(String key, String expectedvalue) throws Throwable  {


		assertEquals(expectedvalue, getJsonPath(resp,key));

	}
	
	@Then("verify place_Id created maps to {string} using {string}")
	public void verify_place_id_created_maps_to_using(String expectedname, String resource) throws Throwable {
	   
		place_id=getJsonPath(resp,"place_id");
		
		request = given().spec(requestSpecification()).queryParam("place_id", place_id);
		
		user_calls_with_http_request(resource,"GET");
		
		String actualname=getJsonPath(resp,"name");
		
		assertEquals(actualname,expectedname);
		
	}
	
	@Given("Delete Place API payload")
	public void delete_place_api_payload() throws Throwable {
	   
		request =  given().spec(requestSpecification()).body(location.deletePlaceApi(place_id));
		
	}



}