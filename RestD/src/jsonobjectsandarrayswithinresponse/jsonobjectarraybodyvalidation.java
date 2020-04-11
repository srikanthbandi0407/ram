package jsonobjectsandarrayswithinresponse;

import org.testng.Assert;

import Body.bodycontent;
import io.restassured.path.json.JsonPath;

public class jsonobjectarraybodyvalidation {

	public static String array() {

		return "{\r\n" + "\"dashboard\": {\r\n" + "\"purchaseAmount\": 910,\r\n"
				+ "\"website\": \"rahulshettyacademy.com\"\r\n" + "},\r\n" + "\"courses\": [\r\n" + "{\r\n"
				+ "\"title\": \"Selenium Python\",\r\n" + "\"price\": 50,\r\n" + "\"copies\": 6\r\n" + "},\r\n"
				+ "{\r\n" + "\"title\": \"Cypress\",\r\n" + "\"price\": 40,\r\n" + "\"copies\": 4\r\n" + "},\r\n"
				+ "{\r\n" + "\"title\": \"RPA\",\r\n" + "\"price\": 45,\r\n" + "\"copies\": 10\r\n" + "}\r\n" + "]\r\n"
				+ "}\r\n" + "\r\n" + "";
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		JsonPath js = new JsonPath(array());

		// Print No of courses returned by API

		int coursescount = js.getInt("courses.size()");

		System.out.println("No of courses returned by API =" + coursescount);

		// Print Purchase Amount

		int Purchase_Amount = js.getInt("dashboard.purchaseAmount");

		System.out.println("Print Purchase Amount =" + Purchase_Amount);

		// Print Title of the first course

		String first_course = js.get("courses[0].title");

		System.out.println("Print Title of the first course=" + first_course);

		// Print All course titles and their respective Prices
		System.out.println("course titles and their respective Prices");
		for (int i = 0; i < coursescount; i++) {

			String cousers = js.get("courses[" + i + "].title");

			int prices = js.get("courses[" + i + "].price");
			System.out.println(cousers + " = " + prices);

		}

		// Print no of copies sold by RPA Course

		for (int i = 0; i < coursescount; i++) {

			String cousers = js.get("courses[" + i + "].title");

			if (cousers.equals("RPA"))
				;
			{
				int RPAA = js.get("courses[" + i + "].copies");

				System.out.println("no of copies sold by RPA Course  " + RPAA);
			}

		}

		System.out.println("Verify if Sum of all Course prices matches with Purchase Amount");
		// Verify if Sum of all Course prices matches with Purchase Amount
		int count = 0;
		for (int i = 0; i < coursescount; i++) {

			int prices = js.get("courses[" + i + "].price");
			int copy = js.get("courses[" + i + "].copies");

			count = count + prices * copy;

		}

		System.out.println(count);
		Assert.assertEquals(count, Purchase_Amount);
		System.out.println("Sum of all Course prices matches with Purchase Amount");

	}

}
