package com.flink.pageObjects;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;

import com.flink.webUtilities.PropertyUtils;

public class BuyProduct extends BasePage {

	/**
	 * Page Objects and methods are associated based on the steps
	 */

	private static By currtempheader = By.xpath("//h2[contains(text(), 'Current temperature')]");
	private static By moisturizerheader = By.xpath("//h2[contains(text(), 'Moisturizers')]");
	private static By sunscreentempheader = By.xpath("//h2[contains(text(), 'Sunscreens')]");
	private static By temprature = By.xpath("//span[@id = 'temperature']");
	private static By buymoisturizerbtn = By.xpath("//button[text() = 'Buy moisturizers']");
	private static By buysunscreenbtn = By.xpath("//button[text() = 'Buy sunscreens']");
	private static By almondproducts = By
			.xpath("//p[contains(text(),'Almond')]/parent::div/p[contains(text(),'Price')]");
	private static By aloeproducts = By.xpath("//p[contains(text(),'Aloe')]/parent::div/p[contains(text(),'Price')]");
	private static By spf30products = By.xpath("//p[contains(text(),'-30')]/parent::div/p[contains(text(),'Price')]");
	private static By spf50products = By.xpath("//p[contains(text(),'-50')]/parent::div/p[contains(text(),'Price')]");
	private static By cartbtn = By.xpath("//span[@id = 'cart']");
	private static By productcolumn = By.xpath("//tr/td[1]");
	private static By productpricecolumn = By.xpath("//tr/td[2]");
	private static By totalpriceele = By.xpath("//p[@id = 'total']");
	private static By paywithcardbtn = By.xpath("//button[@type = 'submit']");
	private static By inputemail = By.xpath("//input[@id = 'email']");
	private static By inputcard = By.xpath("//input[@id = 'card_number']");
	private static By inputexp = By.xpath("//input[@id = 'cc-exp']");
	private static By inputcvc = By.xpath("//input[@id = 'cc-csc']");
//	private static By inputzip = By.xpath("//input[@id = 'billing-zip']");
	private static By paybtn = By.xpath("//button[@id = 'submitButton']");
	private static By paymentsuccess = By.xpath("//h2[text() = 'PAYMENT SUCCESS']");
	private static By paymentjustifymsg = By.xpath("//p[@class = 'text-justify']");
	private static By iframe = By.xpath("//iframe[@class = 'stripe_checkout_app']");

	private static String productcategory;
	private static LinkedHashMap<String, String> products = new LinkedHashMap<String, String>();

	public static void presenceOfHomeScreen() {
		Assert.assertTrue(isElementDisplayed(currtempheader), "There is some issue in Loading Home Page");
	}

	public static void clickOnBuyProduct() {
		int temp = Integer.parseInt((getTextOn(temprature).split(" "))[0]);
		if (temp < 19) {
			clickOn(buymoisturizerbtn);
			productcategory = "Moisturizers";
		} else if (temp > 34) {
			clickOn(buysunscreenbtn);
			productcategory = "Sunscreen";
		} else {
			Assert.fail("The temp is not within the test range.");
		}
	}

	public static void presenceOfAptProductCategoryScreen() {
		if (productcategory.equals("Moisturizers")) {
			Assert.assertTrue(isElementDisplayed(moisturizerheader), "Page didn't land on Moisturizers");
		} else {
			Assert.assertTrue(isElementDisplayed(sunscreentempheader), "Page didn't land on Sunscreen");
		}
	}

