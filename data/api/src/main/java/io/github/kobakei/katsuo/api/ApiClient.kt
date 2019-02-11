package io.github.kobakei.katsuo.api

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.jakewharton.retrofit2.converter.kotlinx.serialization.serializationConverterFactory
import io.github.kobakei.katsuo.entity.Ads
import io.github.kobakei.katsuo.entity.Articles
import kotlinx.coroutines.Deferred
import kotlinx.serialization.json.Json
import okhttp3.MediaType
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.http.GET

/**
 * Dummy json
 * https://gist.github.com/kobakei/a091214688e4c03e4e582019d16cc778
 */
interface ApiClient {
    @GET("kobakei/a091214688e4c03e4e582019d16cc778/raw/b98a91dda49519564dc62bdc34c8c93734a24b82/articles.json")
    fun getArticlesAsync(): Deferred<Articles>

    @GET("kobakei/a091214688e4c03e4e582019d16cc778/raw/b98a91dda49519564dc62bdc34c8c93734a24b82/ads.json")
    fun getAdsAsync(): Deferred<Ads>
}

fun okHttpClient(): OkHttpClient {
    return OkHttpClient.Builder().build()
}

fun apiClient(): ApiClient {
    val retrofit = Retrofit.Builder()
        .addCallAdapterFactory(CoroutineCallAdapterFactory.invoke())
        .addConverterFactory(serializationConverterFactory(MediaType.get("application/json"), Json))
        .client(okHttpClient())
        .baseUrl("https://gist.githubusercontent.com/")
        .build()
    return retrofit.create(ApiClient::class.java)
}