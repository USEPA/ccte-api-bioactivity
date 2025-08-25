#!/bin/bash
echo "*** Building app ..."
./mvnw clean package
echo "*** Selecting the staging space in epa-ccte organization..."
cf target -o epa-ccte -s staging
echo "*** Deleting previous app instance..."
cf delete -f -r ccte-bioactivity-stg
echo "*** Pushing the app with stg manifest file..."
cf push -f manifest-stg.yml --no-start
echo "*** Binding the database service to the app..."
cf bind-service ccte-bioactivity-stg ccte-db-stg
echo "*** Restaging the app to enable the server connection..."
cf restage ccte-bioactivity-stg
echo "*** Showing recent logs of the app..."
cf logs ccte-bioactivity-stg --recent
