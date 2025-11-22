package xyz.malefic.staticsite.pages

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import com.varabyte.kobweb.compose.css.*
import com.varabyte.kobweb.compose.foundation.layout.Box
import com.varabyte.kobweb.compose.foundation.layout.Column
import com.varabyte.kobweb.compose.ui.Alignment
import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.compose.ui.modifiers.*
import com.varabyte.kobweb.compose.ui.styleModifier
import com.varabyte.kobweb.core.Page
import com.varabyte.kobweb.silk.components.graphics.Image
import kotlinx.browser.document
import kotlinx.browser.window
import org.jetbrains.compose.web.css.*
import org.jetbrains.compose.web.dom.*
import org.w3c.dom.CanvasRenderingContext2D
import org.w3c.dom.HTMLCanvasElement
import kotlin.math.floor
import kotlin.random.Random

@Page
@Composable
fun HomePage() {
    LaunchedEffect(Unit) {
        initMatrixRain()
    }

    Canvas(
        attrs = {
            id("matrix-canvas")
            style {
                position(Position.Fixed)
                top(0.px)
                left(0.px)
                width(100.vw)
                height(100.vh)
                property("z-index", "1")
            }
        },
    )

    Box(
        modifier =
            Modifier
                .fillMaxSize()
                .position(Position.Relative)
                .zIndex(10)
                .padding(2.cssRem)
                .overflow { y(Overflow.Auto) }
                .maxHeight(100.vh),
        contentAlignment = Alignment.TopCenter,
    ) {
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Image(
                src = "https://avatars.githubusercontent.com/u/92651174?v=4",
                modifier =
                    Modifier
                        .size(150.px)
                        .borderRadius(50.percent)
                        .border(3.px, LineStyle.Solid, rgb(0, 255, 0))
                        .margin(topBottom = 1.cssRem)
                        .styleModifier {
                            property("box-shadow", "0 0 20px #00ff00, 0 0 40px #00ff00")
                            property("animation", "glow 2s ease-in-out infinite")
                        },
            )

            H1(
                attrs = {
                    style {
                        fontFamily("Courier New", "monospace")
                        fontSize(2.5.cssRem)
                        color(rgb(0, 255, 0))
                        textAlign("center")
                        margin(0.5.cssRem, 0.cssRem)
                        property("text-shadow", "0 0 10px #00ff00")
                        property("animation", "flicker 3s ease-in-out infinite")
                    }
                },
            ) {
                Text("Om Gupta")
            }

            Box(
                modifier =
                    Modifier
                        .maxWidth(800.px)
                        .width(90.percent)
                        .margin(1.cssRem)
                        .padding(1.5.cssRem)
                        .borderRadius(10.px)
                        .border(2.px, LineStyle.Solid, rgb(0, 255, 0))
                        .styleModifier {
                            property("background", "rgba(0, 20, 0, 0.95)")
                            property("box-shadow", "0 0 30px rgba(0, 255, 0, 0.5)")
                            property("backdrop-filter", "blur(2px)")
                        },
            ) {
                Column(modifier = Modifier.fillMaxWidth()) {
                    Div(
                        attrs = {
                            style {
                                fontFamily("Courier New", "monospace")
                                fontSize(1.5.cssRem)
                                color(rgb(0, 255, 0))
                                textAlign("center")
                                marginBottom(1.cssRem)
                                paddingBottom(0.75.cssRem)
                                borderBottom(2.px, LineStyle.Solid, rgb(0, 255, 0))
                                property("text-shadow", "0 0 10px #00ff00")
                            }
                        },
                    ) {
                        Text("MIT LICENSE")
                    }

                    Pre(
                        attrs = {
                            style {
                                fontFamily("Courier New", "monospace")
                                color(rgb(0, 255, 0))
                                fontSize(0.85.cssRem)
                                lineHeight("1.6")
                                margin(0.px)
                                property("white-space", "pre-wrap")
                                property("text-shadow", "0 0 5px #00ff00")
                            }
                        },
                    ) {
                        Text(
                            """
MIT License

Copyright (c) 2025 Om Gupta

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.
                            """.trimIndent(),
                        )
                    }
                }
            }
        }
    }
}

private fun initMatrixRain() {
    val canvas = document.getElementById("matrix-canvas") as? HTMLCanvasElement ?: return
    val ctx = canvas.getContext("2d") as? CanvasRenderingContext2D ?: return

    canvas.width = window.innerWidth
    canvas.height = window.innerHeight

    val katakana = "アイウエオカキクケコサシスセソタチツテトナニヌネノハヒフヘホマミムメモヤユヨラリルレロワヲン"
    val latin = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"
    val nums = "0123456789"
    val alphabet = katakana + latin + nums

    val fontSize = 16
    val columns = floor(canvas.width.toDouble() / fontSize).toInt()
    val drops = IntArray(columns) { 1 }

    fun draw() {
        ctx.fillStyle = "rgba(0, 0, 0, 0.05)"
        ctx.fillRect(0.0, 0.0, canvas.width.toDouble(), canvas.height.toDouble())

        ctx.fillStyle = "#00ff00"
        ctx.font = "${fontSize}px monospace"

        for (i in drops.indices) {
            val text = alphabet[Random.nextInt(alphabet.length)].toString()
            ctx.fillText(text, (i * fontSize).toDouble(), (drops[i] * fontSize).toDouble())

            if (drops[i] * fontSize > canvas.height && Random.nextDouble() > 0.975) {
                drops[i] = 0
            }
            drops[i]++
        }
    }

    window.setInterval({ draw() }, 33)
}
