package uttug.bookmarkserver.global.googleVerifier;


import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdTokenVerifier;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.gson.GsonFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.Collections;


@Slf4j
@Configuration
public class TokenVerifier {

    @Value("${google.clientId}")
    private String clientId;

    private final NetHttpTransport transport = new NetHttpTransport();
    private final JsonFactory jsonFactory = new GsonFactory();


    public String tokenVerify(String idTokenString) throws GeneralSecurityException, IOException {

        log.info("idTokenString={}",idTokenString);

        GoogleIdTokenVerifier verifier = new GoogleIdTokenVerifier.Builder(transport, jsonFactory)
                .setAudience(Collections.singletonList(clientId))
                .build();

        GoogleIdToken idToken = verifier.verify(idTokenString);

        log.info("idToken={}",idToken);

        if (idToken != null) {

            GoogleIdToken.Payload payload = idToken.getPayload();

            String email = payload.getEmail();

            String name = (String) payload.get("name");
            String pictureUrl = (String) payload.get("picture");

            log.info("email={}", email);
            log.info("name={}", name);
            log.info("pictureUrl={}", pictureUrl);

            return email;
        }
        else {
            return null;
        }
    }

}
