plugins {
    alias(libs.plugins.android.application)
}

android {
    namespace = "com.example.qrcodescanner"
    compileSdk = 35

    defaultConfig {
        applicationId = "com.example.qrcodescanner"
        minSdk = 31
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
}

dependencies {

    implementation(libs.appcompat)
    implementation(libs.material)
    implementation(libs.activity)
    implementation(libs.constraintlayout)
    testImplementation(libs.junit)
    androidTestImplementation(libs.ext.junit)
    androidTestImplementation(libs.espresso.core)
    implementation ("com.journeyapps:zxing-android-embedded:4.3.0")
    implementation ("com.google.android.material:material:1.4.0")
    implementation ("androidx.appcompat:appcompat:1.3.1")
    implementation ("androidx.constraintlayout:constraintlayout:2.0.4")
    implementation ("com.google.zxing:core:3.3.0")
    implementation ("com.journeyapps:zxing-android-embedded:4.3.0") // For Android integration
    implementation ("com.google.zxing:core:3.3.0") // For QR code generation
    implementation ("com.google.code.gson:gson:2.8.9")
    implementation("com.razorpay:checkout:1.6.21")

}