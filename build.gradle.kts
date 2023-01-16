plugins {
    id("java")
    // id("application")
    id("io.freefair.lombok") version "6.6.1"
    //  id("com.github.johnrengelman.shadow") version "7.1.2"


}

group = "org.example"
version = "1.0-SNAPSHOT"





repositories {
    mavenCentral()
}

dependencies {

    implementation(group = "org.apache.logging.log4j", name = "log4j-slf4j-impl", version = "2.19.0")
    implementation(group = "org.apache.logging.log4j", name = "log4j-core", version = "2.19.0")
    implementation(group = "org.slf4j", name = "slf4j-log4j12", version = "2.0.6")

    testImplementation("org.testng:testng:7.7.1")
    implementation("org.seleniumhq.selenium:selenium-java:3.141.59")
    implementation("io.github.bonigarcia:webdrivermanager:5.3.1")
    testImplementation("org.assertj:assertj-core:3.24.1")

//    implementation(files("build/libs/javaForTesters-1.0-SNAPSHOT.jar"))
//    testImplementation(files("build/libs/javaForTesters-1.0-SNAPSHOT.jar"))

}



tasks.getByName<Test>("test") {
    ignoreFailures = true
    useTestNG() {
        useDefaultListeners = true
        // suites("src/test/java/resources/testng.xml")
        // includeGroups("smoke")
        if (project.hasProperty("suite1")) {
            suites("src/test/resources/testngCreationTests.xml")
        }
        //  ./gradlew test  -Psuite1 -Psuite2
        if (project.hasProperty("suite2")) {
            //  dependsOn(testing.suites.named("entity-creation-tests"))
            //   dependsOn(suites("suite1"))
            // dependsOn(suites("./src/test/resources/testngCreationTests.xml"))
            suites("src/test/resources/testngDeletionTests.xml")
        }
//        if (project.hasProperty("firefox")) {
//            // System.setProperty("BROWSER", "FIREFOX");
//            environment("BROWSER", "FIREFOX");
//        }
    }
    testLogging {
        events("PASSED", "FAILED", "SKIPPED")
    }
}

//application {
//    mainClass.set("utils.TestRunner")
//}
//
//tasks.jar {
//    manifest.attributes["Main-Class"] = "utils.TestRunner"
//}

