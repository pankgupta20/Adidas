Feature: Order a product from Blaze
  The purpose of these test to test the Order system

  @UI
  Scenario: Order a product
    Given Navigate through the product categories
    Then I Add given "<products>" in the cart
      | Dell i7 8gb  |
      | Sony vaio i5 |
    And Navigate to cart and Delete a product
      | Dell i7 8gb |
    Then click on place order
    And Fill the details and click on purchase
    Then Verify the purchase amount
