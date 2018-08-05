Combined Nginx / Spring Cloud Gateway

 - Istio Ingress Gateway terminates inbound TLS requests from internet and routes to Combined Nginx / 
 Spring Cloud Gateway layer
 
    - Static content is served from Nginx
    - RESTful API calls are routed to Spring Cloud Gateway
        - Spring Cloud Gateway provides HTTP request/response mutations
        - Spring Cloud Gateway aggregrates many microservices running in many DC's
    - Both Nginx and Gateway are combined within the same Pods and communicate via the loopback interface 
    - Traffic pathway is entirely inline to avoid use of CORS

 - Istio provides mTLS between all Envoy connections automatically
 - Istio provides client side load balancing, retrying and circuit breaker logic between inside of mesh endpoints
 - Istio Egress Gateway provides retrying and circuit breaker logic to outside of mesh endpoints
  
Testing Notes

- export INGRESS_PORT=$(kubectl -n istio-system get service istio-ingressgateway -o jsonpath='{.spec.ports[?(@.name=="http2")].nodePort}')
- export SECURE_INGRESS_PORT=$(kubectl -n istio-system get service istio-ingressgateway -o jsonpath='{.spec.ports[?(@.name=="https")].nodePort}')
- export INGRESS_HOST=$(kubectl get po -l istio=ingressgateway -n istio-system -o 'jsonpath={.items[0].status.hostIP}')
- curl --include --header host:frontdoor.bitvector.org http://$INGRESS_HOST:$INGRESS_PORT/
- curl --include --header host:frontdoor.bitvector.org --resolve frontdoor.bitvector.org:$SECURE_INGRESS_PORT:$INGRESS_HOST --cacert 2_intermediate/certs/ca-chain.cert.pem https://frontdoor.bitvector.org:$SECURE_INGRESS_PORT/
