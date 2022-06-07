package com.feliz.apps.domain.model.common

interface Mapper<M, T> {
    fun toDomain(model: T): M
    fun fromDomain(model: M): T
    fun toDomainList(models: List<T>): List<M> = models.map { toDomain(it) }
    fun fromDomainList(models: List<M>): List<T> = models.map { fromDomain(it) }
}