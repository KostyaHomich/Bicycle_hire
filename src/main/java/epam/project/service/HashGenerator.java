package epam.project.service;


import epam.project.service.exception.ServiceException;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class HashGenerator {
    private static final Logger LOGGER=LogManager.getLogger(HashGenerator.class);

    public HashGenerator(){}

    public String encode(String value) throws ServiceException {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hash = digest.digest(value.getBytes(StandardCharsets.UTF_8));
            return String.valueOf(hash);
        }
        catch (NoSuchAlgorithmException e) {
        throw new ServiceException("Failed to get hash of value.",e);
        }

    }

}
