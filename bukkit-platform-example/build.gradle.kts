plugins {
    id("java")
    id("com.github.johnrengelman.shadow") version "8.1.1"
}

group = "github.kvsmil.bukkitplatform"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
    maven {
        url = uri("https://hub.spigotmc.org/nexus/content/repositories/snapshots/")
    }
    maven {
        url = uri("https://storehouse.okaeri.eu/repository/maven-public/")
    }
}

dependencies {
    implementation(project(":bukkit-platform-core"))

    compileOnly("org.spigotmc:spigot-api:1.20.2-R0.1-SNAPSHOT")

    /* lombok */
    val lombok = "1.18.32"
    compileOnly("org.projectlombok:lombok:$lombok")
    annotationProcessor("org.projectlombok:lombok:$lombok")

    /* okaeri */
    implementation("eu.okaeri:okaeri-configs-yaml-bukkit:5.0.1")
    implementation("eu.okaeri:okaeri-configs-serdes-bukkit:5.0.1")
    implementation("eu.okaeri:okaeri-injector:2.1.0")
    implementation("eu.okaeri:okaeri-persistence-jdbc:2.0.3")
}

tasks.shadowJar {
    archiveFileName = "Bukkit-Template.jar"
}