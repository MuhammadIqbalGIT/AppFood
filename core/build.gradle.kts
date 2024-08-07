plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.jetbrains.kotlin.android)
    alias(libs.plugins.kotlinAndroidKsp)
    alias(libs.plugins.hiltAndroid)
}

android {
    namespace = "com.example.core"
    compileSdk = 34

    defaultConfig {
        minSdk = 24

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
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
        buildConfig = true
    }
}

dependencies {

    implementation(libs.androidx.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    testImplementation(libs.junit)
    androidTestImplementation(libs.androidx.junit)
    androidTestImplementation(libs.androidx.espresso.core)

    implementation(libs.retrofit)
    implementation(libs.converter.gson)
    implementation(libs.logging.interceptor)

   /* //koin
    implementation(platform(libs.koin.bom))
    implementation(libs.koin.core)


    implementation(libs.koin.android.compat)
    implementation(libs.koin.androidx.workmanager)
    implementation(libs.koin.androidx.navigation)
*/
    implementation(libs.hilt.android)
    ksp(libs.hilt.compiler)



}