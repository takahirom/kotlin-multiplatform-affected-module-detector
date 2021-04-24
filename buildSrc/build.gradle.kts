plugins {
    kotlin("jvm") version "1.4.32"
    `java-gradle-plugin`
}

repositories {
    google()
    jcenter()
    mavenLocal()
}

dependencies {
    implementation("com.dropbox.affectedmoduledetector:affectedmoduledetector:0.1.1")
}