package com.cadena.ragnarok.entities

import com.cadena.ragnarok.component.AnimationType
import com.cadena.ragnarok.component.AnimationUnit
import com.cadena.ragnarok.component.PositionComponent
import com.cadena.ragnarok.component.SizeComponent
import com.cadena.ragnarok.connection.ConnectionSocket
import com.cadena.ragnarok.connection.GlobalConnection
import kotlinx.coroutines.*

class OnlineCharacter(
    animationUnit: AnimationUnit,
    animationType: AnimationType,
    positionComponent: PositionComponent = PositionComponent(0f, 0f),
    sizeComponent: SizeComponent = SizeComponent(1f, 1f),
    clientId: Int
): Character(animationUnit, animationType, positionComponent, sizeComponent) {

    private val scope = CoroutineScope(Dispatchers.IO)
    private var job : Job? = null
    val connection : ConnectionSocket = GlobalConnection.connection

    fun updatePositionFromServer(){
        scope.obtainPositionFromServer()
        //draw()
    }

    private fun CoroutineScope.obtainPositionFromServer(): PositionComponent {
        //TODO: en principio se va a borrar, se gestiona desde el OnlineCharacterConnectionHandler
        var newPosition = position

        job = launch{
            while (true){
                try{
                    newPosition = connection.obtainPosition()
                    setPosition(newPosition.posX, newPosition.posY)
                    delay(10)
                }catch (e: Exception){
                    println("Connection failed: ${e.message}")
                    delay(5000) // Reintenta después de 5 segundos
                }
            }
        }

        return newPosition
    }

}
