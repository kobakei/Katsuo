package io.github.kobakei.katsuo.repository

import io.github.kobakei.katsuo.api.ApiClient
import io.github.kobakei.katsuo.entity.Ad

/**
 * 広告データのリポジトリ
 * このリポジトリは常にAPIクライアントからデータを取得する
 */
class AdRepository(
    private val apiClient: ApiClient
) {

    suspend fun getTimelineAd(): Ad = apiClient.getAdsAsync().timeline

}