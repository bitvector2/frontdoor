package org.bitvector.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.gateway.webflux.ProxyExchange;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@SpringBootApplication
public class GatewayApp {

    public static void main(String[] args) {
        SpringApplication.run(GatewayApp.class, args);
    }

    @GetMapping("/posts/meta")
    public Mono<ResponseEntity<byte[]>> doPostsMeta(ProxyExchange<byte[]> proxy) {
        return proxy.uri("http://microservice4.default.svc.cluster.local:8080/posts/meta").get();
    }

}
