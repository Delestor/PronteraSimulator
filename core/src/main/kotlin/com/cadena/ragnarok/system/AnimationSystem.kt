package com.cadena.ragnarok.system

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.g2d.Animation
import com.badlogic.gdx.graphics.g2d.Animation.PlayMode
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.graphics.g2d.TextureAtlas
import com.badlogic.gdx.graphics.g2d.TextureRegion
import com.cadena.ragnarok.component.AnimationType
import com.cadena.ragnarok.component.AnimationUnit

class AnimationSystem(var unit: AnimationUnit, var type: AnimationType) {

    lateinit var atlas: TextureAtlas
    lateinit var idleAnimation: Animation<TextureRegion>
    var stateTime : Float = 0f
    lateinit var batch: SpriteBatch

    var posX: Float = 1f
    var posY: Float = 1f
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
        this.posX = posX
        this.posY = posY
    }

    fun updatePosition(posX: Float, posY: Float){
        this.posX += posX
        this.posY += posY
    }

    fun setSize(width: Float, height: Float){
        this.width = width
        this.height = height
    }

    fun draw(){
        stateTime += Gdx.graphics.deltaTime
        val currentFrame = idleAnimation.getKeyFrame(stateTime, true)
        batch.draw(currentFrame, posX, posY, width, height)
    }
}
