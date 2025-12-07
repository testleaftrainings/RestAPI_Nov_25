Feature: Validate the CRUD operation of the incident servicenow table api

Background:
Given User should set base uri "https://dev324941.service-now.com" of the service now table api
And user should set the base path "/api/now/table" of the service now table api
And user should set username as "admin" and password as "e5!pRsPN%lH5" for the service now api

@smoke
Scenario: Validate user should able to fetch all the records from the incident table api
When user should hit the get method of the incident table api
Then user should see the success response with the expected value
| statusCode | statusLine | responseFormat |
| 200        | OK         | JSON           |

@smoke 
Scenario: Validate user should able to fetch all the records from the incident table api in xml format
Given user should set header key as "Accept" and value as "application/xml"
When user should hit the get method of the incident table api
Then user should see the success response with the expected value
| statusCode | statusLine | responseFormat |
| 200        | OK         | XML            |

@regression
Scenario: Validate user should able to fetch a single record from the incident table api
Given user should give the record sysId "46b66a40a9fe198101f243dfbc79033d" to fetch single record
When user should hit the get method of the incident table api to fetch single record
Then user should see the success response with the expected value
| statusCode | statusLine | responseFormat |
| 200        | OK         | JSON           |

Scenario Outline: Validate user should create multiple new incident records
Given user should set header key as "Content-Type" and value as "application/json"
When user should create request payload based on the given data
| <shortDescription> | <categoryName> |
And user should hit the post method of incident table api to create new record
Then user should see the success response with the expected value
| statusCode | statusLine | responseFormat |
| 201        | Created    | JSON           |

Examples:
| shortDescription | categoryName |
| TCS              | software     |
| CTS              | hardware     |
| infosys          | inquiry      |