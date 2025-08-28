package com.cadena.ragnarok.system

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.g2d.Animation
import com.badlogic.gdx.graphics.g2d.Animation.PlayMode
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.graphics.g2d.TextureAtlas
import com.badlogic.gdx.graphics.g2d.TextureRegion
import com.cadena.ragnarok.component.AnimationType
import com.cadena.ragnarok.component.AnimationUnit
import com.cadena.ragnarok.component.PositionComponent
import com.cadena.ragnarok.component.SizeComponent

class AnimationSystem(
    var unit: AnimationUnit,
    var type: AnimationType,
    var positionComponent: PositionComponent,
    var sizeComponent: SizeComponent
) {

    var atlas: TextureAtlas = TextureAtlas("graphics/ragnarokObjects.atlas")
    private var animation: Animation<TextureRegion> =
        Animation(1 / 8f, atlas.findRegions("${unit.toString()}/${type.toString()}"), PlayMode.LOOP)

    private var stateTime: Float = 0f
    private lateinit var batch: SpriteBatch

    var position: PositionComponent = positionComponent
    var size: SizeComponent = sizeComponent

    fun setSpriteBatch(batch: SpriteBatch) {
        this.batch = batch
    }

    fun updateAnimationType(animationType: AnimationType){
        this.type = animationType
        animation = Animation(1 / 8f, atlas.findRegions("${unit.toString()}/${type.toString()}"), PlayMode.LOOP)
    }

    fun setPosition(posX: Float, posY: Float) {
        position.posX = posX
        position.posY = posY
    }

    fun updatePosition(posX: Float, posY: Float) {
        position.posX += posX
        position.posY += posY
    }

    fun setSize(width: Float, height: Float) {
        size.width = width
        size.height = height
    }

    fun draw() {
        stateTime += Gdx.graphics.deltaTime
        val currentFrame = animation.getKeyFrame(stateTime, true)
        batch.draw(currentFrame, position.posX, position.posY, size.width, size.height)
    }
}
