plugins {
    id("java")
}

group = "org.example"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(platform("org.junit:junit-bom:5.9.1"))
    testImplementation("org.junit.jupiter:junit-jupiter")
}

tasks.test {
    useJUnitPlatform()
}

java {
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(17)) // Usa una versión compatible, por ejemplo, Java 17
    }
}

tasks.withType<JavaCompile> {
    options.release.set(17) // Asegúrate de que la versión de release coincida con la versión de la herramienta
}