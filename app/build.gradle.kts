plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
    id ("kotlin-parcelize")

}

android {
    namespace = "first.app.nikestore"
    compileSdk = 34


    defaultConfig {
        multiDexEnabled=true
        applicationId = "first.app.nikestore"
        minSdk = 24
        targetSdk = 34
        vectorDrawables.useSupportLibrary=true
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
}

dependencies {
    implementation("androidx.activity:activity:1.8.0")
    val koin = "3.2.0"
    implementation("androidx.core:core-ktx:1.13.1")
    implementation("androidx.appcompat:appcompat:1.7.0")
    implementation("com.google.android.material:material:1.12.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")
    implementation   ("io.insert-koin:koin-core:${koin}")
    implementation  ("io.insert-koin:koin-test:${koin}")
    implementation ("io.insert-koin:koin-android:${koin}")
    //retrofit
    implementation ("com.google.code.gson:gson:2.10.1")
    implementation ("com.squareup.retrofit2:retrofit:2.11.0")
    implementation ("com.squareup.retrofit2:adapter-rxjava2:2.11.0")
    implementation ("com.squareup.retrofit2:converter-gson:2.11.0")

    implementation ("io.reactivex.rxjava2:rxandroid:2.1.1")
    implementation ("io.reactivex.rxjava2:rxjava:2.2.21")
    implementation ("com.jakewharton.timber:timber:4.7.1")



    implementation ("androidx.navigation:navigation-fragment-ktx:2.7.7")
    implementation ("androidx.navigation:navigation-ui-ktx:2.7.7")
    implementation ("androidx.navigation:navigation-runtime-ktx:2.7.7")
    implementation ("androidx.viewpager2:viewpager2:1.1.0")
    implementation("com.facebook.fresco:fresco:1.10.0")
    implementation("com.tbuonomo:dotsindicator:5.0")
    implementation("androidx.dynamicanimation:dynamicanimation:1.0.0")
}