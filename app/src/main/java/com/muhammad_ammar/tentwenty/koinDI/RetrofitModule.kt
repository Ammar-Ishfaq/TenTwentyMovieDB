package com.muhammad_ammar.tentwenty.koinDI

import android.content.Context
import com.muhammad_ammar.tentwenty.backend.MyAPICall
import kotlinx.serialization.ExperimentalSerializationApi
import okhttp3.*
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.File
import java.util.concurrent.TimeUnit

private const val BASE_URL = "https://api.themoviedb.org/3/movie/"

private const val CACHE_FILE_SIZE: Long = 50 * 1000 * 1000 // 50 Mib

@ExperimentalSerializationApi
val retrofitModule = module {

    single { cacheFile(get()) }

    single { cache(get()) }

    single<Call.Factory> { okHttp(get()) }

    single { retrofit(get() /*, getProperty("server_url")*/) }

    single { get<Retrofit>().create(MyAPICall::class.java) }
}

private val interceptor: Interceptor
    get() = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
//        if (BuildConfig.DEBUG)
//            HttpLoggingInterceptor.Level.BODY
//        else
//            HttpLoggingInterceptor.Level.NONE
    }

private fun cacheFile(context: Context) = File(context.filesDir, "m_ammar_tentwenty_cache").also {
    if (!it.exists())
        it.mkdirs()
}

@ExperimentalSerializationApi
private fun retrofit(callFactory: Call.Factory) = Retrofit.Builder()
    .callFactory(callFactory)
    .baseUrl(BASE_URL)
    .addConverterFactory(
        GsonConverterFactory.create()
    )
    .build()


private fun cache(cacheFile: File) = Cache(cacheFile, CACHE_FILE_SIZE)

private fun okHttp(cache: Cache): OkHttpClient {
    val tlsSpecs = listOf(ConnectionSpec.MODERN_TLS, ConnectionSpec.CLEARTEXT)
    return OkHttpClient.Builder()
        .addInterceptor(interceptor)
        .connectionSpecs(tlsSpecs)
        .connectTimeout(100, TimeUnit.SECONDS)
        .readTimeout(100, TimeUnit.SECONDS)
        .writeTimeout(100, TimeUnit.SECONDS)
        .cache(cache)
        .build()
}