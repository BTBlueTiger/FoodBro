package com.bluetiger.foodbrocompose.feature_open_food_facts.domain.model.product_general

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ProductGeneral(
    @SerialName("image_url")
    val imageByteArray: ByteArray,
    val brands: String,
    val categories: String,
    @SerialName("food_groups")
    val foodGroups: String,
    val packaging: String,
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as ProductGeneral

        if (!imageByteArray.contentEquals(other.imageByteArray)) return false
        if (brands != other.brands) return false
        if (categories != other.categories) return false
        if (foodGroups != other.foodGroups) return false
        if (packaging != other.packaging) return false

        return true
    }

    override fun hashCode(): Int {
        var result = imageByteArray.contentHashCode()
        result = 31 * result + brands.hashCode()
        result = 31 * result + categories.hashCode()
        result = 31 * result + foodGroups.hashCode()
        result = 31 * result + packaging.hashCode()
        return result
    }
}
