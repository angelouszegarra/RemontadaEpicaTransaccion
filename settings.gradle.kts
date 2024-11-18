pluginManagement {
    repositories {
        google()
        mavenCentral()
    }
    plugins {
        id("org.jetbrains.kotlin.android") version "1.9.10"
        id("org.jetbrains.kotlin.plugin.compose") version "1.5.3"
    }
}

dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google() // Repositorio principal para Android
        mavenCentral() // Repositorio para otras bibliotecas
    }
}


rootProject.name = "remontada epica transaccion"
include(":app")
