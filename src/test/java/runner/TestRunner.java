package runner;

import org.junit.platform.suite.api.*;

@Suite
@IncludeEngines("cucumber")
@SelectDirectories("src/test/java/features")
@ConfigurationParameter(
        key = "cucumber.plugin",
        value = "pretty, io.qameta.allure.cucumber7jvm.AllureCucumber7Jvm"
)
@ConfigurationParameter(
        key = "cucumber.glue",
        value = "steps"
)
public class TestRunner {}
