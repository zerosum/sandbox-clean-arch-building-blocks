import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
	kotlin("jvm") version "1.3.41"
}

java.sourceCompatibility = JavaVersion.VERSION_12

repositories {
	mavenCentral()
}

dependencies {
	implementation(kotlin("stdlib"))
	implementation("javax.inject:javax.inject:1")
}

tasks.withType<KotlinCompile> {
	kotlinOptions {
		freeCompilerArgs = listOf("-Xjsr305=strict")
		jvmTarget = "12"
	}
}
