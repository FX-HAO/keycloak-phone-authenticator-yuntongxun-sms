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
