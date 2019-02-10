package io.github.kobakei.katsuo.api

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.jakewharton.retrofit2.converter.kotlinx.serialization.serializationConverterFactory
import io.github.kobakei.katsuo.entity.Articles
import kotlinx.coroutines.Deferred
import kotlinx.serialization.json.Json
import okhttp3.MediaType
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.http.GET

interface ArticleService {
    @GET("kobakei/a091214688e4c03e4e582019d16cc778/raw/0c2fc5cdaf7aa630a4d02a8477a905b2a20c40bc/test.json")
    fun getArticles(): Deferred<Articles>
}

fun okHttpClient(): OkHttpClient {
    return OkHttpClient.Builder().build()
}

fun apiClient(): ArticleService {
    val retrofit = Retrofit.Builder()
        .addCallAdapterFactory(CoroutineCallAdapterFactory.invoke())
        .addConverterFactory(serializationConverterFactory(MediaType.get("application/json"), Json))
        .client(okHttpClient())
        .baseUrl("https://gist.githubusercontent.com/")
        .build()
    return retrofit.create(ArticleService::class.java)
}