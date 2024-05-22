package api.Endpoint;






/*
 * 
 * DELETEndpoint=deleteuser/
POSTEndpoint=createusers
PUTBYID=updateuser/
authLogin=Numpy@gmail.com
authPassword=api@123
baseURI=https\://userserviceapi-a54ceee3346a.herokuapp.com/uap/
user_id=7723

 */

public class Routes {
	
	//User module
	
	public static String base_url ="https://userserviceapi-a54ceee3346a.herokuapp.com/uap/";
	public static String  post_url =base_url +"createusers";
	public static String get_ID_url= base_url  +"user/{userId}";
	public static String get_Name_url= base_url  +"users/username/{userFirstName}";
	public static String get_All_url= base_url  +"users";
	public static String put_ID_url= base_url  +"updateuser/{userId}";
	public static String delete_ID_url= base_url  +"deleteuser/{userId}";
	public static String delete_Name_url= base_url  +"deleteuser/username/{userfirstname}";
	
	
	//you can keep adding urls in the same class
	
	
	
	

}
