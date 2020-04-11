package jsonobjectsandarrayswithinresponse;

import Body.bodycontent;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

import static io.restassured.RestAssured.*;

import org.testng.annotations.Test;

public class dynamicjsonloadwithparameters {

	@Test
	public void addbook() {
		
		// DYNAMIC WAY OF HANDLING JSON INPUT BODY values

		RestAssured.baseURI = "http://216.10.245.166";

		String res = given().log().all().header("Content-Type", "application/json").body(bodycontent.addbook("isbn","bdcd")).when()
				.post("/Library/Addbook.php").then().log().all().assertThat().statusCode(200).extract().response()
				.asString();

		JsonPath js = new JsonPath(res);
		String id = js.get("ID");

	}

}
