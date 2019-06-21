package io.github.kobakei.katsuo.repository

import io.github.kobakei.katsuo.api.ApiClient
import io.github.kobakei.katsuo.entity.Ad
import kotlinx.coroutines.delay

/**
 * 広告データのリポジトリ
 * このリポジトリは常にAPIクライアントからデータを取得する
 */
class AdRepository(
    private val apiClient: ApiClient
) {

    suspend fun getTimelineAd(): Ad {
        delay(3000L)
        //throw IllegalArgumentException()
        return apiClient.getAdsAsync().timeline
    }

}