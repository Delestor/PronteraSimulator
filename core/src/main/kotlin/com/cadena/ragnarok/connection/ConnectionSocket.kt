package com.cadena.ragnarok.connection

import com.badlogic.gdx.net.SocketHints
import com.cadena.ragnarok.component.PositionComponent
import java.io.BufferedReader
import java.io.InputStreamReader
import java.io.PrintWriter
import java.net.Socket
import kotlin.random.Random

class ConnectionSocket : Socket() {

    suspend fun sendMessageToServer(titulo: String) {
        val hints = SocketHints()
        //hints.connectTimeout = 4000
        //val client = Gdx.net.newClientSocket(Net.Protocol.TCP , "localhost", 9999, hints);
        val client = Socket("localhost", 9999)

        val waitTime: Long = Random.nextLong(0, 100)

        client.sendMessage("$titulo Hola Mundo")


        client.close()

    }

    private fun Socket.sendMessage(mensaje: String) {
        val output = PrintWriter(this.outputStream, true)
        val input = BufferedReader(InputStreamReader(this.inputStream))

        println {("El cliente envia: [$mensaje]")}
        output.println(mensaje)

        println {"El cliente recibe: [${input.readLine()}]"}
    }

    fun obtainPosition(): PositionComponent {
        val client = Socket("localhost", 9999)
        val newPosition = client.obtainPosition()
        client.close()
        return newPosition
    }

    private fun Socket.obtainPosition(): PositionComponent {
        val input = BufferedReader(InputStreamReader(this.inputStream))
        val posX = input.readLine().toFloat()
        val posY = input.readLine().toFloat()
        println("Actualizamos posicion x: ${posX}")
        println("Actualizamos posicion y: ${posY}")

        return PositionComponent(posX, posY)
    }

}
