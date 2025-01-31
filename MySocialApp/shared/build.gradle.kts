plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.androidLibrary)
//    id("org.jetbrains.kotlin.plugin.serialization") version "2.0.21-RC"
    kotlin("plugin.serialization") version "1.9.10"
}

kotlin {
    androidTarget {
        compilations.all {
            kotlinOptions {
                jvmTarget = "1.8"
            }
        }
    }
    
    listOf(
        iosX64(),
        iosArm64(),
        iosSimulatorArm64()
    ).forEach {
        it.binaries.framework {
            baseName = "shared"
            isStatic = true
        }
    }


    //Dependencies Version
    val ktorVersion = "2.3.5"
//    val ktorVersion = "2..3.12"
    val koinVersion = "3.4.0"


    sourceSets {
        commonMain.dependencies {
            implementation(libs.kotlinx.coroutines.core)
            implementation(libs.ktor.client.core)
            implementation(libs.ktor.client.content.negotiation)
            implementation(libs.ktor.serialization.kotlinx.json)

            api(libs.koin.core)

            implementation(libs.gson)
//            implementation("io.ktor:ktor-client-okhttp:2.3.4")
            implementation("io.ktor:ktor-client-core:2.3.4")
            implementation("io.ktor:ktor-client-logging:2.3.4")
            implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.6.0")

            implementation("io.ktor:ktor-client-cio:2.3.4")  // Ou outra engine como okhttp
            implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.6.0")
            implementation("org.jetbrains.kotlin:kotlin-reflect:1.9.10") // Use a versão correspondente ao Kotlin do seu projeto

//            implementation("androidx.datastore:datastore-preferences-core:$datastoreVersion")
//            implementation("org.jetbrains.kotlinx:kotlinx-datetime:0.6.0-RC.2")

        }
        androidMain.dependencies {
            api("io.insert-koin:koin-android:$   ktorVersion ")
            implementation("io.ktor:ktor-client-okhttp:2.3.4")

        }
        iosMain.dependencies {
            implementation("io.ktor:ktor-client-darwin:2.3.4")
        }
        commonTest.dependencies {
            implementation(libs.kotlin.test)
        }
    }
}

android {
    namespace = "com.example.mysocialapp"
    compileSdk = 34
    defaultConfig {
        minSdk = 24
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
}
