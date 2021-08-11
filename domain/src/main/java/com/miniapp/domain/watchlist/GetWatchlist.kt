package com.miniapp.domain.watchlist

data class GetWatchlist(
    val type: Int,
    val message: String,
    val metaData: MetaData? = null,
    val data: List<DataItem>? = null
) {
    data class MetaData(
        val count: Int
    )

    data class DataItem(
        val display: Display? = null,
        val coinInfo: CoinInfo? = null
    ){
        data class CoinInfo(
            val internal: String,
            val rating: Rating,
            val blockTime: Double,
            val imageUrl: String,
            val maxSupply: Double,
            val documentType: String,
            val algorithm: String,
            val url: String,
            val name: String,
            val type: Int,
            val proofType: String,
            val fullName: String,
            val id: String,
            val blockNumber: Int,
            val blockReward: Double
        ){
            data class Rating(
                val weiss: Weiss
            ){
                data class Weiss(
                    val rating: String,
                    val technologyAdoptionRating: String,
                    val marketPerformanceRating: String
                )
            }
        }
        data class Display(
            val usd: USD
        ){
            data class USD(
                val conversionType: String,
                val lastTradeId: String,
                val changedDay: String,
                val supply: String,
                val imageUrl: String,
                val volumeDay: String,
                val volume24Hours: String,
                val market: String,
                val price: String
            )
        }
    }
}