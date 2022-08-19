package Project1.RstAssured;

import static io.restassured.RestAssured.given;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import junit.framework.Assert;

import static org.hamcrest.Matchers.*;
import static org.testng.Assert.assertTrue;
public class Base {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		RestAssured.baseURI= "https://rahulshettyacademy.com";
		
		
		// Add place>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
		
		String resp = given().queryParam("key", "qaclick123").header("Content-Type", "application/json").body("{\r\n" + 
				"  \"location\": {\r\n" + 
				"    \"lat\": -38.383494,\r\n" + 
				"    \"lng\": 33.427362\r\n" + 
				"  },\r\n" + 
				"  \"accuracy\": 50,\r\n" + 
				"  \"name\": \"Rahul Shetty Academy\",\r\n" + 
				"  \"phone_number\": \"(+91) 983 893 3937\",\r\n" + 
				"  \"address\": \"29, side layout, cohen 09\",\r\n" + 
				"  \"types\": [\r\n" + 
				"    \"shoe park\",\r\n" + 
				"    \"shop\"\r\n" + 
				"  ],\r\n" + 
				"  \"website\": \"http://rahulshettyacademy.com\",\r\n" + 
				"  \"language\": \"French-IN\"\r\n" + 
				"}\r\n" + 
				"").when().post("maps/api/place/add/json")
		.then().assertThat().statusCode(200).body("scope", equalTo("APP")).header("server", "Apache/2.4.18 (Ubuntu)").extract().response().asString();
		
		System.out.println("The response is ");
		
		System.out.println(resp);
		
		JsonPath js = new JsonPath(resp);
		String placeid = js.getString("place_id");
		
		System.out.println(placeid);
		
		// Put or UPdate place>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>
		
		System.out.println("Now updating place id with new address using put");
		
		String newAddress="Summer Walk, Africa";
		
	given().queryParam("place_id", "qaclick123").header("Content-Type","application/json").body("{\r\n" + 
				"\"place_id\":\""+placeid+"\",\r\n" + 
				"\"address\":\""+newAddress+"\",\r\n" + 
				"\"key\":\"qaclick123\"\r\n" + 
				"}").when().put("maps/api/place/update/json").then().assertThat().statusCode(200).body("msg", equalTo("Address successfully updated"));
		// GetPLace >>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>


	String 	getresponse=     given().log().all().queryParam("key", "qaclick123").queryParam("place_id", placeid).when().get("maps/api/place/get/json")
			                  .then().assertThat().statusCode(200).extract().response().asString();

	System.out.println("The get response is");
	
	System.out.println(getresponse);
	
	JsonPath js1=  new JsonPath(getresponse);
	
	String updatedaddress = js1.getString("address");
	
	System.out.println("The updated address is"+" "+ updatedaddress);
	
	given().queryParam("myParam", "paramval").head
	
	}

    

}