#!/bin/bash

keyStore="${TMP}/testing.jks"

if [ -f "${keyStore}" ]; then
     rm -vf "${keyStore}"
fi

keytool -genkeypair -keyalg RSA -keysize 2048 -keystore "${keyStore}" -alias testing \
   -dname "CN=testing,OU=testing,O=testing,C=US" -storepass testing -keypass testing \
   -validity 3650 -ext KeyUsage=digitalSignature,dataEncipherment,keyEncipherment,keyAgreement \
   -ext ExtendedKeyUsage=serverAuth,clientAuth -ext SubjectAlternativeName:c=DNS:localhost
