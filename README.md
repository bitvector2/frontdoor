Combined Istio / Nginx / Spring Cloud Gateway Front Door Layer

 - Istio Ingress Gateway terminates inbound TLS requests from internet and routes to either Nginx or 
 Spring Cloud Gateway
 
    - Static content is served from Nginx
    - API calls are served from Spring Cloud Gateway
    - Traffic pathway appears entirely inline to avoid use of CORS

 - Istio provides mTLS between all Envoy:Envoy connections automatically
 - Istio provides client side load balancing, retry and circuit breaker logic
 - Istio Egress Gateway provides retry and circuit breaker logic to outside world
  
Testing Notes

- export INGRESS_PORT=$(kubectl -n istio-system get service istio-ingressgateway -o jsonpath='{.spec.ports[?(@.name=="http2")].nodePort}')
- export SECURE_INGRESS_PORT=$(kubectl -n istio-system get service istio-ingressgateway -o jsonpath='{.spec.ports[?(@.name=="https")].nodePort}')
- export INGRESS_HOST=$(kubectl get po -l istio=ingressgateway -n istio-system -o 'jsonpath={.items[0].status.hostIP}')
- curl --include --header host:frontdoor.bitvector.org http://$INGRESS_HOST:$INGRESS_PORT/
- curl --include --header host:frontdoor.bitvector.org --resolve frontdoor.bitvector.org:$SECURE_INGRESS_PORT:$INGRESS_HOST --cacert 2_intermediate/certs/ca-chain.cert.pem https://frontdoor.bitvector.org:$SECURE_INGRESS_PORT/
