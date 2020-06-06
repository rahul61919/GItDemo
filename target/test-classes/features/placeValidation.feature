Feature: Place Validation
@AddPlace @Regression
Scenario Outline: Verify if place is succesfully added using addPlace API
Given Add Place Payload with "<name>" "<language>" "<address>"
When user calls "AddPlaceAPI" API with "POST" HTTP request
Then The API call get success with status code 200
And "status" in response body is "OK"
And "scope" in response body is "APP"
And verify place_Id created maps to "<name>" using "GetPlaceAPI"


Examples:

|name 			| language| address            |
|Godrej Frontier| English | sector -80, Gurgaon|
 #| Mapsko Tech   | English | sector 83, Gurgaon |
 
 @DeletePlace @Regression
 Scenario: Verify delete place functionality
 Given Delete place payload
 When user calls "DeletePlaceAPI" API with "POST" HTTP request
 Then The API call get success with status code 200
And "status" in response body is "OK"
#adding a comment for git push/pull