plugins {
    id("java")
}

group = "github.kvsmil.bukkitutilities"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
    maven {
        url = uri("https://hub.spigotmc.org/nexus/content/repositories/snapshots/")
    }
}

dependencies {

    compileOnly("org.spigotmc:spigot-api:1.20.2-R0.1-SNAPSHOT")

    /* lombok */
    val lombok = "1.18.32"
    compileOnly("org.projectlombok:lombok:$lombok")
    annotationProcessor("org.projectlombok:lombok:$lombok")
}

