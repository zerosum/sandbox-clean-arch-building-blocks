pluginManagement {
	repositories {
		gradlePluginPortal()
	}
}
rootProject.name = "clean-arch-kotlin"
include(
		"modules:domain",
		"modules:springboot",
		"modules:micronaut"
)
