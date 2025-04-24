plugins {
    kotlin("jvm")
    id("com.diffplug.spotless")
}

group = "com.dimanupy"
version = "0.0.1-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    implementation ("org.http4k:http4k-core:5.12.0.0")
    implementation("org.json:json:20250107")

    testImplementation("com.github.javafaker:javafaker:1.0.2") {
        exclude(group = "org.yaml", module = "snakeyaml")
    }
    testImplementation("org.yaml:snakeyaml:2.3")
}

kotlin {
    jvmToolchain(17)
}
