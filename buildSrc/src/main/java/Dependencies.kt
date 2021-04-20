import org.gradle.api.JavaVersion

object Config {
    val minSdk = 23
    val compileSdk = 30
    val targetSdk = 30
    val javaVersion = JavaVersion.VERSION_1_8
    val buildTools = "30.0.3"
}

object Versions {
    // <editor-fold desc="google">
    val androidx_core = "1.6.0-alpha01"
    val androidx_appcompat = "1.2.0"
    val androidx_recyclerview = "1.1.0"
    val androidx_navigation = "2.3.4"
    val androidx_constraintLayout = "2.0.4"
    val androidx_life_cycle = "2.3.0"
    val androidx_room = "2.2.6"
    val material = "1.3.0"
    // </editor-fold>

    val koin = "2.2.2"
    val log_interceptor = "4.2.1"

    val retrofit = "2.9.0"

    // <editor-fold desc="testing">
    val junit = "4.13.2"
    val junitext = "1.1.2"
    val mockito = "2.2.11"
    val androidx_espresso = "3.3.0"
    val androidx_testing = "1.3.0"
    val kt_coroutines = "1.4.3"
    val jupter = "5.6.2"
    //</editor-fold>

    // <editor-fold desc="tools">
    val kotlin = "1.4.31"
    val gradleandroid = "7.0.0-alpha14"
    val gradleversions = "0.38.0"
    // </editor-fold>


}

object Deps {


    val lib_kt_reflect          = "org.jetbrains.kotlin:kotlin-reflect:${Versions.kotlin}"
    val lib_kt_std              = "org.jetbrains.kotlin:kotlin-stdlib-jdk7:${Versions.kotlin}"
    val lib_kt_coroutines       = "org.jetbrains.kotlinx:kotlinx-coroutines-core:${Versions.kt_coroutines}"


    // <editor-fold desc="AndroidX">
    val androidx_core                    = "androidx.core:core-ktx:${Versions.androidx_core}"
    val androidx_constraintlayout        = "androidx.constraintlayout:constraintlayout:${Versions.androidx_constraintLayout}"
    val androidx_material                = "com.google.android.material:material:${Versions.material}"
    val androidx_navigation_fragment     = "androidx.navigation:navigation-fragment-ktx:${Versions.androidx_navigation}"
    val androidx_navigation_ui           = "androidx.navigation:navigation-ui-ktx:${Versions.androidx_navigation}"
    val androidx_navigation_safe_args    = "androidx.navigation:navigation-safe-args-gradle-plugin:${Versions.androidx_navigation}"
    val androidx_recyclerview            = "androidx.recyclerview:recyclerview:${Versions.androidx_recyclerview}"

    val androidx_room                    = "androidx.room:room-runtime:${Versions.androidx_room}"
    val androidx_room_compiler           = "androidx.room:room-compiler:${Versions.androidx_room}"
    val androidx_roomktx                 = "androidx.room:room-ktx:${Versions.androidx_room}"
    val androidx_appcompat               = "androidx.appcompat:appcompat:${Versions.androidx_appcompat}"
    val androidx_lf_viewmodel            = "androidx.lifecycle:lifecycle-viewmodel-ktx:${Versions.androidx_life_cycle}"
    val androidx_lf_livedata             = "androidx.lifecycle:lifecycle-livedata-ktx:${Versions.androidx_life_cycle}"
    // </editor-fold>

    //Region Android
    //easy recyclerview -> adapter/viewholder
    val kiel                             = "me.ibrahimyilmaz:kiel:1.2.1"
    val koin                             =  "org.koin:koin-core:${Versions.koin}"
    val koinAndroid                      =  "org.koin:koin-android:${Versions.koin}"
    val koinLifeCycleScope               =  "org.koin:koin-android-scope:${Versions.koin}"
    val koinAndroidViewModel             =  "org.koin:koin-androidx-viewmodel:${Versions.koin}"
    val koinTest                         =  "org.koin:koin-test:${Versions.koin}"
    //Endregion

    //retrofit
    val logginInterceptor                = "com.squareup.okhttp3:logging-interceptor:${Versions.log_interceptor}"
    val retrofit                         = "com.squareup.retrofit2:retrofit:${Versions.retrofit}"
    val retrofit_gson                    = "com.squareup.retrofit2:converter-gson:${Versions.retrofit}"


    // <editor-fold desc="testing">
    val testandroidx_room           = "androidx.room:room-testing:${Versions.androidx_room}"
    val testlib_junit               = "junit:junit:${Versions.junit}"
    val testlib_junitext            = "androidx.test.ext:junit:${Versions.junitext}"
    val testandroidx_rules          = "androidx.test:rules:${Versions.androidx_testing}"
    val testandroidx_runner         = "androidx.test:runner:${Versions.androidx_testing}"
    val testandroidx_espressocore   = "androidx.test.espresso:espresso-core:${Versions.androidx_espresso}"
    val testlib_mockito             = "org.mockito.kotlin:mockito-kotlin:${Versions.mockito}"
    val testlib_kt_junit            = "org.jetbrains.kotlin:kotlin-test-junit5"
    val testlib_kt_test             = "org.jetbrains.kotlin:kotlin-test-common:${Versions.kotlin}"
    val testlib_coroutines          = "org.jetbrains.kotlinx:kotlinx-coroutines-test:${Versions.kt_coroutines}"
    val testlib_jupter              = "org.junit.jupiter:junit-jupiter-api:${Versions.jupter}"
    //</editor-fold>



    // <editor-fold desc="tools">

        val tools_gradleandroid           = "com.android.tools.build:gradle:${Versions.gradleandroid}"
        val tools_kotlin                  = "org.jetbrains.kotlin:kotlin-gradle-plugin:${Versions.kotlin}"
        val tools_gradleversions          = "com.github.ben-manes:gradle-versions-plugin:${Versions.gradleversions}"

    // </editor-fold>
}