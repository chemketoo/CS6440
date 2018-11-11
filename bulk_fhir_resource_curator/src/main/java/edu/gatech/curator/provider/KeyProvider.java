package edu.gatech.curator.provider;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.security.Key;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;

@Component
public class KeyProvider {
    @Value("classpath:private_key.der")
    Resource privateKey;

    public Key getPrivateKey() throws IOException, NoSuchAlgorithmException, InvalidKeySpecException {
        Path exportOutputJsonPath = privateKey.getFile().toPath();
        byte[] privateKeyBytes = Files.readAllBytes(exportOutputJsonPath);

        PKCS8EncodedKeySpec spec = new PKCS8EncodedKeySpec(privateKeyBytes);
        KeyFactory kf = KeyFactory.getInstance("RSA");
        return kf.generatePrivate(spec);
    }
}
