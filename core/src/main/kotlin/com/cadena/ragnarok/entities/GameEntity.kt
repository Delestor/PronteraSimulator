package com.cadena.ragnarok.entities

import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.cadena.ragnarok.component.AnimationType
import com.cadena.ragnarok.component.AnimationUnit
import com.cadena.ragnarok.system.AnimationSystem

abstract class GameEntity(
    var posX: Float,
    var posY: Float,
    var width: Float,
    var height: Float,
    var animationUnit: AnimationUnit,
    var animationType: AnimationType
) {

    var animationSystem: AnimationSystem = AnimationSystem(animationUnit, animationType)
    lateinit var batch: SpriteBatch

    init {
        animationSystem.setPosition(posX, posY)
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
        this.posX = posX
        this.posY = posY
    }

    fun updatePosition(posX: Float, posY: Float) {
        this.posX += posX
        this.posY += posY
        animationSystem.updatePosition(posX, posY)
    }
}
