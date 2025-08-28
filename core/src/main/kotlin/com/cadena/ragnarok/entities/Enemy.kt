package com.cadena.ragnarok.entities

import com.badlogic.gdx.math.MathUtils
import com.cadena.ragnarok.component.AnimationType
import com.cadena.ragnarok.component.AnimationUnit
import com.cadena.ragnarok.component.PositionComponent
import com.cadena.ragnarok.component.SizeComponent

class Enemy(
    animationUnit: AnimationUnit,
    animationType: AnimationType,
    positionComponent: PositionComponent = PositionComponent(0f, 0f),
    sizeComponent: SizeComponent = SizeComponent(1f, 1f)
) : GameEntity(animationUnit, animationType, positionComponent, sizeComponent) {

    var speed = 1f
    var opciones = listOf<Float>(0f, +speed, -speed)
    var moveX = if (MathUtils.randomBoolean()) +speed else -speed
    var moveY = if (MathUtils.randomBoolean()) +speed else -speed

    enum class State { IDLE, MOVING }

    private var currentState = State.IDLE
    private var stateTimer = MathUtils.random(0f, 3f)

    // Duraciones en segundos
    private val MOVING_DURATION = MathUtils.random(3f, 4f)
    private val IDLE_DURATION = MathUtils.random(3f, 4f)

    fun update(delta: Float) {
        stateTimer += delta

        when (currentState) {
            State.IDLE -> {
                if (stateTimer >= IDLE_DURATION) {
                    switchToMoving()
                }
            }

            State.MOVING -> {
                if (stateTimer >= MOVING_DURATION) {
                    switchToIdle()
                }
                handleMovement(delta)
            }
        }
    }

    private fun switchToMoving() {
        if (currentState == State.IDLE) {
            moveX = opciones.random()
            moveY = opciones.random()
        }
        currentState = State.MOVING
        stateTimer = 0f
    }

    private fun switchToIdle() {
        currentState = State.IDLE
        stateTimer = 0f
        // Detener movimiento
        moveX = 0f
        moveY = 0f
    }

    private fun handleMovement(delta: Float) {
        // Tu lógica de movimiento aquí
        this.updatePosition(+moveX * delta, +moveY * delta)
    }
}
