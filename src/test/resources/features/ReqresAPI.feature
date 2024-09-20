Feature: Validate on the functionality of the API

  Scenario: Verify the user can register successfully
    Given user can calls the "/register" endpoint
    And sets the header as "Content-Type" and "application/json"
    And sets the body from the file "create_register.json"
    When user performs the post call
    Then verify user can get a status code of 200
    And verify the user can get a "id"

  Scenario Outline: Verify the user can login unsuccessfully
    Given user can calls the "/login" endpoint
    And sets the header as "Content-Type" and "application/json"
    And sets the body from the file "create_register.json" of credentials "<username>" and "<password>"
    When user performs the post call
    Then verify user can get a status code of 400
    And verify the user can get a message of "user not found"
    Examples:
      | username       | password  |
      | abc@gmail.com  | password  |
      | admin@admin.in | admin123  |
      | user@user.com  | user123   |
      | hello@user.in  | qwerty    |
      | Hi@qa.lo       | definable |

  Scenario: Verify the user can get list of resource successfully
    Given user can calls the "/unknown" endpoint
    When user performs the get call
    Then verify user can get a status code of 200
    And verify the user can get a "page"

  Scenario: Verify the user can update the data successfully
    Given user can calls the "/users/@id" endpoint
    And sets the header as "Content-Type" and "application/json"
    And sets the body from the file "update_user.json" for update
    When user performs the put call
    Then verify user can get a status code of 200
    And verify the user can get a "updatedAt"

  Scenario: Verify the user can get delete
    Given user can calls the "/users/@id" endpoint
    When user performs the delete call
    Then verify user can get a status code of 204
