package org.hfx.keycloak;

import org.keycloak.Config;
import org.hfx.keycloak.spi.SmsServiceProviderFactory;
import org.hfx.keycloak.spi.SmsService;
import org.keycloak.models.KeycloakSession;
import org.keycloak.models.KeycloakSessionFactory;

public class YunTongXunSmsServiceProviderFactory implements SmsServiceProviderFactory {
    @Override
    public SmsService create(KeycloakSession session) {
        return new YunTongXunSmsService(session);
    }

    @Override
    public void init(Config.Scope config) {

    }

    @Override
    public void postInit(KeycloakSessionFactory factory) {

    }

    @Override
    public void close() {

    }

    @Override
    public String getId() {
        return "YunTongXunSmsServiceProviderFactory";
    }
}
