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

    init {
        atlas = TextureAtlas("graphics/ragnarokObjects.atlas")
        idleAnimation = Animation(1/8f, atlas.findRegions("${unit.toString()}/${type.toString()}"), PlayMode.LOOP)
    }

    fun draw(batch: SpriteBatch){
        stateTime += Gdx.graphics.deltaTime
        val currentFrame = idleAnimation.getKeyFrame(stateTime, true)
        batch.draw(currentFrame, 1f, 1f, 1f, 1f)
    }
}
