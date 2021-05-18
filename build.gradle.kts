plugins {
    java
    kotlin("jvm").version(Dependencies.Kotlin.version)
    `maven-publish`
    `java-library`
}

group = "net.atlantis"
version = "0.1.0"

repositories {
    jcenter()
    mavenCentral()
    maven(Dependencies.Spigot.repository)
}

dependencies {
    api(Dependencies.Spigot.api)
    implementation(Dependencies.Kotlin.stdlib)
    testImplementation(Dependencies.JUnit.core)
}

buildscript {
    repositories {
        mavenCentral()
    }
    dependencies {
        classpath(Dependencies.Kotlin.classpath)
    }
}

afterEvaluate {
    publishing {
        publications {
            create<MavenPublication>("release") {
                groupId = "net.atlantis"
                artifactId = "rxspigot"
                version = project.version.toString()
                from(components["kotlin"])
            }
        }
    }
}