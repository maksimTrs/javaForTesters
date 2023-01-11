package utils;

import org.bouncycastle.util.encoders.Base64;

import java.nio.charset.StandardCharsets;

public class SecretGetter {

    // private static final String PASSWORD_BASE64_DATA = "secret";

    private static String returnPass(String pass) {
        if (pass != null && pass.equals("c2VjcmV0")) {
            System.out.println("*** Password validated without errors ***");
            return new String(Base64.decode(pass.getBytes(StandardCharsets.UTF_8)));
        }
        throw new IllegalArgumentException("***** ERROR: Add correct password!!! *****");
    }

    public static String handlingPassword(String pass) {
        System.out.println("*** Handle Base64 password ***");
        return returnPass(pass);
    }
}
