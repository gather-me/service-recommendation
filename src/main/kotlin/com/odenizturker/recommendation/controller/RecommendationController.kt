package com.odenizturker.recommendation.controller

import com.odenizturker.recommendation.model.EventType
import com.odenizturker.recommendation.service.RecommendationService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
class RecommendationController(
    private val recommendationService: RecommendationService
) {
    @GetMapping("users/{userId}/recommend/events/{eventType}")
    suspend fun getRecommendation(
        @PathVariable userId: Long,
        @PathVariable eventType: EventType
    ) = recommendationService.getRecommendation(eventType, userId)

    @GetMapping("users/{userId}/recommend/events/{eventType}/group")
    suspend fun getGroupRecommendation(
        @PathVariable userId: Long,
        @PathVariable eventType: EventType,
        @RequestParam users: List<Long>
    ) = recommendationService.getGroupRecommendation(eventType, users + userId)
}
