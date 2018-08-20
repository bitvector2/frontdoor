package org.bitvector.gateway;

import org.springframework.stereotype.Component;

@Component
public class GatewayUtilities {
    public static String getJwt(String key, String value) {
        String jwt = "";

        if (key == null || value == null) {
            return jwt;
        }

        if (key.toLowerCase().equals("authorization")) {
            String scheme = value.split("\\s+")[0];

            if (scheme.toLowerCase().equals("bearer")) {
                jwt = value.split("\\s+")[1];
            }
        }

        if (value.split("\\s+").length == 1) {
            jwt = value;
        }

        return jwt;
    }
}
