buildscript {
    repositories{
        mavenCentral()
        google()
    }
    dependencies {
        classpath("com.android.tools.build:gradle:4.1.3")
    }
}
allprojects {
    repositories {
        mavenCentral()
        google()
    }
}

apply(from = "gradle/affected.gradle")