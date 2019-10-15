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

```
> $KEYCLOAK_HOME/bin/jboss-cli.sh --command="module add --name=org.hfx.keycloak-phone-authenticator-yuntongxun-sms --resources=target/keycloak-phone-authenticator-yuntongxun-sms-7.0.0-SNAPSHOT.jar --dependencies=org.keycloak.keycloak-server-spi,org.hfx.keycloak-phone-authenticator,yuntongxun4j,com.squareup.okhttp3"
> $KEYCLOAK_HOME/bin/jboss-cli.sh --command="module add --name=org.hfx.keycloak-phone-authenticator --resources=~/.m2/repository/org/hfx/keycloak-phone-authenticator/7.0.0-SNAPSHOT/keycloak-phone-authenticator-7.0.0-SNAPSHOT.jar --dependencies=org.keycloak.keycloak-core,org.hibernate,org.keycloak.keycloak-server-spi,org.keycloak.keycloak-server-spi-private,org.keycloak.keycloak-services,org.keycloak.keycloak-model-jpa,org.jboss.logging,javax.api,javax.ws.rs.api,javax.transaction.api,javax.persistence.api"
> $KEYCLOAK_HOME/bin/jboss-cli.sh --command="module add --name=yuntongxun4j --resources=~/.m2/repository/org/yuntongxun4j/yuntongxun4j/1.0-SNAPSHOT/yuntongxun4j-1.0-SNAPSHOT.jar --dependencies=com.squareup.okhttp3,javax.xml.bind.api,com.google.code.gson"
```

Then you need register your modules with keycloak. Editing the keycloak-server subsystem section of standalone.xml, standalone-ha.xml, or domain.xml:

```xml
<subsystem xmlns="urn:jboss:domain:keycloak-server:1.1">
    <web-context>auth</web-context>
    <providers>
        <provider>module:org.hfx.keycloak-phone-authenticator</provider>
        <provider>module:org.hfx.keycloak-phone-authenticator-yuntongxun-sms</provider>
        <provider>module:yuntongxun4j</provider>
    </providers>
    ...
```

Or you can register the modules with `jboss-cli.sh`: `$KEYCLOAK_HOME/bin/jboss-cli.sh --file=cli/keycloak-phone-authenticator-yuntongxun-sms-config.cli `
