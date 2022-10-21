# Apple rest client

The implementation of the Rest Client 
is to allow callers to communicate to your rest server. 
The Apple rest client implements the Apple interface.

Each method will have to do the following

* Accept the POJOs and convert them to the JSON classes.
* Use the Loadbalancer Client to send the request to the rest service
* Convert the returned JSON responses to the POJOs
* Return to the caller.

From the caller's perspective, they are not aware how the call was actually executed.
