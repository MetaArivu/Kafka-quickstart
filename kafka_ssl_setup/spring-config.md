# SSL Configuration in Spring

You can configure Spring Producer and Consumer with SSL as below.

Add following configuration in Kafka Configuration File in Producer and Consumer

- props.put(CommonClientConfigs.SECURITY_PROTOCOL_CONFIG, "SSL");	       
- props.put(SslConfigs.SSL_TRUSTSTORE_LOCATION_CONFIG, "/Users/ketangote/self-signed-cert/client.truststore.jks");
- props.put(SslConfigs.SSL_TRUSTSTORE_PASSWORD_CONFIG, "password");
- props.put(SslConfigs.SSL_KEY_PASSWORD_CONFIG, "password");
- props.put(SslConfigs.SSL_KEYSTORE_PASSWORD_CONFIG, "password");
- props.put(SslConfigs.SSL_KEYSTORE_LOCATION_CONFIG, "/Users/ketangote/self-signed-cert/server.keystore.jks");
