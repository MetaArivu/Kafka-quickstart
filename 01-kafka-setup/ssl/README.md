# Kafka SSL Setup

https://kafka.apache.org/documentation/#security_ssl

## Generate KeyStore 

Generate KeyStore File using below command

- keytool -keystore server.keystore.jks -alias localhost -validity 365 -genkey -keyalg RSA
- Enter all the details like name, org, city, state, country etc

## Generate CA

- openssl req -new -x509 -keyout ca-key -out ca-cert -days 365 -subj "/CN=local-security-CA"

## CSR (Certificate Signing Request)

- keytool -keystore server.keystore.jks -alias localhost -certreq -file cert-file
- This command will create cert-file

## Sign the certificate

- openssl x509 -req -CA ca-cert -CAkey ca-key -in cert-file -out cert-signed -days 365 -CAcreateserial -passin pass:password

Note: I have entered password as "password", you need to change above command with password accordingly

- keytool -printcert -v -file cert-signed (This will print content inside cert-signed)

## Add Signed Cert into KeyStore File
- keytool -keystore server.keystore.jks -alias CARoot -import -file ca-cert
- keytool -keystore server.keystore.jks -alias localhost -import -file cert-signed

## Generate the TrustStore
- keytool -keystore client.truststore.jks -alias CARoot -import -file ca-cert

## Update Kafka config file

Note: Below configuration are done in server.properties file

- listeners=PLAINTEXT://:9095,SSL://:9096
- advertised.listeners=PLAINTEXT://localhost:9095,SSL://localhost:9096
- ssl.keystore.location=<location>/server.keystore.jks
- ssl.keystore.password=password
- ssl.key.password=password

## Connect Kafka Producer and Consumer to SSL Kafka

Create file called client-secure.properties in kafka config folder and add below config

- security.protocol=SSL
- ssl.truststore.location=<location>/client.truststore.jks
- ssl.truststore.password=password
- ssl.truststore.type=JKS

### Connect to Producer

./bin/kafka-console-producer.sh --broker-list localhost:9096 --topic test-topic --producer.config config/client-secure.properties

### Connect to Consumer

./bin/kafka-console-consumer.sh --bootstrap-server localhost:9096 --topic test-topic --consumer.config config/client-secure.properties





