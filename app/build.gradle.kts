plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.kotlin.android)
    alias(libs.plugins.kotlin.compose)
    id("kotlin-kapt")
    id("com.google.dagger.hilt.android")
}

android {
    namespace = "com.example.weather"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.example.weather"
        minSdk = 24
        targetSdk = 35
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    kotlinOptions {
        jvmTarget = "11"
    }
    buildFeatures {
        compose = true
    }
}

dependencies {
    implementation("com.google.dagger:hilt-android:2.55")
    kapt("com.google.dagger:hilt-android-compiler:2.51.1")
    kapt("androidx.hilt:hilt-compiler:1.2.0")

    //implementation("com.google.dagger:hilt-android-gradle-plugin:$hiltVers")
    //ksp("com.google.dagger:hilt-compiler:$hiltVers")
        //ksp("androidx.hilt:hilt-compiler:${hiltVers}") // 1.0.0 in course
    implementation("androidx.hilt:hilt-navigation-compose:1.2.0") //
    implementation("androidx.hilt:hilt-compiler:1.2.0")


    //material icons
    implementation("androidx.compose.material:material-icons-extended:1.7.6")

    // coroutines
    implementation(libs.kotlinx.coroutines.android.v1101)
    implementation(libs.kotlinx.coroutines.core)
    implementation(libs.jetbrains.kotlinx.coroutines.android)


    val coroutinesPlayServicesVersion = "1.6.4" // Or the latest version
    implementation(libs.kotlinx.coroutines.play.services)


    //coroutine lifecycle scopes
    implementation(libs.androidx.lifecycle.viewmodel.ktx) //2.4.0 in course
    // lifecycle-runtime-ktx already in auto generated dependencies,
    // otherwise it would be here


    //coil
    implementation(libs.coil.compose)

    //retrofit & json converter
    implementation(libs.retrofit)
    implementation(libs.converter.gson)

    //OkHttp
    implementation(libs.okhttp) // 5.0.0-alpha.2 in course

    //Room
    val roomVers = "2.6.1"
    implementation(libs.androidx.room.runtime)
    annotationProcessor(libs.androidx.room.compiler)
    kapt(libs.androidx.room.compiler)
    implementation(libs.androidx.room.ktx)










    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.lifecycle.runtime.ktx)
    implementation(libs.androidx.activity.compose)
    implementation(platform(libs.androidx.compose.bom))
    implementation(libs.androidx.ui)
    implementation(libs.androidx.ui.graphics)
    implementation(libs.androidx.ui.tooling.preview)
    implementation(libs.androidx.material3)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
    androidTestImplementation(platform(libs.androidx.compose.bom))
    androidTestImplementation(libs.androidx.ui.test.junit4)
    debugImplementation(libs.androidx.ui.tooling)
    debugImplementation(libs.androidx.ui.test.manifest)
}