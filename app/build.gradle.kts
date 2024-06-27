plugins {
    alias(libs.plugins.androidApplication)
    alias(libs.plugins.jetbrainsKotlinAndroid)
}

android {
    namespace = "cl.td.suboch.alkewallet"
    compileSdk = 34

    defaultConfig {
        applicationId = "cl.td.suboch.alkewallet"
        minSdk = 24
        targetSdk = 34
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
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }

    viewBinding {
        enable = true
    }
    buildFeatures{
        viewBinding=true
    }
}

dependencies {

    //Implement RecyclerView
    implementation ("androidx.recyclerview:recyclerview:1.3.2")
    implementation ("androidx.lifecycle:lifecycle-viewmodel-ktx:2.8.0")

    //Implementar las librerias necesarias para el proyecto
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.7.3")
    implementation("com.squareup.retrofit2:retrofit:2.11.0")
    implementation("com.squareup.retrofit2:converter-gson:2.11.0")

    // LiveData
    implementation("androidx.lifecycle:lifecycle-livedata-ktx:2.8.0")

    implementation("com.squareup.okhttp3:logging-interceptor:4.12.0")

    //Picasso
    implementation("com.squareup.picasso:picasso:2.8")

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.constraintlayout)

    //Tests
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)
}