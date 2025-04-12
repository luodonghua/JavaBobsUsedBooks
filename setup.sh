#!/usr/bin/bash

sudo dnf install -y java-17-amazon-corretto-devel-17 maven-amazon-corretto17 maven zip unzip

# zip -r ../JavaBobsUsedBooks.zip . -x "*.git*"
wget https://github.com/luodonghua/JavaBobsUsedBooks/raw/refs/heads/main/JavaBobsUsedBooks.zip 
unzip JavaBobsUsedBooks.zip
cd JavaBobsUsedBook

sqlplus sys/Welcome123_@localhost/XEPDB1 AS SYSDBA << EOD
@src/main/resources/db/oracle/setup.sql
EOD

sqlplus demo/Welcome123_@localhost/XEPDB1<< EOD
@src/main/resources/db/oracle/schema_oracle.sql
@src/main/resources/db/oracle/data_oracle_jpa.sql
EOD

# mvn compile
# mvn spring-boot:run

