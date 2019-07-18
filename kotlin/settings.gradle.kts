pluginManagement {
	repositories {
		gradlePluginPortal()
	}
}
rootProject.name = "clean-arch-kotlin"
include(
		"modules:application",
		"modules:domain"
)
