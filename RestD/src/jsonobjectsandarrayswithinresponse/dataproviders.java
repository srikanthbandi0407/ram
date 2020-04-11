package jsonobjectsandarrayswithinresponse;

import static io.restassured.RestAssured.given;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import Body.bodycontent;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

public class dataproviders {


	@Test(dataProvider = "getData")
	public void addbook(String isbn, String bcd) {

		// DYNAMIC WAY OF HANDLING JSON INPUT BODY values

		RestAssured.baseURI = "http://216.10.245.166";

		String res = given().log().all().header("Content-Type", "application/json")
				.body(bodycontent.addbook(""+isbn+"", ""+bcd+"")).when().post("/Library/Addbook.php").then().log().all()
				.assertThat().statusCode(200).extract().response().asString();

		

	}
	
	@DataProvider
	public Object[][] getData()
	{
	//Rows - Number of times your test has to be repeated.
	//Columns - Number of parameters in test data.
	Object[][] data = new Object[3][2];
	 
	// 1st row
	data[0][0] ="user1";
	data[0][1] = "abc2def";
	 
	// 2nd row
	data[1][0] ="user2";
	data[1][1] = "x2yz";
	 
	// 3rd row
	data[2][0] ="user3";
	data[2][1] = "1223456";
	 
	return data;
	}
}
