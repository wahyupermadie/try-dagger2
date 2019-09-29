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
}