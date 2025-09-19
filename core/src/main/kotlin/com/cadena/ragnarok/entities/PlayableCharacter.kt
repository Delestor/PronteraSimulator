package com.cadena.ragnarok.entities

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.Input
import com.badlogic.gdx.Input.Keys.DOWN
import com.badlogic.gdx.Input.Keys.LEFT
import com.badlogic.gdx.Input.Keys.RIGHT
import com.badlogic.gdx.Input.Keys.UP
import com.cadena.ragnarok.component.AnimationType
import com.cadena.ragnarok.component.AnimationUnit
import com.cadena.ragnarok.component.PositionComponent
import com.cadena.ragnarok.component.SizeComponent
import com.cadena.ragnarok.connection.ConnectionSocket
import com.cadena.ragnarok.system.PlayerInputSystem
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.io.PrintWriter
import java.net.Socket

class PlayableCharacter(
    animationUnit: AnimationUnit,
    animationType: AnimationType,
    positionComponent: PositionComponent = PositionComponent(0f, 0f),
    sizeComponent: SizeComponent = SizeComponent(1f, 1f)
) : Character(animationUnit, animationType, positionComponent, sizeComponent) {

    val playerInputSystem: PlayerInputSystem = PlayerInputSystem()
    private var job : Job? = null
    private val scope = CoroutineScope(Dispatchers.IO)
    val connection : ConnectionSocket = ConnectionSocket()
    var clientInit : Boolean = false

    fun input(delta: Float) {
        val speed = 4f

        this.animationSystem.updateAnimationType(AnimationType.idle)

        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
            //novice_male.translateX(+speed*delta)
            this.updatePosition(+speed * delta, 0f)
            //this.animationSystem.updateAnimationType(AnimationType.walk_rigt)
        }
        if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
            //novice_male.translateX(-speed*delta)
            this.updatePosition(-speed * delta, 0f)
            this.animationSystem.updateAnimationType(AnimationType.walk_left)
        }
        if (Gdx.input.isKeyPressed(Input.Keys.UP)) {
            //novice_male.translateY(+speed*delta)
            this.updatePosition(0f, +speed * delta)
            //this.animationSystem.updateAnimationType(AnimationType.walk_up)
        }
        if (Gdx.input.isKeyPressed(Input.Keys.DOWN)) {
            //novice_male.translateY(-speed*delta)
            this.updatePosition(0f, -speed * delta)
            this.animationSystem.updateAnimationType(AnimationType.walk_down)
        }

    }

    fun sendPosition(){
        scope.sendPosition()
    }

    private fun CoroutineScope.sendPosition(){
        job?.cancel()

        job = launch{
            while (true){
                try{
                    println("Sending position...")
                    connection.sendPosition(position)
                    delay(1000)
                }catch (e: Exception){
                    println("Connection failed: ${e.message}")
                    delay(5000) // Reintenta después de 5 segundos
                }
            }
        }
    }

}
