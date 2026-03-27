Feature: Object API End-to-End Testing

  Scenario: Create, Get and Delete object
    Given a "Apple MacBook Pro 16" item is created
    And the CPU model is "Intel Core i9"
    And has a price of "1849.99"

    When the request to add the item is made
    Then a 200 response code is returned
    And a "Apple MacBook Pro 16" is created

    When user retrieves the created object
    Then response contains correct name

    When user deletes the object
    Then response code should be 200

    When user retrieves the deleted object
    Then response code should be 404

    Scenario: Get invalid object
    When user retrieves object with invalid id
    Then response code should be 404

    Scenario: Create object with missing name
    When user sends request without name
    Then response code should be 405