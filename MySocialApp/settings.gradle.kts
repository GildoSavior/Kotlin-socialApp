enableFeaturePreview("TYPESAFE_PROJECT_ACCESSORS")
pluginManagement {
    repositories {
        google()
        gradlePluginPortal()
        mavenCentral()
    }
    resolutionStrategy {
        eachPlugin {
            if(requested.id.id.startsWith("libs.")) {
                useModule(requested.id.id)
            }
        }
    }

}

dependencyResolutionManagement {
    repositories {
        google()
        mavenCentral()
    }
}

rootProject.name = "My_Social_App"
include(":androidApp")
include(":shared")