plugins {
    id("java")
    //  application
}

group = "org.example"
version = "1.0-SNAPSHOT"





repositories {
    mavenCentral()
}

dependencies {
//    implementation("log4j:log4j:1.2.17")
//    implementation("org.apache.logging.log4j:log4j-core:2.19.0")
//    implementation("org.slf4j:slf4j-api:2.0.5")
    implementation(group = "org.apache.logging.log4j", name = "log4j-slf4j-impl", version = "2.19.0")
    implementation(group = "org.apache.logging.log4j", name = "log4j-core", version = "2.19.0")
    implementation(group = "org.slf4j", name = "slf4j-log4j12", version = "2.0.6")
    // testImplementation("org.junit.jupiter:junit-jupiter-api:5.8.1")
    testImplementation("org.testng:testng:7.7.1")
    implementation("org.seleniumhq.selenium:selenium-java:3.141.59")
    implementation("io.github.bonigarcia:webdrivermanager:5.3.1")
    // testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:5.8.1")

}



tasks.getByName<Test>("test") {
    ignoreFailures = true
    useTestNG() {
        useDefaultListeners = true
        //suites ("src/test/suite.xml")
        // includeGroups("smoke")
    }
    testLogging {
        events("PASSED", "FAILED", "SKIPPED")
    }
}

//application {
//    mainClass.set("org.example.Main")
//}

