package com.cadena.ragnarok.connection

import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.cadena.ragnarok.component.AnimationType
import com.cadena.ragnarok.component.AnimationUnit
import com.cadena.ragnarok.component.PositionComponent
import com.cadena.ragnarok.component.SizeComponent
import com.cadena.ragnarok.entities.OnlineCharacter
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.serialization.json.Json

class OnlineCharacterConnectionHandler(var batch: SpriteBatch) {

    var onlineCharacterList = GlobalOnlineCharacters.onlineCharacterList
    val connection : ConnectionSocket = GlobalConnection.connection
    private val scope = CoroutineScope(Dispatchers.IO)


    fun run(){
        scope.run()
    }

    private fun CoroutineScope.run(){
        scope.launch{
            while (true) {
                //1.Comprobar si se ha conectado un nuevo cliente.

                val serverAction = connection.readStringFromServer()
                val msg = parseServerAction(serverAction)

                when (msg) {
                    is ClientIdComponent -> addNewOnlineCharacter(msg)
                    is ClientPositionComponent -> updatePositionFromServer(msg)
                    else -> {}
                }
                //2.Asignar el nuevo cliente a un nuevo OnlineCharacter.
                //3.Actualizar posiciones de todos los OnlineCharacters.
            }
        }
    }

    private fun updatePositionFromServer(msg: ClientPositionComponent) {
        val clientId = msg.clientId
        if(onlineCharacterList.size > clientId && clientId != GlobalConnection.clientId) {
            val newPosition = PositionComponent(msg.posX, msg.posY)
            val onlineNovice = onlineCharacterList[clientId]

            onlineNovice.setPosition(newPosition.posX, newPosition.posY)
        }else{
            println("Aún no se han cargado el cliente $clientId")
        }

    }

    private fun addNewOnlineCharacter(msg: ClientIdComponent) {

        if(GlobalConnection.clientId == -1){
            //inicializamos la variable clientId que nos asigna el servidor.
            GlobalConnection.clientId = msg.clientId
        }
        val clientId = msg.clientId
        GlobalOnlineCharacters.pendingOnlineChartersList.add(clientId)

    }
}

fun parseServerAction(jsonString: String): ServerMessage {
    // Configuración igual que la del servidor si quieres defaults
    return JsonConfig.instance.decodeFromString(jsonString)
}

object JsonConfig {
    val instance = Json {
        classDiscriminator = "action"
        encodeDefaults = true
        ignoreUnknownKeys = true
    }
}
