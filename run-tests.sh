#!/bin/bash
mvn clean test || echo "Tests failed but continuing with report generation"
allure generate target/allure-results --clean -o target/allure-report
allure open target/allure-report
