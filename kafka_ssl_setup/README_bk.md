# Enable Kafka SSL 
Setup kafka with self signed certificate

#### 1. Create own private Certificate Authority (CA)

openssl req -new -newkey rsa:4096 -days 365 -x509 -subj “/CN=kafka” -keyout ca-key -out ca-cert -nodes

#### 2. Create Kafka Server Certificate and store in KeyStore

/Library/Java/JavaVirtualMachines/jdk1.8.0_202.jdk/Contents/Home/jre/bin/keytool -genkey -keystore kafka.server.keystore.jks -validity 365 -storepass password -keypass password -dname “CN=localhost” -storetype pkcs12

#### 3. verify certificate

/Library/Java/JavaVirtualMachines/jdk1.8.0_202.jdk/Contents/Home/jre/bin/keytool -list -v -keystore kafka.server.keystore.jks

#### 4.Create Certificate signed request (CSR)


/Library/Java/JavaVirtualMachines/jdk1.8.0_202.jdk/Contents/Home/jre/bin/keytool -keystore kafka.server.keystore.jks -certreq -file cert-file -storepass password -keypass password


#### 5. Get CSR Signed with the CA


/Library/Java/JavaVirtualMachines/jdk1.8.0_202.jdk/Contents/Home/jre/bin/keytool -keystore kafka.server.keystore.jks -certreq -file cert-file -storepass password -keypass password



#### 6. verify certificate


/Library/Java/JavaVirtualMachines/jdk1.8.0_202.jdk/Contents/Home/jre/bin/keytool -printcert -v -file cert-file-signed


#### 7. Import CA certificate in KeyStore

/Library/Java/JavaVirtualMachines/jdk1.8.0_202.jdk/Contents/Home/jre/bin/keytool -keystore kafka.server.keystore.jks -alias CARoot -import -file ca-cert -storepass password -keypass password -noprompt



#### 8. Import CA certificate In TrustStore


/Library/Java/JavaVirtualMachines/jdk1.8.0_202.jdk/Contents/Home/jre/bin/keytool -keystore kafka.server.keystore.jks -import -file cert-file-signed -storepass password -keypass password -noprompt








