package com.nocountry.s1123mkotlin.farmacias

data class RouteResponse(
    val coordinates: List<List<Double>>,
    val route: List<RouteFeature>,
)

data class RouteFeature(
    val geometry: RouteGeometry
)

data class RouteGeometry(
    val coordinates: List<List<Double>>
)
