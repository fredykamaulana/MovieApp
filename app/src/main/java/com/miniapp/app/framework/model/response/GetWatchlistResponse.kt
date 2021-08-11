package com.miniapp.app.framework.model.response

import com.miniapp.domain.watchlist.GetWatchlist
import com.squareup.moshi.Json

data class GetWatchlistResponse(
	@field:Json(name = "Type")
	val type: Int? = null,
	@field:Json(name = "Message")
	val message: String? = null,
	@field:Json(name = "MetaData")
	val metaData: MetaData? = null,
	@field:Json(name = "Data")
	val data: List<DataItem?>? = null
) {
    data class MetaData(
		@field:Json(name = "Count")
		val count: Int? = null
	)

    data class DataItem(
		@field:Json(name = "DISPLAY")
		val display: Display? = null,
		@field:Json(name = "CoinInfo")
		val coinInfo: CoinInfo? = null
	) {
        data class CoinInfo(
			@field:Json(name = "Internal")
			val internal: String? = null,
			@field:Json(name = "Rating")
			val rating: Rating? = null,
			@field:Json(name = "BlockTime")
			val blockTime: Double? = null,
			@field:Json(name = "ImageUrl")
			val imageUrl: String? = null,
			@field:Json(name = "MaxSupply")
			val maxSupply: Double? = null,
			@field:Json(name = "DocumentType")
			val documentType: String? = null,
			@field:Json(name = "Algorithm")
			val algorithm: String? = null,
			@field:Json(name = "Url")
			val url: String? = null,
			@field:Json(name = "Name")
			val name: String? = null,
			@field:Json(name = "Type")
			val type: Int? = null,
			@field:Json(name = "ProofType")
			val proofType: String? = null,
			@field:Json(name = "FullName")
			val fullName: String? = null,
			@field:Json(name = "Id")
			val id: String? = null,
			@field:Json(name = "BlockNumber")
			val blockNumber: Int? = null,
			@field:Json(name = "BlockReward")
			val blockReward: Double? = null
		) {
            data class Rating(
				@field:Json(name = "Weiss")
				val weiss: Weiss? = null
			) {
                data class Weiss(
					@field:Json(name = "Rating")
					val rating: String? = null,
					@field:Json(name = "TechnologyAdoptionRating")
					val technologyAdoptionRating: String? = null,
					@field:Json(name = "MarketPerformanceRating")
					val marketPerformanceRating: String? = null
				)
            }
        }

        data class Display(
			@field:Json(name = "USD")
			val usd: USD? = null
		) {
            data class USD(
				@field:Json(name = "CONVERSIONTYPE")
				val conversionType: String? = null,
				@field:Json(name = "LASTTRADEID")
				val lastTradeId: String? = null,
				@field:Json(name = "CHANGEDAY")
				val changedDay: String? = null,
				@field:Json(name = "SUPPLY")
				val supply: String? = null,
				@field:Json(name = "IMAGEURL")
				val imageUrl: String? = null,
				@field:Json(name = "VOLUMEDAY")
				val volumeDay: String? = null,
				@field:Json(name = "VOLUME24HOUR")
				val volume24Hours: String? = null,
				@field:Json(name = "MARKET")
				val market: String? = null,
				@field:Json(name = "PRICE")
				val price: String? = null
			)
        }
    }

    fun map(): GetWatchlist =
        GetWatchlist(
			type = this.type ?: 0,
			message = this.message ?: "",
			metaData = this.metaData?.map(),
			data = this.data?.map()
		)

    fun MetaData?.map(): GetWatchlist.MetaData =
        GetWatchlist.MetaData(
			count = this?.count ?: 0
		)

    fun List<DataItem?>?.map(): List<GetWatchlist.DataItem>? =
        this?.map {
            GetWatchlist.DataItem(
				display = it?.display.map(),
				coinInfo = it?.coinInfo.map()
			)
        }

    fun DataItem.CoinInfo?.map(): GetWatchlist.DataItem.CoinInfo =
        GetWatchlist.DataItem.CoinInfo(
			internal = this?.internal ?: "",
			rating = this?.rating.map(),
			blockTime = this?.blockTime ?: 0.0,
			imageUrl = this?.imageUrl ?: "",
			maxSupply = this?.maxSupply ?: 0.0,
			documentType = this?.documentType ?: "",
			algorithm = this?.algorithm ?: "",
			url = this?.url ?: "",
			name = this?.name ?: "",
			type = this?.type ?: 0,
			proofType = this?.proofType ?: "",
			fullName = this?.fullName ?: "",
			id = this?.id ?: "",
			blockNumber = this?.blockNumber ?: 0,
			blockReward = this?.blockReward ?: 0.0
		)

    fun DataItem.CoinInfo.Rating?.map(): GetWatchlist.DataItem.CoinInfo.Rating =
        GetWatchlist.DataItem.CoinInfo.Rating(
			weiss = this?.weiss.map()
		)

    fun DataItem.CoinInfo.Rating.Weiss?.map(): GetWatchlist.DataItem.CoinInfo.Rating.Weiss =
        GetWatchlist.DataItem.CoinInfo.Rating.Weiss(
			rating = this?.rating ?: "-",
			technologyAdoptionRating = this?.technologyAdoptionRating ?: "",
			marketPerformanceRating = this?.marketPerformanceRating ?: ""
		)

    fun DataItem.Display?.map(): GetWatchlist.DataItem.Display =
        GetWatchlist.DataItem.Display(
			usd = this?.usd.map()
		)

    fun DataItem.Display.USD?.map(): GetWatchlist.DataItem.Display.USD =
        GetWatchlist.DataItem.Display.USD(
			conversionType = this?.conversionType ?: "",
			lastTradeId = this?.lastTradeId ?: "",
			changedDay = this?.changedDay ?: "",
			supply = this?.supply ?: "",
			imageUrl = this?.imageUrl ?: "",
			volumeDay = this?.volumeDay ?: "",
			volume24Hours = this?.volume24Hours ?: "",
			market = this?.market ?: "",
			price = this?.price ?: ""
		)
}