package com.cadena.ragnarok.entities

import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.cadena.ragnarok.component.AnimationType
import com.cadena.ragnarok.component.AnimationUnit
import com.cadena.ragnarok.component.PositionComponent
import com.cadena.ragnarok.system.AnimationSystem

abstract class GameEntity(
    var width: Float,
    var height: Float,
    var animationUnit: AnimationUnit,
    var animationType: AnimationType,
    var positionComponent: PositionComponent = PositionComponent(0f, 0f)
) {

    var animationSystem: AnimationSystem = AnimationSystem(animationUnit, animationType, positionComponent)
    var position : PositionComponent = positionComponent
    lateinit var batch: SpriteBatch

    init {
        animationSystem.setSize(width, height)
    }

    fun draw() {
        animationSystem.draw()
    }

    fun setSpriteBatch(batch: SpriteBatch) {
        animationSystem.setSpriteBatch(batch)
    }

    fun setSize(width: Float, height: Float) {
        this.width = width
        this.height = height
    }

    fun setPosition(posX: Float, posY: Float) {
        position.posX = posX
        position.posY = posY
    }

    fun updatePosition(posX: Float, posY: Float) {
        animationSystem.updatePosition(posX, posY)
    }
}
