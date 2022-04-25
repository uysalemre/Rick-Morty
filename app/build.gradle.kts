plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id("kotlin-kapt")
    id("dagger.hilt.android.plugin")
}

android {
    compileSdk = Configurations.compileSdkVersion

    defaultConfig {
        applicationId = Configurations.applicationId
        minSdk = Configurations.minSdkVersion
        targetSdk = Configurations.targetSdkVersion
        versionCode = Configurations.versionCode
        versionName = Configurations.versionName

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    buildFeatures {
        dataBinding = true
    }
    tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile> {
        kotlinOptions {
            jvmTarget = "1.8"
        }
    }
}

kapt {
    correctErrorTypes = true
}

dependencies {
    implementation(DependencyPlugins.ktx)
    implementation(DependencyPlugins.activityKtx)
    implementation(DependencyPlugins.appCompat)
    implementation(DependencyPlugins.constraintLayout)
    implementation(DependencyPlugins.coroutines)
    implementation(DependencyPlugins.paging)
    implementation(DependencyPlugins.glide)
    implementation(DependencyPlugins.hilt)
    kapt(DependencyPlugins.hiltKapt)
    implementation(DependencyPlugins.httpLoggingInterceptor)
    implementation(DependencyPlugins.materialDesign)
    implementation(DependencyPlugins.retrofit)
    implementation(DependencyPlugins.retrofitGsonConverter)
    implementation(DependencyPlugins.viewModel)
    implementation(DependencyPlugins.room)
    implementation(DependencyPlugins.roomPaging)
    kapt(DependencyPlugins.roomKapt)
    implementation(DependencyPlugins.roomKtx)
    testImplementation(DependencyPlugins.junit)
}