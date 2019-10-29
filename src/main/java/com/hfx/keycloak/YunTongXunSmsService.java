package com.hfx.keycloak;

import okhttp3.Response;
import com.hfx.keycloak.spi.SmsService;
import org.keycloak.models.KeycloakSession;
import yuntongxun4j.YunTongXun;
import yuntongxun4j.YunTongXunFactory;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class YunTongXunSmsService implements SmsService<Object> {
    private final KeycloakSession session;

    private static YunTongXunFactory factory;
    private static YunTongXun client;

    static {
        factory = new YunTongXunFactory();
        client = factory.getInstance();
    }

    public YunTongXunSmsService(KeycloakSession session) {
        this.session = session;
    }

    @Override
    public void close() {
    }

    @Override
    public boolean send(String phoneNumber, Map<String, ? super Object> params) throws SmsException {
        String templateId = (String)params.get("templateId");
        try {
            Response res = client.sms().sendSMS(templateId, phoneNumber, params);
            if (!res.isSuccessful()) {
                throw new SmsException(res.body().string());
            }
        }
        catch (IOException e) {
            throw new SmsException(e.getMessage());
        }
        return true;
    }

    @Override
    public boolean sendVerificationCode(VerificationCodeRepresentation rep, Map<String, ? super Object> params) throws SmsException {
        String templateId = System.getProperty("template_id");
        List<String> extraData = new ArrayList<>();
        extraData.add(rep.getCode());
        params.put("datas", extraData);
        try {
            Response res = client.sms().sendSMS(templateId, rep.getPhoneNumber(), params);
            if (!res.isSuccessful()) {
                throw new SmsException(res.body().string());
            }
        }
        catch (IOException e) {
            throw new SmsException((e.getMessage()));
        }
        return true;
    }
}
