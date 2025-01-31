plugins {
    alias(libs.plugins.androidApplication)
    alias(libs.plugins.kotlinAndroid)
    alias(libs.plugins.compose.compiler)

    id("com.google.devtools.ksp") version "2.0.20-1.0.25"
}

android {
    namespace = "com.example.mysocialapp.android"
    compileSdk = 34
    defaultConfig {
        applicationId = "com.example.mysocialapp.android"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"
        compileSdkPreview = "UpsideDownCake"

    }
    buildFeatures {
        compose = true
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }

    applicationVariants.all {
        addJavaSourceFoldersToModel(
            File(buildDir, "generated/ksp/$name/kotlin")
        )
    }
}

dependencies {
    implementation(projects.shared)
    implementation(libs.compose.ui)
    implementation(libs.compose.ui.tooling.preview)
    implementation(libs.compose.material3)
    implementation(libs.androidx.activity.compose)
    debugImplementation(libs.compose.ui.tooling)


//    implementation("io.github.raamcosta.compose-destinations:core:2.1.0-beta12")
//    ksp("io.github.raamcosta.compose-destinations:ksp:2.1.0-beta12")

    implementation("io.github.raamcosta.compose-destinations:core:1.8.38-beta")
    ksp("io.github.raamcosta.compose-destinations:ksp:1.8.38-beta")


    implementation("androidx.core:core-splashscreen:1.1.0-rc01")

    implementation("io.insert-koin:koin-androidx-compose:4.0.0-RC2")

    implementation("androidx.lifecycle:lifecycle-runtime-compose:2.8.5")

    implementation("com.google.accompanist:accompanist-systemuicontroller:0.36.0")

    implementation("io.coil-kt:coil-compose:2.7.0")

    implementation("androidx.compose.material:material:1.7.0")
}