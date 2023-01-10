package utils;

import org.bouncycastle.util.encoders.Base64;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;

public class SecretGetter {

   // private static final String PASSWORD_BASE64_DATA = "secret";

    public static String returnPass(String pass)  {
        if (pass != null && pass.equals("c2VjcmV0")) {
            return new String(Base64.decode(pass.getBytes(StandardCharsets.UTF_8)));
        }
        throw new IllegalArgumentException("Add correct password!");
    }
}
