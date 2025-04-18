package com.characohol.characohol_api.domain

import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Table

@Table
data class Recommendation(
    @Id
    val id: Long? = null,
    val tag: String,
    val character: String,
    val message: String
)