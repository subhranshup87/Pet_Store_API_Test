#!/bin/bash

# Build the Docker image
docker-compose build

# Run tests and generate report
docker-compose up --abort-on-container-exit

# Open the report (MacOS specific)
open allure-report/index.html