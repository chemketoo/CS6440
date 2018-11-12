package edu.gatech.curator.provider;

import edu.gatech.curator.entity.SourceSystem;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.Date;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@RunWith(SpringRunner.class)
public class ClientAssertionProviderTest extends BaseProviderTest {

    @Autowired
    KeyProvider keyProvider;

    @Autowired
    ClientAssertionProvider subject;

    private SourceSystem sourceSystem;

    @Before
    public void setUp() throws Exception {
        Date defaultDate = new Date();
        sourceSystem = new SourceSystem("http://example.net", "client-id", "keyId", "https://example.net", defaultDate, null);
    }

    @Test
    public void create_returnsSignedJwtToken() throws IOException, InvalidKeySpecException, NoSuchAlgorithmException {
        String jws = subject.create(sourceSystem);

        Key key = keyProvider.getPrivateKey();
        Claims claims = Jwts.parser().setSigningKey(key).parseClaimsJws(jws).getBody();

        assertThat(claims.getAudience()).isEqualTo(sourceSystem.getLocation() + "/auth/token");
    }

}

