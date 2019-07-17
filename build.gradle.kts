buildscript {

    repositories {
        google()
        jcenter()
    }

    dependencies {
        classpath(BuildPlugins.androidGradlePluginClassPath)
        classpath(BuildPlugins.kotlinGradlePluginClassPath)
        classpath(BuildPlugins.googleServicesClassPath)
    }
}

allprojects {
    repositories {
        google()
        jcenter()
        maven { url = uri("https://jitpack.io") }
    }

    gradle.projectsEvaluated {
        tasks.withType<JavaCompile> {
            options.compilerArgs.addAll(arrayOf("-Xmaxerrs", "1000"))
        }
    }
}

tasks.register("clean").configure {
    delete("rootProject.buildDir")
}
