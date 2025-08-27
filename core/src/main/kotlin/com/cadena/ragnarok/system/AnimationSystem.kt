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

class AnimationSystem(var unit: AnimationUnit, var type: AnimationType, var positionComponent: PositionComponent = PositionComponent(0f, 0f)) {

    lateinit var atlas: TextureAtlas
    lateinit var idleAnimation: Animation<TextureRegion>
    var stateTime : Float = 0f
    lateinit var batch: SpriteBatch

    var position : PositionComponent = positionComponent
    var width: Float = 1f
    var height: Float = 1f

    init {
        atlas = TextureAtlas("graphics/ragnarokObjects.atlas")
        idleAnimation = Animation(1/8f, atlas.findRegions("${unit.toString()}/${type.toString()}"), PlayMode.LOOP)
    }

    fun setSpriteBatch(batch: SpriteBatch) {
        this.batch = batch
    }

    fun setPosition(posX: Float, posY: Float){
        position.posX = posX
        position.posY = posY
    }

    fun updatePosition(posX: Float, posY: Float){
        position.posX += posX
        position.posY += posY
    }

    fun setSize(width: Float, height: Float){
        this.width = width
        this.height = height
    }

    fun draw(){
        stateTime += Gdx.graphics.deltaTime
        val currentFrame = idleAnimation.getKeyFrame(stateTime, true)
        batch.draw(currentFrame, position.posX, position.posY, width, height)
    }
}
