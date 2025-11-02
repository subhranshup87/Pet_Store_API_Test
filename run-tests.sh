#!/bin/bash
mvn clean test && allure generate target/allure-results --clean -o target/allure-report && allure open target/allure-report
