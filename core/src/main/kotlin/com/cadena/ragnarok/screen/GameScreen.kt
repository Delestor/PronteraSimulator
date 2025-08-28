package com.cadena.ragnarok.screen

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.Input
import com.badlogic.gdx.Screen
import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.graphics.g2d.Sprite
import com.badlogic.gdx.graphics.g2d.TextureAtlas
import com.badlogic.gdx.utils.ScreenUtils
import com.cadena.ragnarok.Main
import com.cadena.ragnarok.component.AnimationType
import com.cadena.ragnarok.component.AnimationUnit
import com.cadena.ragnarok.component.PositionComponent
import com.cadena.ragnarok.component.SizeComponent
import com.cadena.ragnarok.entities.Character
import com.cadena.ragnarok.entities.Enemy
import com.cadena.ragnarok.entities.GameEntity
import com.cadena.ragnarok.entities.PlayableCharacter
import com.cadena.ragnarok.system.AnimationSystem

class GameScreen(var game: Main) : Screen {

    val batch = game.batch
    val font = game.font
    val viewport = game.viewport

    var poringEntity: Enemy
    var noviceEntity: PlayableCharacter

    init {
        poringEntity = Enemy(AnimationUnit.poring, AnimationType.idle, PositionComponent(1f, 1f), SizeComponent(1f, 1f))
        poringEntity.setSpriteBatch(batch)

        noviceEntity = PlayableCharacter(AnimationUnit.novice_male, AnimationType.walk_down, PositionComponent(5f, 5f), SizeComponent(1f, 1.5f))
        noviceEntity.setSpriteBatch(batch)

    }

    override fun render(delta: Float) {
        input(delta)
        logic()
        draw()
    }

    private fun logic() {

    }

    private fun input(delta: Float) {
        noviceEntity.input(delta)
    }

    private fun draw() {
        ScreenUtils.clear(Color.DARK_GRAY)
        viewport.apply()
        batch.setProjectionMatrix(viewport.camera.combined)//Esta instrucción se ha de llamar después del viewport.apply()
        batch.begin()

        poringEntity.draw()
        noviceEntity.draw()

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
        //atlas.dispose()
    }

}
