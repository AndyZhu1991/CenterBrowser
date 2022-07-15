import org.jetbrains.compose.compose
import org.jetbrains.compose.desktop.application.dsl.TargetFormat
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("multiplatform")
    id("org.jetbrains.compose")
}

group = "andy.zhu"
version = "1.0-SNAPSHOT"

repositories {
    google()
    mavenCentral()
    maven("https://maven.pkg.jetbrains.space/public/p/compose/dev")
}

// Dependency versions
val jsoupVersion by project.properties
val graalVersion by project.properties
val okhttpVersion by project.properties

kotlin {
    jvm {
        compilations.all {
            kotlinOptions.jvmTarget = "11"
        }
        withJava()
    }
    sourceSets {
        val jvmMain by getting {
            dependencies {
                implementation(compose.desktop.currentOs)
                implementation("org.jsoup:jsoup:$jsoupVersion")
                implementation("org.graalvm.sdk:graal-sdk:$graalVersion")
                implementation("org.graalvm.js:js:$graalVersion")
                implementation("org.graalvm.js:js-scriptengine:$graalVersion")
                implementation("org.graalvm.tools:profiler:$graalVersion")
                implementation("org.graalvm.tools:chromeinspector:$graalVersion")
                implementation("com.squareup.okhttp3:okhttp:$okhttpVersion")
            }
        }
        val jvmTest by getting
    }
}

compose.desktop {
    application {
        mainClass = "MainKt"
        nativeDistributions {
            targetFormats(TargetFormat.Dmg, TargetFormat.Msi, TargetFormat.Deb)
            packageName = "center"
            packageVersion = "1.0.0"
        }
    }
}
