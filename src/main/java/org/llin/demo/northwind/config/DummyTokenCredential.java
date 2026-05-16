package org.llin.demo.northwind.config;

import java.time.OffsetDateTime;

import com.azure.core.credential.TokenCredential;
import com.azure.core.credential.TokenRequestContext;
import com.azure.core.credential.AccessToken;          

import reactor.core.publisher.Mono;

public class DummyTokenCredential implements TokenCredential {

    @Override
    public Mono<AccessToken> getToken(TokenRequestContext tokenRequestContext) {
        // Dummy token that never expires for local development
        return Mono.just(new AccessToken("dummy-token", OffsetDateTime.now().plusDays(365)));
    }
}