Feature: Validate the CRUD operation of the incident servicenow table api

Background:
Given User should set base uri "https://dev324941.service-now.com" of the service now table api
And user should set the base path "/api/now/table" of the service now table api
And user should set username as "admin" and password as "e5!pRsPN%lH5" for the service now api

@smoke
Scenario: Validate user should able to fetch all the records from the incident table api
When user should hit the get method of the incident table api
Then user should see the success status code as a 200
And user should see the success line as a "OK"
And user should see the respone as a "JSON" format

@smoke 
Scenario: Validate user should able to fetch all the records from the incident table api in xml format
Given user should set header key as "Accept" and value as "application/xml"
When user should hit the get method of the incident table api
Then user should see the success status code as a 200
And user should see the success line as a "OK"
And user should see the respone as a "XML" format

@regression
Scenario: Validate user should able to fetch a single record from the incident table api
Given user should give the record sysId "46b66a40a9fe198101f243dfbc79033d" to fetch single record
When user should hit the get method of the incident table api to fetch single record
Then user should see the success status code as a 200
And user should see the success line as a "OK"
And user should see the respone as a "JSON" format