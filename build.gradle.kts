buildscript {
    repositories {
        maven { setUrl("https://maven.aliyun.com/repository/public") } //jcenter
        maven { setUrl("https://maven.aliyun.com/repository/google") } //google
        maven { setUrl("https://jitpack.io") }
        gradlePluginPortal()
        google()
        mavenCentral()
        jcenter()
    }
    dependencies {
        classpath("com.android.tools.build:gradle:8.1.1")
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:1.8.10")
        classpath("com.github.ben-manes:gradle-versions-plugin:0.45.0")
    }

    extra.apply {
        set("kotlinVersion", "1.8.10")
        set("minSdkVersion", 21)
        set("targetSdkVersion", 30)
        set("buildToolsVersion", 30)
    }
}

allprojects {
    repositories {
        maven { setUrl("https://maven.aliyun.com/repository/public") } //jcenter
        maven { setUrl("https://maven.aliyun.com/repository/google") } //google
        maven { setUrl("https://jitpack.io") }
        google()
        mavenCentral()
        jcenter()
    }
}
