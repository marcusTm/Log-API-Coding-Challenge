# Log-API-Coding-Challenge


## Assumptions:
- I can just publish the entries to some RabbitMQ exchange
- I will for simplicity just use h2 as the database (can easily be changed)
- I will skip implementation of integration and component tests and just focus on unit tests.
(I did the integration tests manually instead).


## Process:
1) Create spring boot app
2) Implement REST API + tests 
3) Store log entries in database (h2)
4) Publish log entries to RabbitMQ exchange
5) Implement load tests using JMeter (we could also implement this using threads in java)
6) Based on load tests maybe change implementation to be async (didn't get to do this)


## Load test (using JMeter)

I was able to do a quick load test of the application using JMeter.
Initial test: 
At the first couple of tests the latency time was on average 11 ms for each request.

**Test 1:**
Each thread (users) sends 1 request. The ramp-up period is 1 s.
For the load test with 10, 100, and 1000 users (threads) the latency is around to 2-3 ms.
The app seems to be able to handle this load successfully but this is also because we use h2 which is an in-memory database
and we run rabbitMQ locally. Thus we avoid the network. 

**Test 2:**
To make sure the threads do send the requests concurrently we change it so each thread sends 5 requests instead of 1.
Now we can see that the latency increases quite drastically. 
For the test with 100 users the average is still 2 ms. 
However, for the test with 1000 users the average has now changed to 129 ms. 
Thus we can see how the application starts to struggle. However, the application is still able to handle the load successfully.

## Running rabbitmq locally

Note on how to run RabbitMQ server locally (using docker):
```docker run -it --rm --name rabbitmq -p 5672:5672 -p 15672:15672 rabbitmq:3.11-management```

Credentials: 
- user: guest
- password: guest
