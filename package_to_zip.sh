#!/usr/bin/bash

## Clean up previous zip files
rm ../JavaBobsUsedBooks.zip
rm JavaBobsUsedBooks.zip

# "mvn clean" is a Maven command that cleans the project by deleting the target directory and its contents. 
# This directory typically contains compiled classes, JAR files, and other build artifacts from previous builds 
mvn clean

## Create ZIP file for the current git 

zip -r ../JavaBobsUsedBooks.zip . -x "*.git*" -x setup.sh -x package_to_zip.sh

# Copy to git folder, later can be resync to github
mv ../JavaBobsUsedBooks.zip .

# In future, to unzip, use: 
# unzip JavaBobsUsedBooks.zip -d JavaBobsUsedBooks