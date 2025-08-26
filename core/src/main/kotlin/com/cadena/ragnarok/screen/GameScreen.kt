package com.cadena.ragnarok.screen

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.Input
import com.badlogic.gdx.Screen
import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.graphics.g2d.Animation
import com.badlogic.gdx.graphics.g2d.Animation.PlayMode
import com.badlogic.gdx.graphics.g2d.Sprite
import com.badlogic.gdx.graphics.g2d.TextureAtlas
import com.badlogic.gdx.graphics.g2d.TextureRegion
import com.badlogic.gdx.utils.ScreenUtils
import com.cadena.ragnarok.Main
import com.cadena.ragnarok.component.AnimationType
import com.cadena.ragnarok.component.AnimationUnit
import com.cadena.ragnarok.system.AnimationSystem

class GameScreen(var game: Main) : Screen {

    val batch = game.batch
    val font = game.font
    val viewport = game.viewport

    lateinit var novice_male : Sprite
    lateinit var poring : Sprite
    lateinit var atlas: TextureAtlas

    lateinit var animatePoring: AnimationSystem
    lateinit var animateNovie: AnimationSystem

    init {
        atlas = TextureAtlas("graphics/ragnarokObjects.atlas")
        novice_male = Sprite(atlas.findRegion("novice_male/idle"))
        novice_male.setSize(1f, 1.5f)
        novice_male.setPosition(0f,0f)

        poring = Sprite(atlas.findRegion("poring/idle"))
        poring.setSize(1.3f, 1f)
        poring.setPosition(14f,0f)

        animatePoring = AnimationSystem(AnimationUnit.poring, AnimationType.idle)
        animateNovie = AnimationSystem(AnimationUnit.novice_male, AnimationType.walk_down)


    }

    override fun render(delta: Float) {
        input(delta)
        logic()
        draw()
    }

    private fun logic() {



    }

    private fun input(delta: Float) {
        val speed = 4f
        if(Gdx.input.isKeyPressed(Input.Keys.RIGHT)){
            novice_male.translateX(+speed*delta)
        }
        if(Gdx.input.isKeyPressed(Input.Keys.LEFT)){
            novice_male.translateX(-speed*delta)
        }
        if(Gdx.input.isKeyPressed(Input.Keys.UP)){
            novice_male.translateY(+speed*delta)
        }
        if(Gdx.input.isKeyPressed(Input.Keys.DOWN)){
            novice_male.translateY(-speed*delta)
        }
    }

    private fun draw() {
        ScreenUtils.clear(Color.DARK_GRAY)
        viewport.apply()
        batch.setProjectionMatrix(viewport.camera.combined)//Esta instrucción se ha de llamar después del viewport.apply()
        batch.begin()

        //novice_male.draw(batch)
        animatePoring.draw(batch)
        animateNovie.draw(batch)

        batch.end()
    }

    override fun show() {

    }

    override fun resize(width: Int, height: Int) {
        viewport.update(width, height, true)
    }

    override fun pause() {
    }

    override fun resume() {
    }

    override fun hide() {
    }

    override fun dispose() {
        batch.dispose()
        atlas.dispose()
    }

}
