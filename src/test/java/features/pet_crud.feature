Feature: Petstore API - CRUD operations

  Scenario Outline: Create a pet
    Given a pet payload with id <id>, name "<name>", category "<category>", and status "<status>"
    When I send a POST request to pet
    Then the response status code should be 200
    And the response should contain name "<name>" and status "<status>"

    Examples:
      | id   | name   | category | status    |
      | 1001 | Buddy  | Dogs     | available |
      | 1002 | Kitty  | Cats     | pending   |

  Scenario: Read a pet
    Given a pet payload with id 1003, name "Max", category "Dogs", and status "available"
    When I send a POST request to pet
    Then the response status code should be 200
    When I send a GET request to pet
    Then the response status code should be 200
    And the response should contain name "Max"

  Scenario: Update a pet
    Given a pet payload with id 1004, name "Bella", category "Cats", and status "available"
    When I send a POST request to pet
    Then the response status code should be 200
    Given an updated pet payload with id 1004, name "Bella", category "Cats", and status "sold"
    When I send a PUT request to pet
    Then the response status code should be 200
    And the response should contain status "sold"

  Scenario: Delete a pet
    Given a pet payload with id 1005, name "Charlie", category "Dogs", and status "available"
    When I send a POST request to pet
    Then the response status code should be 200
    When I send a DELETE request to pet
    Then the response status code should be 200