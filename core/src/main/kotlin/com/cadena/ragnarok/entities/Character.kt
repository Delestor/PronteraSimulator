package com.cadena.ragnarok.entities

import com.cadena.ragnarok.component.AnimationType
import com.cadena.ragnarok.component.AnimationUnit

class Character(
    posX: Float,
    posY: Float,
    width: Float,
    height: Float,
    animationUnit: AnimationUnit,
    animationType: AnimationType
) : GameEntity(posX, posY, width, height, animationUnit, animationType) {


}
