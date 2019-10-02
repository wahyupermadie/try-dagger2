object ApplicationId {
    const val id = "com.wepe.trydagger"
}

object Versions {
    const val kotlin_version = "1.3.50"
    const val gradleVersion = "3.5.0"
    const val minSdk = 19
    const val compiledSdk = 29
    const val targetSdk = 29
    const val versionCode = 1
    const val coreKtx = "1.1.0"
    const val constraintLayout = "1.1.3"
    const val jUnitTest = "4.12"
    const val daggerVersion = "2.24"
    const val appCompact = "1.1.0"

    const val gson = "2.8.5"
    const val okHttp = "3.12.1"
    const val retrofit = "2.6.1"
    const val rxAndroid = "2.1.1"
    const val rxRoom = "2.1.0"
    const val rxKotlin = "2.4.0"
    const val rx = "2.2.12"

    const val lifecycle = "2.1.0-alpha04"
}

object Release {
    const val versionName = "1.0"
}

object AndroidLibraries {
    const val dagger = "com.google.dagger:dagger:${Versions.daggerVersion}"
    const val daggerAnnotation = "com.google.dagger:dagger-compiler:${Versions.daggerVersion}"
    const val coreKtx = "androidx.core:core-ktx:${Versions.coreKtx}"
    const val constraintLayout = "androidx.constraintlayout:constraintlayout:${Versions.constraintLayout}"
    const val appCompact = "androidx.appcompat:appcompat:${Versions.appCompact}"

    // RETROFIT
    const val gson = "com.google.code.gson:gson:${Versions.gson}"
    const val retrofit = "com.squareup.retrofit2:retrofit:${Versions.retrofit}"
    const val retrofitGsonConverter = "com.squareup.retrofit2:converter-gson:${Versions.retrofit}"
    const val retrofitRxAdapter ="com.squareup.retrofit2:adapter-rxjava2:${Versions.retrofit}"
    const val httpLoggingInterceptor = "com.squareup.okhttp3:logging-interceptor:${Versions.okHttp}"
    const val rx = "io.reactivex.rxjava2:rxjava:${Versions.rx}"
    const val rxKotlin = "io.reactivex.rxjava2:rxkotlin:${Versions.rxKotlin}"
    const val rxRoom = "androidx.room:room-rxjava2:${Versions.rxRoom}"

    const val lifecycleViewModel = "androidx.lifecycle:lifecycle-viewmodel-ktx:${Versions.lifecycle}"
    const val lifecycleExtensions = "androidx.lifecycle:lifecycle-extensions:${Versions.lifecycle}"
}