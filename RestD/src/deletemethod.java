
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
import static org.testng.Assert.assertEquals;

import Body.bodycontent;

public class deletemethod {

	public static void main(String[] args)

	{

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

		// GET METHOD

		String addresss = given().log().all().queryParam("key", "qaclick123").queryParam("place_id", placeid).when()
				.get("maps/api/place/get/json").then().log().all().assertThat().statusCode(200).extract().asString();

		JsonPath js1 = new JsonPath(addresss);
		String name = js1.get("name");

		// validating address by using assertions

		assertEquals("Srikanth Bandi", name);
		System.out.println("posted sucessfully");

		// DELETE

		given().log().all().queryParam("key", "qaclick123").header("Content-Type", "application/json")
				.body("{\r\n" + "    \"place_id\":\"" + placeid + "\"   	 \r\n" + "}").when()
				.delete("maps/api/place/delete/json").then().log().all().assertThat().statusCode(200)
				.body("status", equalTo("OK"));

	}

}
