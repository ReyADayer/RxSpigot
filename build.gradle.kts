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

publishing {
    publications {
        create<MavenPublication>("root") {
            groupId = "net.atlantis"
            artifactId = "rxspigot"
            version = project.version.toString()
            from(components["kotlin"])
        }
    }
    repositories {
        githubPackages("ReyADayer/RxSpigot")
        maven {
            name = "OSSRH"
            url = uri("https://oss.sonatype.org/service/local/staging/deploy/maven2/")
            credentials {
                username = System.getenv("MAVEN_USERNAME")
                password = System.getenv("MAVEN_PASSWORD")
            }
        }
    }
}

tasks {
    compileJava {
        sourceCompatibility = "1.8"
        targetCompatibility = "1.8"
        options.encoding = "UTF-8"
    }

    compileTestJava {
        sourceCompatibility = "1.8"
        targetCompatibility = "1.8"
        options.encoding = "UTF-8"
    }

    compileKotlin {
        kotlinOptions.jvmTarget = "1.8"
    }

    compileTestKotlin {
        kotlinOptions.jvmTarget = "1.8"
    }

    javadoc {
        options.encoding = "UTF-8"
    }
}

fun RepositoryHandler.githubPackages(path: String) = maven {
    url = uri("https://maven.pkg.github.com/$path")
    name = "GithubPackages"
    credentials {
        username = project.findProperty("gpr.user") as String? ?: System.getenv("USERNAME")
        password = project.findProperty("gpr.token") as String? ?: System.getenv("TOKEN")
    }
}