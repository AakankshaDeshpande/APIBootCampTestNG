package api.test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;
import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import api.Endpoint.UserEndPoints;
import api.payload.User;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class userTest {

    User userPayload;
    int id;
    int expectedStatusCode;

    @BeforeClass
    public void setupData() throws IOException {
        File f = new File("C:\\postman\\RestAssure\\APIBootCampTestNg\\src\\test\\resources\\DATA\\body.json");
        FileReader fr = new FileReader(f);
        JSONTokener jt = new JSONTokener(fr);

        JSONObject data = new JSONObject(jt);
        userPayload = createPayload(data);

    }

    public User createPayload(JSONObject data) {
        try {
            userPayload = new User(
                    data.getString("user_first_name"),
                    data.getString("user_last_name"),
                    data.optLong("user_contact_number",0),
                    data.getString("user_email_id"),
                    buildAddressObject(data.getJSONObject("userAddress"))
            );
        } catch (NumberFormatException | JSONException e) {
            // If parsing fails, set status code to 400
            expectedStatusCode = 400;
        }

        return userPayload;
    }

    private User.Address buildAddressObject(JSONObject addressData) {
        return new User.Address(
                addressData.getString("plotNumber"),
                addressData.getString("street"),
                addressData.getString("state"),
                addressData.getString("country"),
                addressData.optLong("zipCode", 0)
        );
    }

    @Test(priority = 1)
    public void testUsingUserObject(ITestContext context) {
        // Use the populated userPayload object in your tests
        Response response = UserEndPoints.createUser(userPayload);
        String responseBody = response.asString();
        response.then().log().all();

        JsonPath jsonPath = response.jsonPath();

        id = jsonPath.getInt("user_id");

        context.setAttribute("user_id", id);

        Assert.assertEquals(response.getStatusCode(), 201, "Unexpected status code");

        Assert.assertEquals(jsonPath.getString("user_first_name"), userPayload.getUser_first_name(), "First name mismatch");
        Assert.assertEquals(jsonPath.getString("user_last_name"), userPayload.getUser_last_name(), "Last name mismatch");
       // Assert.assertEquals(jsonPath.getString("user_contact_number"), userPayload.getUser_contact_number(), "Contact number mismatch");
        Assert.assertEquals(jsonPath.getString("user_email_id"), userPayload.getUser_email_id(), "Email id mismatch");
        Assert.assertEquals(jsonPath.getString("userAddress.plotNumber"), userPayload.getUserAddress().getPlotNumber(), "Plot number mismatch");
        Assert.assertEquals(jsonPath.getString("userAddress.street"), userPayload.getUserAddress().getStreet(), "Street mismatch");
        Assert.assertEquals(jsonPath.getString("userAddress.state"), userPayload.getUserAddress().getState(), "State mismatch");
        Assert.assertEquals(jsonPath.getString("userAddress.country"), userPayload.getUserAddress().getCountry(), "Country mismatch");
        Assert.assertEquals(jsonPath.getLong("userAddress.zipCode"), userPayload.getUserAddress().getZipCode().longValue(), "Zip code mismatch");

        System.out.println("Response body:\n" + responseBody);
        System.out.println("User ID: " + id);
    }

    @Test(priority = 2)
    public void testPutRequests(ITestContext context) {
        try {
            File file = new File("C:\\postman\\RestAssure\\APIBootCampTestNg\\src\\test\\resources\\DATA\\scenarios.json");
            FileReader fr = new FileReader(file);
            JSONTokener jt = new JSONTokener(fr);
            JSONObject data = new JSONObject(jt);

            JSONArray scenarios = data.getJSONArray("scenarios");

            id = (int) context.getAttribute("user_id");

            for (int i = 0; i < scenarios.length(); i++) {
                JSONObject scenario = scenarios.getJSONObject(i);
                performPutRequest(scenario, id);
                System.out.println(i);
            }

            fr.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void performPutRequest(JSONObject scenario, int id) {
        JSONObject requestBody = createRequestBody(scenario);

        userPayload = createPayload(requestBody);

        expectedStatusCode = scenario.getInt("expectedStatus");

        Response response = UserEndPoints.updateUserByID(userPayload, id);
        String responseBody = response.asString();

        response.then().log().all();
        JsonPath jsonPath = response.jsonPath();

        int actualStatusCode = response.getStatusCode();

        Assert.assertEquals(actualStatusCode, expectedStatusCode,
                "Expected status to be " + expectedStatusCode + " but found: " + actualStatusCode);
    }

    private JSONObject createRequestBody(JSONObject scenario) {
        JSONObject requestBody = new JSONObject();
        try {
            for (String key : scenario.keySet()) {
                if (!key.equals("scenario_name") && !key.equals("expectedStatus")) {
                    requestBody.put(key, scenario.get(key));
                }
            }
        } catch (NumberFormatException | JSONException e) {
            // If parsing fails, set status code to 400
            expectedStatusCode = 400;
        }
        return requestBody;
    }
}