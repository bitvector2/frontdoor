package org.bitvector.gateway;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Component
public class AddWcsTokensFromJwtGatewayFilterFactory extends AbstractGatewayFilterFactory<AddWcsTokensFromJwtGatewayFilterFactory.Config> {
    private static final Log log = LogFactory.getLog(AddWcsTokensFromJwtGatewayFilterFactory.class);
    private static final String HEADER_NAME = "headerName";

    public AddWcsTokensFromJwtGatewayFilterFactory() {
        super(Config.class);
    }

    @Override
    public List<String> shortcutFieldOrder() {
        return Arrays.asList(HEADER_NAME);
    }

    @Override
    public GatewayFilter apply(Config config) {
        return (exchange, chain) -> {
            String headerName = config.getHeaderName();

            String headerValue = exchange
                    .getRequest()
                    .getHeaders()
                    .getFirst(headerName);

            String jwt = Util.getJwt(headerName, headerValue);

            ServerHttpRequest request = exchange
                    .getRequest()
                    .mutate()
                    .header("WCS_TOKEN", jwt)
                    .header("WCS_SECURE_TOKEN", jwt)
                    .build();

            log.info("AddWcsTokensFromJwt Ran");

            return chain.filter(exchange
                    .mutate()
                    .request(request)
                    .build()
            );
        };
    }

    public static class Config {
        private String headerName;

        public String getHeaderName() {
            return headerName;
        }

        public void setHeaderName(String headerName) {
            this.headerName = headerName;
        }
    }
}
