package com.cadena.ragnarok.entities

import com.cadena.ragnarok.component.AnimationType
import com.cadena.ragnarok.component.AnimationUnit
import com.cadena.ragnarok.component.PositionComponent

class Character(
    width: Float,
    height: Float,
    animationUnit: AnimationUnit,
    animationType: AnimationType,
    positionComponent: PositionComponent = PositionComponent(0f, 0f)
) : GameEntity(width, height, animationUnit, animationType, positionComponent) {


}
