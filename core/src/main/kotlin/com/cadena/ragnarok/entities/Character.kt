package com.cadena.ragnarok.entities

import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.cadena.ragnarok.component.AnimationType
import com.cadena.ragnarok.component.AnimationUnit

class Character(
    override var posX : Long,
    override var posY : Long,
    override var animationUnit: AnimationUnit,
    override var animationType: AnimationType,
    override var batch: SpriteBatch
) : GameEntity(posX, posY, animationUnit, animationType, batch) {


}
