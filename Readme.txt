Below are the service URLs and their features
1)	Upload Service (POST, application/json)
  a)	http://localhost:8080/api/uploadflags
  	The application allows a user to upload the flag details in the mentioned JSON format
  	The user can keep on adding the continents to the application
    If any invalid input provided, the system will not accept the data
2)	Search Service (GET)
  a)	http://localhost:8080/api/search?keyword=UK
    The user is able to search the flag details by passing either the continent or country name.
    If the continent is provided, then it will return all of the country and flag in respect to it.
    If the country is provided, it will return the flag details 
    Along with that, it will return the number of times, the user has viewed it
3)	Metrics Service (GET)
  a)	http://localhost:8080/api/metrics
    	The metrics service is used to show the number of countries available in the system with their number of search requests.
      The most number of viewed will be in the sorted order.
Coverage Of work :
    Implemented the application using Spring Boot
  	Covered the mentioned requirements
   	Added the Logger details for Audit purposes
  	Used collections to implement the functionality, but provided the DAO design to extend it to a database
  	Used Mockito Framework to cover unit testing for service class
