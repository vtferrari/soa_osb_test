#!/bin/bash

exec $(type -p java) -jar /opt/app.jar \
--api.hotels=https://cvcbackendhotel.herokuapp.com/hotels \
--server.port=8080
