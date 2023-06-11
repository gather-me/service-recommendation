package com.odenizturker.recommendation.model

import com.fasterxml.jackson.annotation.JsonProperty
import java.math.BigDecimal

data class PredictionRecommendationModel(
    val id: Long,
    @JsonProperty("event_type")
    val eventType: EventType,
    val prediction: BigDecimal
)
