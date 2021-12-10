package dwp.APIAutomation;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
// import io.restassured.parsing.Parser;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import pojo.User;

import static io.restassured.RestAssured.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Ignore;
import org.junit.Test;



public class GetUsers {
	
public static ArrayList<Integer> al ;

	
//	public static void main(String [] args) {
		
		
		
	@Ignore @Test 
		public void getUsersTest1() {
		
		RestAssured.baseURI = "https://bpdts-test-app.herokuapp.com";
		
		RequestSpecification reqSpec = new RequestSpecBuilder().setBaseUri("https://bpdts-test-app.herokuapp.com").build();
		
		ResponseSpecification resSpec = new ResponseSpecBuilder().expectContentType(ContentType.JSON).expectStatusCode(200).build();
		
		
		/*
		 * Response res = given().log().all(). when().get("/users").
		 * then().log().all().statusCode(200).extract().response();
		 */
		
		
		Response res = given().spec(reqSpec).when().get("/users").then().spec(resSpec).extract().response();
		
		String resposneString = res.asString();
		
		JsonPath js = new JsonPath(resposneString);
			
		String actualFirstName = js.getString("first_name");
		
		System.out.println(actualFirstName.contains("Ches"));
		
		
		int userCount = js.getInt("Object.size()");
		
		System.out.println("Number of users : " + userCount);
		

		}
	
		
		
		@Ignore @Test
		public void getCityUsersTest2() {
			
			RestAssured.baseURI = "https://bpdts-test-app.herokuapp.com";
			
			
		String apiResponse = 	given().log().all().
			when().get("city/London/users").
			then().log().all().assertThat().statusCode(200).extract().response().asString();
		
		JsonPath js1 = new JsonPath(apiResponse);
		
		int countOfLondonUsers = js1.getInt("Object.size()");
		
		System.out.println("The count of London users : " + countOfLondonUsers);
		
			
		}
		
		
		@Ignore @Test
		public void getUsersTest3() {
		
			RestAssured.baseURI = "https://bpdts-test-app.herokuapp.com";
		
			/*
			 * GetUsersList urs = given().expect().defaultParser(Parser.JSON).
			 * when().get("/users").as(GetUsersList.class);
			 */
		
	
			Response response = given().when().get("/users");
			
			List<User> users = Arrays.asList(response.jsonPath().getObject("$", User[].class));
			
		     
		    System.out.println("out from Test3 : " +users.size());
		
		    
		    System.out.println(users.get(999).getFirst_name());
		    System.out.println(users.get(999).getLast_name());
		    
		}
		
		
		@Ignore @Test
		public void getUsersByIdTest4() {
			
			RestAssured.baseURI = "https://bpdts-test-app.herokuapp.com";
			
           Response response = given().when().get("city/London/users");
			
			List<User> users = Arrays.asList(response.jsonPath().getObject("$", User[].class));
	
		String lat = users.get(0).getLatitude();
		String lng = users.get(0).getLongitude();
				
				System.out.println("lat is :" + lat);
				System.out.println("lat is :" + lng);
				
				RestAssured.baseURI = "https://maps.googleapis.com";		
				given()
				.param("latlng", "lat,lng").param("key", "AIzaSyBOv0FdgOatjhKdACFdnCeSTV0rVOwKfIw")
				.when().get("/maps/api/geocode/json")
				.then().statusCode(200);
				
		
			
		}
		
		
		
		@Test
		public void getLondonUsersTest5() {
			
			RestAssured.baseURI = "https://bpdts-test-app.herokuapp.com";
			
		
           Response response = given().pathParam("city", "London")
        		   .when().get("city/{city}/users");
			
			List<User> users = Arrays.asList(response.jsonPath().getObject("$", User[].class));
			
		     
		    System.out.println("output from Test5 : " +users.size());
		
		    
		    System.out.println(users.get(0).getFirst_name());	
		    
		    al = new ArrayList<Integer>();
		    
		    for (int i=0; i<users.size(); i++) {
		    	
		    	System.out.println(users.get(i).getId());
		    	
		    	al.add(users.get(i).getId());
		    
		    	System.out.println("--------------------------");
		    	
		    }
		    
		    for (int j=0; j<al.size(); j++ )
		    {
		    	System.out.println("Id from Arraylist :" + al.get(j));
		    }
		    
		   System.out.println("------------------Users by id-------------"); 
		    
		   
			/*
			 * RestAssured.baseURI = "https://bpdts-test-app.herokuapp.com"; Response
			 * response1 = given().pathParam("id", al.get(0).toString())
			 * .when().get("user/{id}");
			 * 
			 * String responseFromUserIdAPI = response1.asString();
			 * 
			 * JsonPath js5 = new JsonPath(responseFromUserIdAPI);
			 * 
			 * System.out.println(js5.getString("id"));
			 * System.out.println(js5.getString("city"));
			 * 
			 * System.out.println(responseFromUserIdAPI);
			 */
		   
			
			/*
			 * List<User> users1 = Arrays.asList(response.jsonPath().getObject("$",
			 * User[].class));
			 * 
			 * 
			 * System.out.println("London users by id :" +users1.get(UNDEFINED_PORT));
			 */
		    
		    	
		    }
			
			
			@Test
			public void getUsersByIdTest6() {
				
				
				
				 RestAssured.baseURI = "https://bpdts-test-app.herokuapp.com";
				 
				 for(int i=0; i<al.size(); i++) {
				 
				   Response response5= given().pathParam("id", al.get(i))
		        		   .when().get("/user/{id}");
				   
				   String responseFromUserIdAPI = response5.asString();
				   
				   JsonPath js5 = new JsonPath(responseFromUserIdAPI);
				   
				   System.out.println(responseFromUserIdAPI);
				   
				   System.out.println(System.getProperty("user.dir"));
				   
				 }
				
			}
			
			
	
		
		
		
}
