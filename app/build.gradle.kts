plugins {
    alias(libs.plugins.android.application)
    alias(libs.plugins.jetbrains.kotlin.android)
    alias(libs.plugins.kotlinAndroidKsp)
    alias(libs.plugins.hiltAndroid)
    alias(libs.plugins.androidx.navigation.safeargs.kotlin)
}

android {
    namespace = "com.example.appfood"
    compileSdk = 34

    defaultConfig {
        applicationId = "com.example.appfood"
        minSdk = 24
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }


    flavorDimensions.add("env")

    productFlavors {

        create("dev") {
            dimension = "env"
            buildConfigField("String", "BASE_API", "\"https://www.themealdb.com/\"")
        }

        create("prod") {
            dimension = "env"
            buildConfigField("String", "BASE_API", "\"https://www.themealdb.com/\"")
        }
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
    buildFeatures {
     //   compose = true
        buildConfig = true
        //noinspection DataBindingWithoutKapt
        dataBinding = true
        viewBinding = true

    }
}

dependencies {

    implementation(project(":core"))

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    implementation(libs.androidx.activity)
    implementation(libs.androidx.constraintlayout)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)

    //Navigation
    implementation(libs.navigation.fragment)
    implementation(libs.navigation.ui)

    //hilt
    implementation(libs.hilt.android)
    ksp(libs.hilt.compiler)

    //lottie
    implementation(libs.lottie)


    //coil for load image
    implementation(libs.coilAndroid)
    
    /*//image gif
    implementation(libs.gifDrawable)*/

}