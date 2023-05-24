Feature: Api Assesment test

  Scenario: Get the api call and verify the details and response code
    Given Get the userdetails from the given api call
    Then Get the Firstname and Email of users for the Get api call
    Then Get the userdetails by ID 2

  Scenario: Create the userdetails by calling post api call
    Given Post the userdetails by given api
    Then Verify the userdetails for post api

  Scenario: Update the created userdetails from the api 
    Given Update the userdetails by given api for the existing user
    Then Verify the userdetails after updating the details
    