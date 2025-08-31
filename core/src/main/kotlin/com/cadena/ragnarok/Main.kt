package com.cadena.ragnarok

import com.badlogic.gdx.Game
import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.OrthographicCamera
import com.badlogic.gdx.graphics.g2d.BitmapFont
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.utils.viewport.ExtendViewport
import com.cadena.ragnarok.screen.GameScreen

/** [com.badlogic.gdx.ApplicationListener] implementation shared by all platforms. */
class Main : Game() {
    lateinit var batch: SpriteBatch
    lateinit var viewport: ExtendViewport
    lateinit var font: BitmapFont
    lateinit var camera: OrthographicCamera

    override fun create() {
        // Configurar la cámara
        camera = OrthographicCamera()

        viewport = ExtendViewport(16f, 9f, camera)
        batch = SpriteBatch()

        font = BitmapFont()
        font.setUseIntegerPositions(false)
        font.data.scale(viewport.worldHeight / Gdx.graphics.height)

        camera.setToOrtho(false, viewport.minWorldWidth, viewport.minWorldHeight) // Ajusta al tamaño de tu juego

        this.screen = GameScreen(this)
    }

    override fun render() {
        super.render()
    }

    override fun dispose() {
        batch.dispose()
        font.dispose()
    }
}
