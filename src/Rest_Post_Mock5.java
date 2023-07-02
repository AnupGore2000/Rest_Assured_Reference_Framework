import java.time.LocalDateTime;

import org.testng.Assert;

import io.restassured.RestAssured;
import static io.restassured.RestAssured.given;
import io.restassured.path.json.JsonPath;

public class Rest_Post_Mock5 {

	public static void main(String[] args) {
		
		RestAssured.baseURI="https://reqres.in/";
		
		String RequestBody= "{\r\n"
				+ "    \"name\": \"morpheus\",\r\n"
				+ "    \"job\": \"leader\"\r\n"
				+ "}";
		
		JsonPath Jsprequest =new JsonPath(RequestBody);
		String Req_name= Jsprequest.getString(RequestBody);
		String Req_job=Jsprequest.getString(Req_name);
		LocalDateTime currentdate=LocalDateTime.now();
		String expecteddate =currentdate.toString().substring(0,11);
		
		String ResponseBody =given().header("Content-Type","application/json").body(RequestBody).when().post("api/users").then().extract().response().asString();
		System.out.println(ResponseBody);
		
		JsonPath JspResponse = new JsonPath(ResponseBody);
		String Res_name = JspResponse.getString("name");
		String Res_job = JspResponse.getString("job");
		String Res_createdAt = JspResponse.getString("createdAt");
		Res_createdAt = Res_createdAt.substring(0,11);
		
		Assert.assertEquals(Res_name, Req_name);
		Assert.assertEquals(Res_job, Req_job);
		Assert.assertEquals(Res_createdAt, expecteddate);
		
		
	}
	
}
