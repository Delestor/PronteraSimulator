package com.cadena.ragnarok.screen

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.Screen
import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.graphics.OrthographicCamera
import com.badlogic.gdx.maps.tiled.AtlasTmxMapLoader
import com.badlogic.gdx.maps.tiled.TiledMap
import com.badlogic.gdx.maps.tiled.TmxMapLoader
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer
import com.badlogic.gdx.math.MathUtils
import com.badlogic.gdx.utils.ScreenUtils
import com.cadena.ragnarok.Main
import com.cadena.ragnarok.component.AnimationType
import com.cadena.ragnarok.component.AnimationUnit
import com.cadena.ragnarok.component.PositionComponent
import com.cadena.ragnarok.component.SizeComponent
import com.cadena.ragnarok.entities.Enemy
import com.cadena.ragnarok.entities.PlayableCharacter

class GameScreen(var game: Main) : Screen {

    val batch = game.batch
    val font = game.font
    val viewport = game.viewport

    var poringEntity: Enemy
    var poringList = mutableListOf<Enemy>()
    var noviceEntity: PlayableCharacter

    private lateinit var map: TiledMap
    private lateinit var renderer: OrthogonalTiledMapRenderer
    private lateinit var camera: OrthographicCamera

    init {

        for (i in 0..100) {
            var x = MathUtils.random(0f, 15f)
            var y = MathUtils.random(0f, 15f)
            var newPoring =
                Enemy(AnimationUnit.poring, AnimationType.idle, PositionComponent(x, y), SizeComponent(1f, 1f))
            newPoring.setSpriteBatch(batch)
            poringList.add(newPoring)
        }

        poringEntity = Enemy(AnimationUnit.poring, AnimationType.idle, PositionComponent(1f, 1f), SizeComponent(1f, 1f))
        poringEntity.setSpriteBatch(batch)

        noviceEntity = PlayableCharacter(
            AnimationUnit.novice_male,
            AnimationType.walk_down,
            PositionComponent(5f, 5f),
            SizeComponent(1f, 1.5f)
        )
        noviceEntity.setSpriteBatch(batch)

        // Cargar el mapa TMX usando AtlasTmxMapLoader (para usar el atlas)
        map = TmxMapLoader().load("map/map1.tmx")

        // Crear el renderer para renderizar el mapa
        renderer = OrthogonalTiledMapRenderer(map)

        // Configurar la cámara
        camera = OrthographicCamera()
        camera.setToOrtho(false, 800f, 480f) // Ajusta al tamaño de tu juego
        camera.update()

    }

    override fun render(delta: Float) {
        input(delta)
        logic()
        draw()
    }

    private fun logic() {
        poringEntity.update(Gdx.graphics.deltaTime)
        poringList.forEach { poring ->
            poring.update(Gdx.graphics.deltaTime)
        }
    }

    private fun input(delta: Float) {
        noviceEntity.input(delta)
    }

    private fun draw() {
        ScreenUtils.clear(Color.DARK_GRAY)
        viewport.apply()
        batch.setProjectionMatrix(viewport.camera.combined)//Esta instrucción se ha de llamar después del viewport.apply()

        drawMap()

        batch.begin()

        poringList.forEach { poring ->
            poring.draw()
        }
        poringEntity.draw()
        noviceEntity.draw()

        batch.end()
    }

    private fun drawMap() {
        camera.update()
        renderer.setView(camera)
        renderer.render()
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
        renderer.dispose()
        map.dispose()
    }

}
