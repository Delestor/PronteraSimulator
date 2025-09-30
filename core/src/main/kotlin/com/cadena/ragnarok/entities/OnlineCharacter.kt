package com.cadena.ragnarok.entities

import com.cadena.ragnarok.component.AnimationType
import com.cadena.ragnarok.component.AnimationUnit
import com.cadena.ragnarok.component.PositionComponent
import com.cadena.ragnarok.component.SizeComponent
import com.cadena.ragnarok.connection.ConnectionSocket
import com.cadena.ragnarok.connection.GlobalConnection
import kotlinx.coroutines.*

class OnlineCharacter(
    animationUnit: AnimationUnit,
    animationType: AnimationType,
    positionComponent: PositionComponent = PositionComponent(0f, 0f),
    sizeComponent: SizeComponent = SizeComponent(1f, 1f),
    clientId: Int
): Character(animationUnit, animationType, positionComponent, sizeComponent) {



}
