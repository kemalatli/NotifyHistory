dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
        maven(url ="https://jitpack.io")
        jcenter() // Warning: this repository is going to shut down soon
    }
}
rootProject.name = "Notify History"
include(":app")
include(":domain")
include(":data")

plugins {
    // See https://jmfayard.github.io/refreshVersions
    id("de.fayard.refreshVersions") version "0.23.0"
}
refreshVersions {
    enableBuildSrcLibs()
}
