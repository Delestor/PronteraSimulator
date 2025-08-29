package com.cadena.ragnarok.entities

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.Input
import com.cadena.ragnarok.component.AnimationType
import com.cadena.ragnarok.component.AnimationUnit
import com.cadena.ragnarok.component.PositionComponent
import com.cadena.ragnarok.component.SizeComponent
import com.cadena.ragnarok.system.PlayerInputSystem

class PlayableCharacter(
    animationUnit: AnimationUnit,
    animationType: AnimationType,
    positionComponent: PositionComponent = PositionComponent(0f, 0f),
    sizeComponent: SizeComponent = SizeComponent(1f, 1f)
) : Character(animationUnit, animationType, positionComponent, sizeComponent) {

    val playerInputSystem: PlayerInputSystem = PlayerInputSystem()

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

}
