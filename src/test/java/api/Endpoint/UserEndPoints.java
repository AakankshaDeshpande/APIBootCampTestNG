package api.Endpoint;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
import java.io.IOException;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.json.JSONObject;
import api.payload.User;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

//created to perform CURD  Requests to USER API(create,read update and delete)

public class UserEndPoints {
	
	
	
	public static  Response createUser(User payload)
	{
		Response response = given()
				.auth().basic("Numpy@gmail.com", "api@123")
		     .contentType(ContentType.JSON)
		     .body(payload)
		.when()
		     .post(Routes.post_url);
		return response;
	}
	
	
	
	public static  Response getUserbyID(int user_id)
	{
		Response response = given()
				.auth().basic("Numpy@gmail.com", "api@123")
				.pathParam("userId",user_id)
				
		     
		.when()
		     .get(Routes.get_ID_url);
		return response;
	}

	
	public static  Response getUserbyName(String user_first_name)
	{
		Response response = given()
				.auth().basic("Numpy@gmail.com", "api@123")
		          .pathParam("userFirstName",user_first_name)
		     
		.when()
		     .get(Routes.get_Name_url);
		return response;
	}
	public static  Response updateUserByID(User payload,int user_id)
	{
		Response response = given()
				.auth().basic("Numpy@gmail.com", "api@123")
		     .contentType(ContentType.JSON)
		     .pathParam("userId",user_id)
		     .body(payload)
		     
		.when()
		     .put(Routes.put_ID_url);
		return response;
	}
	
	
	public static  Response deleteUserByID(int user_id)
	{
		Response response = given()
				.pathParam("userId",user_id)
		     
		.when()
		     .delete(Routes.delete_ID_url);
		return response;
	}
	
	
}