	public static void addLessExpensiveProducts() {
		String leastpriceproduct;
		String leastprice;
		if (productcategory.equals("Moisturizers")) {
			leastprice = getLeastPrice(listOfElements(almondproducts));
			leastpriceproduct = getTextOn(By.xpath("(//p[contains(text(),'" + leastprice + "')]/parent::div/p)[1]"));
			products.put(leastpriceproduct, leastprice);
			clickOn(By.xpath("//p[contains(text(),'" + leastpriceproduct + "')]/parent::div/button"));
			leastprice = getLeastPrice(listOfElements(aloeproducts));
			leastpriceproduct = getTextOn(By.xpath("(//p[contains(text(),'" + leastprice + "')]/parent::div/p)[1]"));
			products.put(leastpriceproduct, leastprice);
			clickOn(By.xpath("//p[contains(text(),'" + leastpriceproduct + "')]/parent::div/button"));
		} else {
			leastprice = getLeastPrice(listOfElements(spf30products));
			leastpriceproduct = getTextOn(By.xpath("(//p[contains(text(),'" + leastprice + "')]/parent::div/p)[1]"));
			products.put(leastpriceproduct, leastprice);
			clickOn(By.xpath("//p[contains(text(),'" + leastpriceproduct + "')]/parent::div/button"));
			leastprice = getLeastPrice(listOfElements(spf50products));
			leastpriceproduct = getTextOn(By.xpath("(//p[contains(text(),'" + leastprice + "')]/parent::div/p)[1]"));
			products.put(leastpriceproduct, leastprice);
			clickOn(By.xpath("//p[contains(text(),'" + leastpriceproduct + "')]/parent::div/button"));
		}
	}

	public static String getLeastPrice(List<WebElement> elements) {
		ArrayList<Integer> pricelist = new ArrayList<Integer>();
		try {
			for (WebElement ele : elements) {
				String[] prices = getTextOnEle(ele).split(" ");
				pricelist.add(Integer.parseInt(prices[prices.length - 1]));
			}
			Collections.sort(pricelist);
			return String.valueOf(pricelist.get(0));
		} catch (Exception e) {
			Assert.fail("There is no product related to the selected scenario");
			return null;
		}
	}

	public static void clickOnCart() {
		clickOn(cartbtn);
	}

	public static void verifyAddedProducts() {
		List<WebElement> productlist = listOfElements(productcolumn);
		for (int i = 0; i < productlist.size(); i++) {
			String productname = getTextOnEle(productlist.get(i));
			String productprice = getTextOn(By.xpath("//td[text() = '" + productname + "']/parent::tr/td[2]"));
			Assert.assertEquals(products.get(productname), productprice,
					"Price of product " + productname + " is not as expected");
		}
	}

	public static void verifyTotalProductCost() {
		List<WebElement> productpricelist = listOfElements(productpricecolumn);
		int totalprice = 0;
		for (WebElement ele : productpricelist) {
			int eleprice = Integer.parseInt(getTextOnEle(ele));
			totalprice += eleprice;
		}
		String fullprice = String.valueOf(totalprice);
		Assert.assertTrue(getTextOn(totalpriceele).contains(fullprice), "The total price is not as expected.");
	}

	public static void clickOnPayCardBtn() {
		clickOn(paywithcardbtn);
	}

	public static void sendOnEmailandCardDetails() {
		switchToFrame(iframe);
		pause(2);
		sendKeysOnJS(inputemail, PropertyUtils.configProperty("email"));
		sendKeysOnJS(inputcard, PropertyUtils.configProperty("card"));
		pause(1);
		sendKeysOnJS(inputexp, PropertyUtils.configProperty("expiry"));
		sendKeysOnJS(inputcvc, PropertyUtils.configProperty("cvc"));
//		pause(1);
//		sendKeysOnJS(inputzip,PropertyUtils.configProperty("zip"));
	}

	public static void clickOnPay() {
		clickOn(paybtn);
		switchToDefault();
	}

	public static void verifyPaymentSuccessMsg() {
		Assert.assertTrue(isElementDisplayed(paymentsuccess),
				"Payment got failed as there was chance the payment may fail");
		Assert.assertEquals(PropertyUtils.configProperty("successmsg"), getTextOn(paymentjustifymsg),
				"Payment success msg is not as expected");
	}

}