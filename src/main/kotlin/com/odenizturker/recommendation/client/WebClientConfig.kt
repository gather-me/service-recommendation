package com.odenizturker.recommendation.client

import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.reactive.function.client.WebClient

@Configuration
class WebClientConfig(
    private val builder: WebClient.Builder
) {
    @Bean
    fun recommendationEngineWebClient(
        @Value("\${gather.server.recommendation-engine.url}")
        baseUrl: String
    ) = builder.baseUrl(baseUrl).build()

    @Bean
    fun eventWebClient(
        @Value("\${gather.server.event.url}")
        baseUrl: String
    ) = builder.baseUrl(baseUrl).build()
}
