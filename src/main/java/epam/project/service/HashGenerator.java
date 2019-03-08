package epam.project.service;


import epam.project.service.exception.ServiceException;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class HashGenerator {
    private static final Logger LOGGER = LogManager.getLogger(HashGenerator.class);

    public HashGenerator() {
    }

    public String encode(String value) throws ServiceException {

        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] buffer = value.getBytes("UTF-8");
            md.update(buffer);
            byte[] digest = md.digest();
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < digest.length; i++) {
                sb.append(Integer.toString((digest[i] & 0xff) + 0x100, 16).substring(1));
            }
            return sb.toString();
        } catch (NoSuchAlgorithmException | UnsupportedEncodingException e) {
            throw new ServiceException("Failed to get hash of value.", e);
        }

    }

}
