package io.github.kobakei.katsuo.repository

import io.github.kobakei.katsuo.api.apiClient
import io.github.kobakei.katsuo.entity.Ad

/**
 * 広告データのリポジトリ
 * このリポジトリは常にAPIクライアントからデータを取得する
 */
class AdRepository {

    suspend fun getTimelineAd(): Ad = apiClient().getAdsAsync().await().timeline

}