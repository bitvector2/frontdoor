package org.bitvector.gateway;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties
class UriConfiguration {

    private String httpbinUrl = "http://httpbin.org:80";

    public String getHttpbinUrl() {
        return httpbinUrl;
    }

    public void setHttpbinUrl(String httpbinUrl) {
        this.httpbinUrl = httpbinUrl;
    }

}
