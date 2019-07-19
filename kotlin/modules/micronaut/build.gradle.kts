import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    id("io.spring.dependency-management") version "1.0.7.RELEASE"
    id("com.github.johnrengelman.shadow") version "4.0.2"
    id("application")
    kotlin("jvm")
    kotlin("kapt")
    kotlin("plugin.allopen") version "1.3.41"
}

java.sourceCompatibility = JavaVersion.VERSION_12

repositories {
    mavenLocal()
    mavenCentral()
    jcenter()
}

dependencyManagement {
    imports {
        mavenBom("io.micronaut:micronaut-bom:1.1.4")
    }
}

val developmentOnly by configurations.creating

dependencies {
    implementation(project(":modules:domain"))

    implementation(kotlin("stdlib"))
    implementation(kotlin("reflect"))

    implementation("io.micronaut:micronaut-runtime")
    implementation("io.micronaut:micronaut-http-client")
    implementation("io.micronaut:micronaut-http-server-netty")
    kapt("io.micronaut:micronaut-inject-java")
    kapt("io.micronaut:micronaut-validation")
    kaptTest("io.micronaut:micronaut-inject-java")
    runtimeOnly("ch.qos.logback:logback-classic:1.2.3")
    runtimeOnly("com.fasterxml.jackson.module:jackson-module-kotlin:2.9.7")
    testImplementation("org.junit.jupiter:junit-jupiter-api")
    testImplementation("org.spekframework.spek2:spek-dsl-jvm:2.0.5")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine")
}

tasks.withType<Test> {
    classpath += developmentOnly
}

tasks.withType<JavaExec> {
    classpath += developmentOnly
    jvmArgs("-noverify", "-XX:TieredStopAtLevel=1")
}

application {
    mainClassName = "dev.zerosum.example.Application"
}

allOpen {
	annotation("io.micronaut.aop.Around")
}

tasks.withType<KotlinCompile> {
    kotlinOptions {
	    jvmTarget = "12"
	    javaParameters = true
	}
}
