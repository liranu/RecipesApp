package com.example.cal_project2.model

import com.google.gson.annotations.SerializedName

data class RecipeModel(

    @SerializedName("calories")
    val calories : String? = null,
    @SerializedName("carbos")
    val carbos : String? = null,
    @SerializedName("description")
    val description : String? = null,
    @SerializedName("difficulty")
    private val difficulty : Int? = null,
    @SerializedName("fats")
    val fats : String? = null,
    @SerializedName("headline")
    private val headlines : String? = null,
    @SerializedName("id")
    private val id : String? = null,
    @SerializedName("image") val image : String? = null,
    @SerializedName("name") val name : String? = null,
    @SerializedName("proteins")
    private val proteins : String? = null,
    @SerializedName("thumb")
    val thumb : String? = null,
    @SerializedName("time")
    private val time : String? = null) {
}