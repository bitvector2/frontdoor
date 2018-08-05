Combined Nginx / Spring Cloud Gateway

 - Istio Ingress Gateway terminates inbound TLS requests from internet and routes to Combined Nginx / 
 Spring Cloud Gateway layer
 
    - Static content is served from Nginx
    - HTTP compression is provided by Nginx on appropriate mime types
    - Microservice API calls are routed to Spring Cloud Gateway
        - Spring Cloud Gateway provides HTTP request/response mutations
        - Spring Cloud Gateway aggregrates many microservices running in many DC's
    - Both Nginx and Gateway are combined within the same Pods and communicate via the loopback interface 

 - Istio provides mTLS between all Envoy connections automatically
 - Istio provides client side load balancing, retrying and circuit breaker logic between inside of mesh endpoints
 - Istio Egress Gateway provides retrying and circuit breaker logic to outside of mesh endpoints
  