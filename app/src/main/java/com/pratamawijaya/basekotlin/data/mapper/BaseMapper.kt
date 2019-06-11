package com.pratamawijaya.basekotlin.data.mapper


interface BaseMapper<M, D> {
    fun mapToDomain(model: M): D
    fun mapToModel(domain: D): M

    fun mapToListDomain(models: List<M>): List<D> {
        val listDomain = mutableListOf<D>()
        models.map { listDomain.add(mapToDomain(it)) }
        return listDomain
    }

    fun mapToListModel(domains: List<D>): List<M> {
        val listModels = mutableListOf<M>()
        domains.map { listModels.add(mapToModel(it)) }
        return listModels
    }
}