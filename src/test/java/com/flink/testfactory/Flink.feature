Feature: Flink Assesment for Channels 
	Description: This feature will test given scenario as part of Assesment

@test 
Scenario: As a user, I want to buy two products from weathershopper 
	Given I am on the homescreen 
	When I click on Buy option on suncreen or Moisturizers based on the temprature 
	And I am on apt product category screen 
	And I add less expensive product from two product types 
	And I click on the Cart 
	Then I verify added products are available in cart 
	And I verify the total product cost is shown correctly 
	When I click on pay with card button 
	And I enter email and card details 
	And I click on pay 
	Then I verify whether payment is done successfully