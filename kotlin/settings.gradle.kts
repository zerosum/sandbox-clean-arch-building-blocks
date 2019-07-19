pluginManagement {
	repositories {
		gradlePluginPortal()
	}
}
rootProject.name = "clean-arch-kotlin"
include(
		"modules:springboot",
		"modules:domain"
)
