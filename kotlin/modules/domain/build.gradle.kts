import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
	kotlin("jvm")
}

java.sourceCompatibility = JavaVersion.VERSION_12

repositories {
	jcenter()
}

dependencies {
	implementation(kotlin("stdlib"))
	implementation("javax.inject:javax.inject:1")

	testImplementation("org.spekframework.spek2:spek-dsl-jvm:2.0.5")
	testImplementation("org.assertj:assertj-core:3.11.1")
	testRuntimeOnly(kotlin("reflect"))
}

tasks.withType<KotlinCompile> {
	kotlinOptions {
		freeCompilerArgs = listOf("-Xjsr305=strict")
		jvmTarget = "12"
	}
}
