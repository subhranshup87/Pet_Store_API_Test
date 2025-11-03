FROM eclipse-temurin:17-jdk

# Install curl for Allure installation
RUN apt-get update && apt-get install -y curl

# Install Allure
RUN curl -o allure-2.24.0.tgz -OLs https://repo.maven.apache.org/maven2/io/qameta/allure/allure-commandline/2.24.0/allure-commandline-2.24.0.tgz \
    && tar -zxvf allure-2.24.0.tgz -C /opt/ \
    && ln -s /opt/allure-2.24.0/bin/allure /usr/bin/allure \
    && rm allure-2.24.0.tgz

WORKDIR /app

# Create maven directory and set permissions
RUN mkdir -p /root/.m2 && chmod 777 /root/.m2

# Copy Maven files
COPY pom.xml .
COPY src ./src

# Download Maven dependencies (this layer will be cached)
RUN apt-get install -y maven \
    && mvn dependency:go-offline

# Create and set permissions for target and allure-report directories
RUN mkdir -p target allure-report \
    && chmod 777 target \
    && chmod 777 allure-report

# Command to run tests and generate report
# Use a container-local build directory (/tmp/target) so the Maven clean goal
# doesn't attempt to delete the host-mounted /app/target mountpoint.
CMD ["sh", "-c", "mvn -Dproject.build.directory=/tmp/target -Dclean.skip=true -Dallure.results.directory=/app/allure-results test && allure generate /app/allure-results --clean -o /app/allure-report"]