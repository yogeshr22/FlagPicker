Below are the service URLs and their features
1)	List All Flag Details Service (POST, application/json)
  a)	http://localhost:8080/api/all
  	The application allows a user to fetch all of the flag details in the mentioned JSON format
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
  	Used Mockito Framework to cover unit testing for service class
    
 Bonus :
  1) Implemented the Mongo DB for Persistance and Retrieval
  2) Implemented Spring Actuator to Monitor the application
  3) Metrics Service to show the search requests
  4) Audit logging has been implemented
