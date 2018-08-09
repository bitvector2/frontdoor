package org.bitvector.gateway;

public class Util {
    public static String getJwt(String headerName, String headerValue) {
        String jwt = "";

        if (headerName == null || headerValue == null) {
            return jwt;
        }

        if (headerName.toLowerCase().equals("authorization")) {
            String scheme = headerValue.split("\\s+")[0];

            if (scheme.toLowerCase().equals("bearer")) {
                jwt = headerValue.split("\\s+")[1];
            }
        }

        if (headerValue.split("\\s+").length == 1) {
            jwt = headerValue;
        }

        return jwt;
    }
}
