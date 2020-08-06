# infrrd
Assignment for Infrrd

We have created a banking application with basic transactional operations (deposit, withdraw, checkbalance) using spring boot framework.

The methods deposit and withdraw are thread safe, only a single request can access the API at a single time.(Marked the service requests as synchronized)

TO avoid multiple concurrent requests at the controller, we have used the spring library Bucket4j, which allows us to have a granular control on API's which hit the server at the same time.
Currently in the code, we have limited the API to handle only 1 request per 2 seconds. Failing to do so will throw a 429(TOO_MANY_REQUESTS) error with message("Rejecting the concurrent request"). Again this code is highly customisable.

For persistence, we have used JAVA collections to create basic account structure.
