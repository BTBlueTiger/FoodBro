package com.bluetiger.foodbrocompose.feature_open_food_facts.data.remote

import io.ktor.client.utils.EmptyContent.headers
import io.ktor.http.ContentType
import io.ktor.http.HttpHeaders
import io.ktor.http.URLBuilder
import io.ktor.http.takeFrom
import java.net.URL


sealed class OpenFoodFactsRoutes {

    abstract fun getUrlBuilder(): URLBuilder.() -> Unit
    fun baseUrlBuilder(country: Country) =
        "https://${country.preFix}.openfoodfacts.org/"

    enum class Country(val preFix: String){
        NONE("world"),
        GERMANY("de")
    }

    class Barcode(private val barcode: String) : OpenFoodFactsRoutes(){
        override fun getUrlBuilder(): URLBuilder.() -> Unit = {
            val base = baseUrlBuilder(Country.GERMANY)
            val uri = base + "api/2/product/$barcode.json"
            takeFrom(uri)
        }
    }
}