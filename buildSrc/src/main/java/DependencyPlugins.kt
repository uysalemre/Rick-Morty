object DependencyPlugins {
    val ktx by lazy { "androidx.core:core-ktx:${Versions.kotlin}" }
    val activityKtx by lazy { "androidx.activity:activity-ktx:${Versions.activityKtx}" }
    val appCompat by lazy { "androidx.appcompat:appcompat:${Versions.appCompat}" }
    val materialDesign by lazy { "com.google.android.material:material:${Versions.material}" }
    val constraintLayout by lazy { "androidx.constraintlayout:constraintlayout:${Versions.constraintLayout}" }
    val glide by lazy { "com.github.bumptech.glide:glide:${Versions.glide}" }
    val retrofit by lazy { "com.squareup.retrofit2:retrofit:${Versions.retrofit}" }
    val retrofitGsonConverter by lazy { "com.squareup.retrofit2:converter-gson:${Versions.retrofit}" }
    val httpLoggingInterceptor by lazy { "com.squareup.okhttp3:logging-interceptor:${Versions.loggingInterceptor}" }
    val coroutines by lazy { "org.jetbrains.kotlinx:kotlinx-coroutines-android:${Versions.coroutines}" }
    val hilt by lazy { "com.google.dagger:hilt-android:${Versions.hilt}" }
    val hiltKapt by lazy { "com.google.dagger:hilt-android-compiler:${Versions.hilt}" }
    val viewModel by lazy { "androidx.lifecycle:lifecycle-viewmodel-ktx:${Versions.architecture}" }
    val room by lazy { "androidx.room:room-runtime:${Versions.room}" }
    val roomKtx by lazy { "androidx.room:room-ktx:${Versions.room}" }
    val roomKapt by lazy { "androidx.room:room-compiler:${Versions.room}" }
    val roomPaging by lazy { "androidx.room:room-paging:${Versions.roomPaging}" }
    val paging by lazy { "androidx.paging:paging-runtime:${Versions.paging}" }
    val junit by lazy { "junit:junit:${Versions.jUnit}" }
    val navigationFragment by lazy { "androidx.navigation:navigation-fragment-ktx:${Versions.navigation}" }
    val navigationKtx by lazy { "androidx.navigation:navigation-ui-ktx:${Versions.navigation}" }
    val liveDataKtx by lazy { "androidx.lifecycle:lifecycle-livedata-ktx:${Versions.liveData}" }
    val liveDataViewModel by lazy { "androidx.lifecycle:lifecycle-viewmodel-ktx:${Versions.liveData}" }
    val hiltNavigation by lazy { "androidx.hilt:hilt-navigation-fragment:${Versions.hiltNavigation}" }
    val dataStore by lazy { "androidx.datastore:datastore-preferences:${Versions.dataStore}" }
}