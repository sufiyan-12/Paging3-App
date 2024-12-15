// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.kotlin.android) apply false
    alias(libs.plugins.hilt.android) apply false
}

// Root-level build.gradle.kts file
tasks.register<Delete>("clean") {
    delete(rootProject.buildDir)
}