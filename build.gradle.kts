plugins {
    id("java")
    // id("application")
    id("io.freefair.lombok") version "6.6.1"
    //  id("com.github.johnrengelman.shadow") version "7.1.2"
    id("io.qameta.allure") version "2.11.2"
    //id("io.qameta.allure-aggregate-report") version "2.11.2"

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
    implementation("io.qameta.allure:allure-testng:2.20.1")


    testImplementation("org.testng:testng:7.7.1")
    implementation("org.seleniumhq.selenium:selenium-java:3.141.59")
    implementation("io.github.bonigarcia:webdrivermanager:5.3.1")
    testImplementation("org.assertj:assertj-core:3.24.1")
    implementation("mysql:mysql-connector-java:8.0.31")
    // implementation(gradleApi())

//    implementation(files("build/libs/javaForTesters-1.0-SNAPSHOT.jar"))
//    testImplementation(files("build/libs/javaForTesters-1.0-SNAPSHOT.jar"))

}


tasks.register("copyAllDependencies", Copy::class) {

    from(configurations.runtimeClasspath.files)
    into("$buildDir/libs/lib")

}

tasks.jar {

    from(sourceSets["main"].output)
    archiveFileName.set("javaForTesters-1.0-SNAPSHOT.jar")
    destinationDirectory.set(file("$buildDir/libs"))
}


tasks.register<Jar>("packageTests") {
    from(sourceSets.test.get().output)
    archiveFileName.set("javaForTesters-test-1.0-SNAPSHOT.jar")
    destinationDirectory.set(file("$buildDir/libs"))
}


tasks {
    "jar" {
        dependsOn("copyAllDependencies")
        dependsOn("packageTests")
    }
}


tasks.getByName<Test>("test") {
    ignoreFailures = true
    systemProperty("allure.results.directory", "/app/allure-results")

    if (project.hasProperty("firefox")) {
        // System.setProperty("BROWSER", "FIREFOX");
        systemProperty("BROWSER", "FIREFOX");
    }

    // OR we can use this option: # !!!!!!!!!!!!
    // systemProperty('HUB_HOST', "${hub_host}")
    // and send command: ./gradlew clean test -Phub_host="host.docker.internal" -Psuite1
    if (project.hasProperty("hub_host")) {
        // System.setProperty("BROWSER", "FIREFOX");
        systemProperty("HUB_HOST", "host.docker.internal")

    }
    //reports.html.isEnabled = true
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


reporting {
    baseDir = File("/app/reports")
}

allure {
    report {
        // There might be several tasks producing the report, so the property
        // configures a base directory for all the reports
        // Each task creates its own subfolder there
        reportDir.set(project.reporting.baseDirectory.dir("allure-report"))
        adapter.autoconfigure
        adapter.aspectjWeaver
        // dependsOnTests.set(false)  // ./gradlew allureReport --depends-on-tests
    }
}
