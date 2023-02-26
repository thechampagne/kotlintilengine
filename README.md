# kotlintilengine

[![](https://img.shields.io/github/v/tag/thechampagne/kotlintilengine?label=version)](https://github.com/thechampagne/kotlintilengine/releases/latest) [![](https://img.shields.io/github/license/thechampagne/kotlintilengine)](https://github.com/thechampagne/kotlintilengine/blob/main/LICENSE)

Kotlin binding for **Tilengine** a 2D graphics engine with raster effects for retro/classic style game development.

### Example
```kt
fun main() {
      val tile = Tilengine()
      tile.Init(400, 240, 1, 0, 20)
      tile.SetLoadPath("assets")
  
      val foreground = tile.LoadTilemap("sonic_md_fg1.tmx", "Layer 1")
      tile.SetLayer(0, 0, foreground)
  
      var frame = 0
      tile.CreateWindow("", 0)
      while (tile.ProcessWindow()) {
      	    tile.DrawFrame(frame)
	    frame += 1
      }
      tile.Deinit()
}
```

### References
 - [Tilengine](https://github.com/megamarc/Tilengine)
 - [JTilengine](https://github.com/megamarc/JTilengine)

### License

This repo is released under the [MPL-2.0](https://github.com/thechampagne/kotlintilengine/blob/main/LICENSE).
