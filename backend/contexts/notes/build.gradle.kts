plugins {
    kotlin("jvm")
    id("com.diffplug.spotless")
}

group = "com.dimanupy"
version = "0.0.1-SNAPSHOT"

repositories {
    mavenCentral()
}

kotlin {
    jvmToolchain(17)
}
