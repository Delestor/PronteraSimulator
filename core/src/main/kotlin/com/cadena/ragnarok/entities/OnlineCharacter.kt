package com.cadena.ragnarok.entities

import com.cadena.ragnarok.component.AnimationType
import com.cadena.ragnarok.component.AnimationUnit
import com.cadena.ragnarok.component.PositionComponent
import com.cadena.ragnarok.component.SizeComponent
import com.cadena.ragnarok.connection.ConnectionSocket
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class OnlineCharacter(
    animationUnit: AnimationUnit,
    animationType: AnimationType,
    positionComponent: PositionComponent = PositionComponent(0f, 0f),
    sizeComponent: SizeComponent = SizeComponent(1f, 1f)
): Character(animationUnit, animationType, positionComponent, sizeComponent) {

    private val scope = CoroutineScope(Dispatchers.IO)
    private var job : Job? = null

    fun updatePositionFromServer(){
        scope.obtainPositionFromServer()
        draw()
    }

    private fun CoroutineScope.obtainPositionFromServer(): PositionComponent {

        var newPosition = position

        job = launch{
            try{
                val connection = ConnectionSocket()
                newPosition = connection.obtainPosition()
                updatePosition(newPosition.posX, newPosition.posY)
            }catch (e: Exception){
                //println("The server is not reachable.")
            }
        }

        return newPosition
    }

}
