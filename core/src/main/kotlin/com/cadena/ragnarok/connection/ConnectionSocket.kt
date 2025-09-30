package com.cadena.ragnarok.connection

import com.badlogic.gdx.net.SocketHints
import com.cadena.ragnarok.component.PositionComponent
import kotlinx.serialization.json.Json
import java.io.BufferedReader
import java.io.InputStreamReader
import java.io.PrintWriter
import java.net.Socket
import kotlin.random.Random

class ConnectionSocket : Socket() {

    lateinit var client : Socket
    //val input = BufferedReader(InputStreamReader(this.inputStream))

    init {
        client = Socket("localhost", 9999)
    }

    fun obtainPosition(): PositionComponent {
        val newPosition = client.obtainPosition()
        return newPosition
    }



    fun readStringFromServer(): String{
        return client.readStringFromServer()
    }

    private fun Socket.readStringFromServer(): String{
        val input = BufferedReader(InputStreamReader(this.inputStream))
        val instruction = input.readLine()
        return instruction.toString()
    }

    private fun Socket.obtainPosition(): PositionComponent {
        val input = BufferedReader(InputStreamReader(this.inputStream))
        val posX = input.readLine().toFloat()
        val posY = input.readLine().toFloat()
        println("Actualizamos posicion x: ${posX}")
        println("Actualizamos posicion y: ${posY}")

        return PositionComponent(posX, posY)
    }

    fun sendPosition(positionComponent: PositionComponent, clientId: Int){
        if (positionComponent.isPositionUpdated) {
            client.sendPosition(positionComponent, clientId)
            positionComponent.isPositionUpdated = false
        }
            //client.noSendPosition()
    }

    private fun Socket.sendPosition(positionComponent: PositionComponent, clientId: Int){
        val output = PrintWriter(this.outputStream, true)
        //output.println(clientId)
        output.println("${positionComponent.posX} ${positionComponent.posY}")
    }

    fun obtainClientId(): Int {
        return client.readIntFromServer()
    }

    private fun Socket.readIntFromServer(): Int{
        val input = BufferedReader(InputStreamReader(this.inputStream))
        val clientId = input.readLine()
        return clientId.toInt()
    }

    object JsonConfig {
        val instance = Json {
            encodeDefaults = true
            ignoreUnknownKeys = true
        }
    }
}
