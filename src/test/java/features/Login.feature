Feature: Validate Google APIs

  @AddPlace @Regression
  Scenario Outline: Verify if place is successfully added in AddPlace API
    # Scenario: Verify if place is successfully added in AddPlace API
    Given Add Place Payload with "<Name>" ,"<Language>" and "<Address>"
    When User calls "addPlaceAPI" with "get" http request
    Then API Response is generated successfully with status code 200
    And "scope" in response body is "APP"
    And "status" in response body is "OK"
    And verify place_Id created maps to "<Name>" using "getPlaceAPI"

    Examples: 
      | Name  | Language | Address              |
      | House | English  | Uttrakhand dehradun  |
      | Mouse | Hindi    | S23 Japan            |
      | Maker | French   | B-101 A/2 Netherland |
      | Madam | Russian  | @23 (67) Austraila   |

  @DeletePlace @Regression
  Scenario: Verify if delete place api is working
    Given Delete Place API payload
    When User calls "deletePlaceAPI" with "delete" http request
    Then API Response is generated successfully with status code 200
    And "status" in response body is "OK"
