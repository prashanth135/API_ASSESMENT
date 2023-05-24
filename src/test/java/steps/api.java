package steps;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jayway.jsonpath.JsonPath;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import utils.Shared;
import utils.payload;
import utils.request;

public class api extends request{

	request req = new request();
	List<String> first_name = new ArrayList<String>();
	List<String> email = new ArrayList<String>();
	
	
	@Given("^Get the userdetails from the given api call$")
	public void Get_User_Detials() {
		 payload p = new payload();
		 Shared.Newuser	= p.userjson();
		 Shared.Updateduser = p.userUpdatejson();
		 
	//Get the api call
		String response = req.get(Shared.getUrl);
		Shared.listuserdetails = response;
		System.out.println("cool"+ Shared.listuserdetails);
	}

	@Then("^Get the Firstname and Email of users for the Get api call$")
	public void user_deatils() {
	//Filter out list of First name and email of all the Users obtained in GET call.
		first_name = JsonPath.read(Shared.listuserdetails,"$.data[*].first_name");
		 email = JsonPath.read(Shared.listuserdetails,"$.data[*].email");
		System.out.println("first_name"+ first_name);
		System.out.println("email"+ email);
	}

	@Then("^Get the userdetails by ID (.*)$")
	public void user_deatils_id(int id) {
		// Filter out user details by giving ID. and Validate  response code
        String GetResponseById=  req.get("https://reqres.in/api/users?id="+id+"");
        System.out.println("GetResponseById"+ GetResponseById);
       
        
        // Validate user details by comparing with previous response 
        String name = JsonPath.read(GetResponseById,"$.data.first_name");
        String emailobj = JsonPath.read(GetResponseById,"$.data.email");
        Assert.assertTrue(first_name.contains(name));
        Assert.assertTrue(email.contains(emailobj));

	}
	
	@Then("^Post the userdetails by given api$")
	  public  void createUser() throws IOException {
		//Create new user and and save it in shared varible for verification
     String PostResponse = req.sendPost1(Shared.postUrl, Shared.Newuser);
     Shared.createUser = PostResponse;
 	System.out.println("PostResponse"+  Shared.createUser);
	
	}
	
	@Then("^Verify the userdetails for post api$")
	  public static void verifyUser() throws IOException {
		// retrieve newly created ID.and verifying the json before post and after post
		//Shared.Newuser == before post saved it in shared varibale
		// Shared.createUser = Response from the post api 
		
		String id = JsonPath.read(Shared.createUser,"$.id");
		 System.out.println("passid" + id);
		 Assert.assertTrue(Shared.createUser.contains(id));
			ObjectMapper mapper = new ObjectMapper();	
		    JsonNode actualObj = mapper.readTree(Shared.Newuser);
		    JsonNode expectedObj = mapper.readTree(Shared.createUser);
		    String actualName = actualObj.get("name").asText();
		    String expectedName = expectedObj.get("name").asText();
		    String actualJob = actualObj.get("job").asText();
		    String expectedJob = expectedObj.get("job").asText();
		    System.out.println("actualObj" + actualObj + "\n" + "expectedObj"+ expectedObj);
		    Assert.assertEquals(actualName, expectedName);  // Assertion
		    Assert.assertEquals(actualJob, expectedJob);
		   	
	}
	
	@Then("^Update the userdetails by given api for the existing user$")
	  public void updateUser() throws IOException {
		 //Update user information for created user in the previous step
	        String PutResponse = req.sendPut(Shared.putUrl, Shared.Updateduser);
	        Shared.userdetails = PutResponse;
		System.out.println("PutResponse\n" + PutResponse);
		
	  
}
	
	@Then("^Verify the userdetails after updating the details$")
	  public void VerifyUpdateUser() throws IOException {
		//Validate response code and user information for the updated record.
		 String job = JsonPath.read(Shared.createUser,"$.job");
		 System.out.println("passjob" + job);
			ObjectMapper mapper = new ObjectMapper();	
		    JsonNode actualObj = mapper.readTree(Shared.userdetails);// Shared.userdetails = actual object
		    JsonNode expectedObj = mapper.readTree(Shared.Updateduser);// Shared.Updateduser = epected object saved in static variable
		    String actualPostingJob = actualObj.get("job").asText();
		    String expectedJob = expectedObj.get("job").asText();
		    System.out.println("actualObj" + actualObj + "\n" + "expectedObj"+ expectedObj);
		    Assert.assertEquals(actualPostingJob, expectedJob);// Assertion before and after post
	
		
	}



	}
