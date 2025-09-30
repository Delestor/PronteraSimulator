package com.cadena.ragnarok.connection

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
sealed class ServerMessage {
    abstract val clientId: Int
    abstract val action: String
}

@Serializable
@SerialName("NewClientId")
data class ClientIdComponent(
    override val clientId: Int,
    override val action: String = "NewClientId"
) : ServerMessage()

@Serializable
@SerialName("SendingPositionClient")
data class ClientPositionComponent(
    override val clientId: Int,
    val posX: Float,
    val posY: Float,
    override val action: String = "SendingPositionClient"
) : ServerMessage()
