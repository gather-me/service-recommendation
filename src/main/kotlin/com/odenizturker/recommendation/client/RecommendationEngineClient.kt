package com.odenizturker.recommendation.client

import com.odenizturker.recommendation.model.EventType
import com.odenizturker.recommendation.model.RecommendationModel
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.client.WebClient
import org.springframework.web.reactive.function.client.bodyToFlux
import reactor.core.publisher.Flux

@Component
class RecommendationEngineClient(
    @Qualifier("recommendationEngineWebClient")
    private val client: WebClient
) {
    fun getRecommendation(eventType: EventType, userId: Long): Flux<RecommendationModel> =
        client
            .get()
            .uri("/events/{eventType}/predict/rate/{userId}", eventType, userId)
            .retrieve()
            .bodyToFlux()

    fun getGroupRecommendation(eventType: EventType, userIds: List<Long>): Flux<RecommendationModel> =
        client
            .get()
            .uri {
                it.path("/events/{eventType}/predict/rate")
                    .queryParam("users", userIds)
                    .build(eventType)
            }
            .retrieve()
            .bodyToFlux()
}
