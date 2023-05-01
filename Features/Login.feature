Feature: Login
@sanity
  Scenario: Login with valid credentials
     Given User Launch Browser
     And opens URL "http://localhost/opencart/upload/"
     When user navigate to MyAccountPage
     And click on login
     And user enters email as "abcabc@gmail.com" and password as "abcabc"
     And click on login button
     Then user navigates to My account page
