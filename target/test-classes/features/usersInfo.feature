Feature: Validating Users API

@GetUsers
Scenario: Verify all users are being retruned by GetUsers API

Given baseURL and  API resource
When  user calls "GetUserAPI" with "GET" http request
Then the API call got success with status code 200
And  the API response contain users count 1000 


@GetLondonUsers
Scenario: Verify the list of London users
Given baseURL and  CityUserAPI resource and city is "London"
When  user calls "GetUsersByCityAPI" with "GET" http request
Then the API call got success with status code 200
And  the API response contain users count 6 

@GetUsersbyId
Scenario Outline: Verify the all London users are being returned by GetUserByIdAPI
Given baseURL and  API resource and "<userId>" of London users
When  user calls "GetUserByIdAPI" with "GET" http request
Then the API call got success with status code 200

Examples:

		|userId |
		|135	|
		|396	|
		|520	|
		|658	|
		|688	|
		|794	|


