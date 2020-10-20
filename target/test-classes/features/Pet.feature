Feature: E2E Pets Test
  The purpose of these test to test the Pet API's

  @API
  Scenario Outline: Get Available pets list
    Given User sent a request with pet status as "<petStatus>"
    Then A list of available pets is returned

    Examples: 
      | petStatus |
      | available |

  @API
  Scenario Outline: Add a pet to the store
    Given User want to add a pet with details "<catId>","<catName>","<petName>","<photoUrls>","<tagId>","<tagName>","<Status>"
    Then verify that pet is added to the store

    Examples: 
      | catId | catName | petName   | photoUrls         | tagId | tagName   | Status     |
      |     1 | dog     | dobberman | https://image.com |     1 | strongdog | availaible |

  @API
  Scenario Outline: Update a pet in the store
    Given User want to update a pet record details as "<Status>"
    Then I verify that pet details are updated

    Examples: 
      | Status |
      | sold   |

  @API
  Scenario Outline: Delete a pet from the list
    Given User sent a delete pet request
    Then a pet is deleted successfully from the store
