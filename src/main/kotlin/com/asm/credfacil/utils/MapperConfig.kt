package com.asm.credfacil.utils

import org.modelmapper.ModelMapper
import org.modelmapper.config.Configuration
import org.modelmapper.convention.MatchingStrategies
import org.springframework.context.annotation.Bean
import org.springframework.stereotype.Component

@Component
class MapperConfig {
    @Bean
    fun mapper(): ModelMapper {
        val mapper = ModelMapper()
        mapper.configuration
            .setFieldMatchingEnabled(true)
            .setMatchingStrategy(MatchingStrategies.STRICT)
            .setFieldAccessLevel(Configuration.AccessLevel.PRIVATE)

        return mapper
    }
}