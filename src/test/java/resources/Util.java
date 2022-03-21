package resources;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Properties;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class Util {

	public static RequestSpecification requestSpec;
	Response resp;

	public RequestSpecification requestSpecification() throws IOException {

		if(requestSpec==null) {
		PrintStream log = new PrintStream(new FileOutputStream("logging.txt"));

		requestSpec = new RequestSpecBuilder().setBaseUri(getGlobalValue("baseUrl"))
				.addHeader("Content-Type", "application/json").addQueryParam("key", "qaclick123")
				.addFilter(RequestLoggingFilter.logRequestTo(log)).addFilter(ResponseLoggingFilter.logResponseTo(log))
				.build();
		// requestSpec.log().all(); To log all request in console
		return requestSpec;
		}
		return requestSpec;
	}

	public static String getGlobalValue(String key) throws IOException {

		//Get the file path and and read the file
		FileInputStream file = new FileInputStream("src\\test\\java\\resources\\global.properties");

		Properties prop = new Properties();
		
		//property class use to read property file
		prop.load(file);
		
		//get the property in file means various fields
		return prop.getProperty(key);

	}
	
	public String getJsonPath(Response resp,String key) {
		
		String response = resp.asString();

		JsonPath js = new JsonPath(response);
		
		return js.get(key).toString();		
		
	}
}
