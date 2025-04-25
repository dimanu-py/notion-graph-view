plugins {
    kotlin("jvm") version "1.9.25"
    kotlin("plugin.spring") version "1.9.25"
    id("org.springframework.boot") version "3.4.4"
    id("io.spring.dependency-management") version "1.1.7"
    id("com.diffplug.spotless") version "5.7.0"
    application
}

group = "com.dimanupy"
version = "0.0.1-SNAPSHOT"

allprojects {
    apply(plugin = "org.jetbrains.kotlin.jvm")
    apply(plugin = "com.diffplug.spotless")
    apply(plugin = "io.spring.dependency-management")

    repositories {
        mavenCentral()
    }

    java {
        toolchain {
            languageVersion = JavaLanguageVersion.of(17)
        }
    }

    dependencies {
        implementation(platform("org.jetbrains.kotlin:kotlin-bom"))
        implementation("org.jetbrains.kotlin:kotlin-reflect")
        implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
        implementation("org.jetbrains.exposed:exposed-core:0.61.0")
        implementation("org.jetbrains.exposed:exposed-dao:0.61.0")
        implementation("org.jetbrains.exposed:exposed-jdbc:0.61.0")
        implementation("org.postgresql:postgresql:42.5.4")

        compileOnly("org.projectlombok:lombok")
        annotationProcessor("org.projectlombok:lombok")

        testRuntimeOnly("org.junit.platform:junit-platform-launcher")
        testImplementation(kotlin("test"))
        testImplementation("org.jetbrains.kotlin:kotlin-test-junit5")
        testImplementation("org.springframework.boot:spring-boot-starter-test")
        testImplementation("org.testcontainers:postgresql:1.19.0")
        testImplementation("org.postgresql:postgresql:42.5.4")
        testImplementation("org.testcontainers:junit-jupiter:1.19.0")
        testImplementation("com.zaxxer:HikariCP:5.0.1")
    }

    tasks.withType<Test> {
        useJUnitPlatform()
        include("**/*Test.class*", "**/*Should.class*", "**/*Tests.class*")
    }

    configure<com.diffplug.gradle.spotless.SpotlessExtension> {
        kotlin {
            ktlint().userData(mapOf("insert_final_newline" to "true"))
        }
        kotlinGradle {
            ktlint()
        }
    }

    tasks.check {
        dependsOn("spotlessCheck")
    }
}

subprojects {
    apply(plugin = "org.jetbrains.kotlin.jvm")
    apply(plugin = "com.diffplug.spotless")
    apply(plugin = "io.spring.dependency-management")

    the<io.spring.gradle.dependencymanagement.dsl.DependencyManagementExtension>().apply {
        imports {
            mavenBom(org.springframework.boot.gradle.plugin.SpringBootPlugin.BOM_COORDINATES)
        }
    }

    dependencies {
        implementation("org.springframework:spring-jdbc")
        implementation("org.springframework:spring-web")

        testImplementation("io.mockk:mockk:1.14.0")
    }
}

configurations {
    compileOnly {
        extendsFrom(configurations.annotationProcessor.get())
    }
}

dependencies {
    implementation(project(":contexts:notes"))

    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.springframework:spring-context")
    implementation("org.springframework.boot:spring-boot-starter")
    implementation("org.springframework.boot:spring-boot-starter-actuator")
    implementation ("org.http4k:http4k-core:5.12.0.0")

    developmentOnly("org.springframework.boot:spring-boot-devtools")

    testImplementation("org.springframework.boot:spring-boot-starter-test")
    testImplementation("io.rest-assured:rest-assured")
    testImplementation("io.rest-assured:kotlin-extensions")
}

kotlin {
    compilerOptions {
        freeCompilerArgs.addAll("-Xjsr305=strict")
    }
}
