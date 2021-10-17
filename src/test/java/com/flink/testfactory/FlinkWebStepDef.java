package com.flink.testfactory;

import com.flink.pageObjects.BuyProduct;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class FlinkWebStepDef {

	@Given("I am on the homescreen")
	public void iamOnHomeScreen() {
		BuyProduct.presenceOfHomeScreen();
	}

	@When("I click on Buy option on suncreen or Moisturizers based on the temprature")
	public void iClickOnBuyProductCategoryBasedOnTemp() {
		BuyProduct.clickOnBuyProduct();
	}

	@And("I am on apt product category screen")
	public void iamOnAptProductCategoryScreen() {
		BuyProduct.presenceOfAptProductCategoryScreen();
	}

	@And("I add less expensive product from two product types")
	public void iAddLessExpensiveProductFromTwoProductTypes() {
		BuyProduct.addLessExpensiveProducts();
	}

	@And("I click on the Cart")
	public void iClickOnCart() {
		BuyProduct.clickOnCart();
	}

	@And("I verify added products are available in cart")
	public void iVerifyAddedProductsInCart() {
		BuyProduct.verifyAddedProducts();
	}

	@Then("I verify the total product cost is shown correctly")
	public void iVerifyTotProductCostShown() {
		BuyProduct.verifyTotalProductCost();
	}

	@And("I click on pay with card button")
	public void iClickOnPayCardBtn() {
		BuyProduct.clickOnPayCardBtn();
	}

	@When("I enter email and card details")
	public void iEntercardandEmailDetails() {
		BuyProduct.sendOnEmailandCardDetails();
	}

	@And("I click on pay")
	public void iClickOnPay() {
		BuyProduct.clickOnPay();
	}

	@And("I verify whether payment is done successfully")
	public void iVerifyPaymentSuccessMsg() {
		BuyProduct.verifyPaymentSuccessMsg();
	}
}