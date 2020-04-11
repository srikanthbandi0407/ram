import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
import static org.testng.Assert.assertEquals;

import Body.bodycontent;

public class postputget {

	public static void main(String[] args) {

		// POST METHOD

		RestAssured.baseURI = "https://rahulshettyacademy.com";

		String responce = given().log().all().queryParam("key", "qaclick123").header("Content-Type", "application/json")
				.body(bodycontent.addbody()).when().post("maps/api/place/add/json").then().log().all().assertThat()

				// validating responses by status code body and header

				.statusCode(200).body("scope", equalTo("APP")).header("Server", "Apache/2.4.18 (Ubuntu)").extract()
				.asString();

		JsonPath js = new JsonPath(responce);
		String placeid = js.getString("place_id");
		System.out.println(placeid);

		// PUT METHOD

		String updateaddress = "india";

		given().log().all().queryParam("key", "qaclick123").header("Content-Type", "application/json")
				.body("{\r\n" + "\"place_id\":\"" + placeid + "\",\r\n" + "\"address\":\"" + updateaddress + " \",\r\n"
						+ "\"key\":\"qaclick123\"\r\n" + "}")
				.when().put("maps/api/place/update/json").then().log().all().assertThat().statusCode(200)
				.body("msg", equalTo("Address successfully updated"));

		// GET METHOD

		String addresss = given().log().all().queryParam("key", "qaclick123").queryParam("place_id", placeid).when()
				.get("maps/api/place/get/json").then().log().all().assertThat().statusCode(200).extract().asString();

		JsonPath js1 = new JsonPath(addresss);
		String uaddress = js1.get("address");

		// validating address by using assertions

		assertEquals("india ", uaddress);

	}

}
