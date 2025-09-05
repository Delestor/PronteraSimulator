package com.cadena.ragnarok.entities

import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.cadena.ragnarok.component.AnimationType
import com.cadena.ragnarok.component.AnimationUnit
import com.cadena.ragnarok.component.PositionComponent
import com.cadena.ragnarok.component.SizeComponent
import com.cadena.ragnarok.system.AnimationSystem

abstract class GameEntity(
    animationUnit: AnimationUnit,
    animationType: AnimationType,
    positionComponent: PositionComponent,
    private var sizeComponent: SizeComponent
) {

    var animationSystem: AnimationSystem =
        AnimationSystem(animationUnit, animationType, positionComponent, sizeComponent)
    var position: PositionComponent = positionComponent

    fun draw() {
        animationSystem.draw()
    }

    fun setSpriteBatch(batch: SpriteBatch) {
        animationSystem.setSpriteBatch(batch)
    }

    fun setSize(width: Float, height: Float) {
        sizeComponent.width = width
        sizeComponent.height = height
    }

    fun setPosition(posX: Float, posY: Float) {
        position.posX = posX
        position.posY = posY
    }

    /*fun getPosition(): PositionComponent {
        return position
    }*/

    fun updatePosition(posX: Float, posY: Float) {
        animationSystem.updatePosition(posX, posY)
    }
}
