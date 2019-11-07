# keycloak-phone-authenticator-yuntongxun-sms

The implementation of [keycloak-phone-authenticator](https://github.com/FX-HAO/keycloak-phone-authenticator) for 
[云通讯](http://www.yuntongxun.com/doc.html) SMS.

To install the SMS Authenticator one has to:

* Build and package the project:
  * `$ mvn package`

* Add the jar to the Keycloak server:
  * `$ cp target/keycloak-phone-authenticator-yungtongxun-sms-*.jar _KEYCLOAK_HOME_/providers/`

## Configuration

1. via `$HOME/yuntongxun4j.properties`  

```
account_sid=xxx
auth_token=xxx
app_id=xxx
template_id=xxx
```

See [yuntongxun4j](https://github.com/FX-HAO/yuntongxun4j) for more details.

## deploy modules

Firstly, adding the modules into your wildfly server.

```
> $KEYCLOAK_HOME/bin/jboss-cli.sh --command="module add --name=com.hfx.keycloak-phone-authenticator-yuntongxun-sms --resources=target/keycloak-phone-authenticator-yuntongxun-sms-1.0.0-SNAPSHOT.jar --dependencies=org.keycloak.keycloak-server-spi,com.hfx.keycloak-phone-authenticator,yuntongxun4j,com.squareup.okhttp3"
> $KEYCLOAK_HOME/bin/jboss-cli.sh --command="module add --name=com.hfx.keycloak-phone-authenticator --resources=~/.m2/repository/com/hfx/keycloak-phone-authenticator/1.0.0-SNAPSHOT/keycloak-phone-authenticator-1.0.0-SNAPSHOT.jar --dependencies=org.keycloak.keycloak-core,org.keycloak.keycloak-common,org.hibernate,org.keycloak.keycloak-server-spi,org.keycloak.keycloak-server-spi-private,org.keycloak.keycloak-services,org.keycloak.keycloak-model-jpa,org.jboss.logging,javax.api,javax.ws.rs.api,javax.transaction.api,javax.persistence.api,org.jboss.resteasy.resteasy-jaxrs,org.apache.httpcomponents,org.apache.commons.lang"
> $KEYCLOAK_HOME/bin/jboss-cli.sh --command="module add --name=yuntongxun4j --resources=~/.m2/repository/org/yuntongxun4j/yuntongxun4j/1.0-SNAPSHOT/yuntongxun4j-1.0-SNAPSHOT.jar --dependencies=com.squareup.okhttp3,javax.xml.bind.api,com.google.code.gson"
```

Then you need register your modules with keycloak. Editing the keycloak-server subsystem section of standalone.xml, standalone-ha.xml, or domain.xml:

```xml
<subsystem xmlns="urn:jboss:domain:keycloak-server:1.1">
    <web-context>auth</web-context>
    <providers>
        <provider>module:com.hfx.keycloak-phone-authenticator</provider>
        <provider>module:com.hfx.keycloak-phone-authenticator-yuntongxun-sms</provider>
        <provider>module:yuntongxun4j</provider>
    </providers>
    ...
```

Or you can register the modules with `jboss-cli.sh`: `$KEYCLOAK_HOME/bin/jboss-cli.sh --file=cli/keycloak-phone-authenticator-yuntongxun-sms-config.cli `

Finally, you need copy the theme files into `$KEYCLOAK_HOME/themes/base/login`:
```
> cp src/main/resources/theme-resources/templates/* $KEYCLOAK_HOME/themes/base/login/
> cat messages/messages_en.properties >> $KEYCLOAK_HOME/theme/base/login/messages/messages_en.properties
> cat messages/messages_zh_CN.properties >> $KEYCLOAK_HOME/theme/base/login/messages/messages_zh_CN.properties
```
