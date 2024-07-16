package com.zaklabs.pokey.core.model.mapper

/**
 * Repository mapper
 *
 * @param DTO
 * @param DBEntity
 * @param Data
 */
interface RepositoryMapper<in DTO, DBEntity, out Data> {
    /**
     * Map DTO to DB entity
     *
     * @param dto
     * @return
     */
    fun dtoToDBEntity(dto: DTO): DBEntity

    /**
     * Map DBEntity to DATA
     *
     * @param dbEntity
     * @return
     */
    fun dbEntityToData(dbEntity: DBEntity): Data
}
