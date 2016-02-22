# usrmgr
GEM User Management System[Alpha version]

##API Specifications

* /register:  
    * Desc: create new user.
    * Receive: a POST request containing an UserInfo DTO as JSON, which contains registering info.
    * Return: nothing and status 200 if succeed
* /login: 
    * Desc: login.
    * Receive: a POST request containing an UserInfo DTO as JSON, which contains credential info.
    * Return: a token string and status 200 if succeed
* /user:
    * Desc: list all registered users, for testing purposes only.
    * Receive: a GET request with a field named "token" in header. This field contains the authentication token received by login.
    * Return: a list of users as JSON if succeed
* /logoutuser:
    * Desc: logout.
    * Receive: a GET request with a field named "token" in header. This field contains the authentication token received by login.
    * Return: nothing and status 200 if succeed
