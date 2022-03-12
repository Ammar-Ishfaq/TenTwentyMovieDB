package com.muhammad_ammar.tentwenty.models.utilityModels

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
open class BaseResponse {
    @SerialName(value = "Exceptions")
    var Exceptions: String = ""

    @SerialName(value = "Status")
    val Status: Int = 0

    @SerialName(value = "ResultType")
    var ResultType: Int = 0


    @SerialName(value = "Message")
    var Message: String = ""
}