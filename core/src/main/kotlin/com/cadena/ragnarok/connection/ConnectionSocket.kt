package com.cadena.ragnarok.connection

import com.badlogic.gdx.net.SocketHints
import com.cadena.ragnarok.component.PositionComponent
import java.io.BufferedReader
import java.io.InputStreamReader
import java.io.PrintWriter
import java.net.Socket
import kotlin.random.Random

class ConnectionSocket : Socket() {

    lateinit var client : Socket

    init {
        client = Socket("localhost", 9999)
    }

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
        val newPosition = client.obtainPosition()
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

    fun sendPosition(positionComponent: PositionComponent){
        if (positionComponent.isPositionUpdated) {
            client.sendPosition(positionComponent)
            positionComponent.isPositionUpdated = false
        }
            //client.noSendPosition()
    }

    private fun Socket.sendPosition(positionComponent: PositionComponent){
        val output = PrintWriter(this.outputStream, true)
        //output.println("updatePosition")
        output.println("${positionComponent.posX} ${positionComponent.posY}")
    }

    private fun Socket.noSendPosition(){
        val output = PrintWriter(this.outputStream, true)
        output.println("noUpdatedPosition")
    }

    fun sendPositionTest(positionComponent: PositionComponent){
        //client.sendPosition(positionComponent)
        //client.close()
        client.use { socket ->
            //while (true) {
            println("Conectado al servidor localhost:$port")
            val writer = PrintWriter(socket.getOutputStream(), true)

            // Enviar un mensaje de ejemplo
            val message = "¡Hola desde el cliente!"
            writer.println(message)
            println("Enviado al servidor: $message")
            Thread.sleep(1000)
            //}
            // Cerrar automáticamente al salir del bloque use
        }
    }

}
