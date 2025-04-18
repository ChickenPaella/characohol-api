package com.characohol.characohol_api.config

import io.r2dbc.spi.ConnectionFactory
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.core.io.ClassPathResource
import org.springframework.data.r2dbc.repository.config.EnableR2dbcRepositories
import org.springframework.r2dbc.connection.init.ConnectionFactoryInitializer
import org.springframework.r2dbc.connection.init.ResourceDatabasePopulator

@Configuration
@EnableR2dbcRepositories(basePackages = ["com.characohol.characohol_api.repository"])
class DatabaseConfig {

    @Bean
    fun initializer(connectionFactory: ConnectionFactory): ConnectionFactoryInitializer {
        val initializer = ConnectionFactoryInitializer()
        initializer.setConnectionFactory(connectionFactory)
        val populator = ResourceDatabasePopulator()
        // Uncomment and add schema.sql if needed
        // populator.addScript(ClassPathResource("schema.sql"))
        initializer.setDatabasePopulator(populator)
        return initializer
    }
}