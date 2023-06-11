package com.odenizturker.recommendation.service

import com.odenizturker.recommendation.client.RecommendationEngineClient
import com.odenizturker.recommendation.model.EventType
import com.odenizturker.recommendation.model.PredictionRecommendationModel
import kotlinx.coroutines.reactor.awaitSingle
import org.springframework.stereotype.Service

@Service
class RecommendationService(
    private val recommendationClient: RecommendationEngineClient
) {
    suspend fun getRecommendation(eventType: EventType, userId: Long): List<PredictionRecommendationModel> =
        recommendationClient.getRecommendation(eventType, userId).collectList().awaitSingle()
    suspend fun getGroupRecommendation(eventType: EventType, userIds: List<Long>): List<PredictionRecommendationModel> {
        if (userIds.count() > 3 || userIds.count() < 2) throw Exception("Invalid count")
        return recommendationClient.getGroupRecommendation(eventType, userIds).collectList().awaitSingle()
    }
}
