package com.cadena.ragnarok.entities

import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.cadena.ragnarok.component.AnimationType
import com.cadena.ragnarok.component.AnimationUnit
import com.cadena.ragnarok.system.AnimationSystem

abstract class GameEntity(
    open var posX : Long,
    open var posY : Long,
    open var animationUnit: AnimationUnit,
    open var animationType: AnimationType,
    open var batch: SpriteBatch) {

    var animationSystem: AnimationSystem = AnimationSystem(animationUnit, animationType)

    init {
        animationSystem.draw(batch)
    }

    fun setPosition() {
        TODO("Se ha de asignar la posicion inicial para la Entity")
    }

    fun updatePosition(){
        TODO("Se ha de poder actualziar la posición de la Entity")
    }
}
