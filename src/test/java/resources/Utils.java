package resources;

import java.io.FileInputStream;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Properties;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

public class Utils {
	
	public static RequestSpecification reqSpec;
	public static ResponseSpecification resSpec;
	
	public RequestSpecification getRequestSpecification() throws IOException {
	
	
		if (reqSpec==null) {
		PrintStream log = new PrintStream(new FileOutputStream("logging.txt"));
		
		reqSpec = new RequestSpecBuilder()
			  .setBaseUri(getGlobalValue("baseUrl"))
			  .addFilter(RequestLoggingFilter.logRequestTo(log))
			  .addFilter(ResponseLoggingFilter.logResponseTo(log))
			  .build();
		
		return reqSpec;
		
		}
		
		return reqSpec;
	
	
	}
	
	
	
	
	
	public ResponseSpecification getResponseSpecification() {
		
		resSpec = new ResponseSpecBuilder()
				  .expectStatusCode(200).expectContentType(ContentType.JSON).build();
		
		return resSpec;
		
	}
	
	
	public  static String getGlobalValue(String key) throws IOException {
		
		Properties prop = new Properties();
		
	//	FileInputStream fis = new FileInputStream("/Users/vineetkumar/eclipse-workspace2021/APIAutomation/src/test/java/resources/global.properties");
		
		String basedir = System.getProperty("user.dir");
		
		FileInputStream fis = new FileInputStream(basedir+"/src/test/java/resources/global.properties");
		prop.load(fis);
		
		return prop.getProperty(key);
		
		
	}
	
	
	public String getJsonPath(Response response, String key) {
		
		String resp = response.asString();
		
		JsonPath js = new JsonPath(resp);
		
		return js.get(key).toString();
		
		
		
		
	}

}
