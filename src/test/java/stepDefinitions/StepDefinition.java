package stepDefinitions;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import pojo.User;
import resources.APIResources;
import resources.Utils;

      

public class StepDefinition  extends Utils{

	RequestSpecification reqSpec;
	ResponseSpecification resSpec;
	Response response;
	
	public static ArrayList<Integer> al ;
	
	
	
	
	
	
	
	@Given("baseURL and  API resource")
	public void base_url_and_api_resource()  throws IOException {
		
	
	   reqSpec =	given().spec(getRequestSpecification());
		

	}
	@When("user calls {string} with {string} http request")
	public void user_calls_with_http_request(String resource, String httpMethod) {
	   
	//response = reqSpec.when().get("/users");
		
      APIResources apiResource = APIResources.valueOf(resource);
		
		System.out.println(apiResource.getResource());
		 
		 if(httpMethod.equalsIgnoreCase("POST"))
		response = reqSpec.when().post(apiResource.getResource());
		 else if(httpMethod.equalsIgnoreCase("GET"))
			 response = reqSpec.when().get(apiResource.getResource()); 
		
		
	}
	@Then("the API call got success with status code {int}")
	public void the_api_call_got_success_with_status_code(int expectedstatusCode) {
	   
		assertEquals(response.getStatusCode(),expectedstatusCode);
		
	}
	@Then("the API response contain users count {int}")
	public void the_api_response_contain_users_count(int expectedUserCount) {
	    
       String resposneString = response.asString();
		
		JsonPath js = new JsonPath(resposneString);
	
		List<User> users = Arrays.asList(response.jsonPath().getObject("$", User[].class));
		
		assertEquals(users.size(),expectedUserCount);
		
	   // adding userid's of London users for another test
		al = new ArrayList<Integer>();
	    
	    for (int i=0; i<users.size(); i++) {
	    	
	    	System.out.println(users.get(i).getId());
	    	
	    	al.add(users.get(i).getId());
	    }
	}


	@Given("baseURL and  CityUserAPI resource and city is {string}")
	public void base_url_and_city_user_api_resource_and_city_is(String requestedCity) throws IOException {
		
		 reqSpec =	given().spec(getRequestSpecification()).pathParam("city", requestedCity);
	    
	}
	
	
	
	@Given("baseURL and  API resource and {string} of London users")
	public void base_url_and_api_resource_and_of_london_users(String userId) throws IOException {
	   
		 reqSpec =	given().spec(getRequestSpecification()).pathParam("id", userId );
		 
	}
	

	
	
}
