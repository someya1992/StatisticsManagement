### StatisticsManagement

  A HTTP service for recording of statistics of some arbitrary data over a closed period of time.
 
 
### Decisions taken : 
 *  If event received is older than 60 sec or event time > current time, then an exception is thrown which maps to 204 status
 *  An error is thrown when any of the fields is null in the event
 *  If received event is  successfully processed then the service returns 202 status code
 *  If there were no events in last 60 seconds then calculating stats will throw an exception and return a status code 204
 *  Linked list to used to store and process the events 
 *  Delete data older than 60 seconds since we require the stats for last 60 sec. Hence we will have O(1) space
    complexity as we delete older events (> 60 seconds) after a fixed amount of time.
 *  BigDecimal is used to large decimal numbers (10 fractional digits)  and long to  store the sum of ints
 
### Improvements

  * reponse and request must be in json(better approach)
 
###  Dependencies :-

  1. 'org.projectlombok'
  
### APIs

  POST - /event :- This API consumes the event timestamp and x,y values
	
  *Request* :

 	`1607341341814,0.0442672968,1282509067`
  
  *Response*

	`HTTP Status 202 is received`
										  
   GET - /stats :- This API return the average and sum of x values and y values for the last 60 seconds.
	
  *Response* :

 	`7,1.1345444135,0.1620777734,11824011150,1689144450.000`

 #### Building and running the project

 1. `mvn clean` and `mvn install`
 
 2.  In the target folder executable jar is created, Run as
     `java -jar <jar_name>.jar`
  
 3. In Postman or any other REST client hit the APIs mentioned above :- http://localhost:8090/{request}
  

 
  
