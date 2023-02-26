public class Tilengine {

    init {
        System.loadLibrary("TilengineJNI")
    }

    companion object {

        // CreateWindow flags
        public const val CWF_FULLSCREEN = 1 shl 0
        public const val CWF_VSYNC = 1 shl 1
        public const val CWF_S1 = 1 shl 2
        public const val CWF_S2 = 2 shl 2
        public const val CWF_S3 = 3 shl 2
        public const val CWF_S4 = 4 shl 2
        public const val CWF_S5 = 5 shl 2
        public const val CWF_NEAREST = 6 shl 2

        // error codes
        public const val TLN_ERR_OK = 0 // No error
        public const val TLN_ERR_OUT_OF_MEMORY = 1 // Not enough memory
        public const val TLN_ERR_IDX_LAYER = 2 // Layer index out of range
        public const val TLN_ERR_IDX_SPRITE = 3 // Sprite index out of range
        public const val TLN_ERR_IDX_ANIMATION = 4 // Animation index out of range
        public const val TLN_ERR_IDX_PICTURE = 5 // Picture or tile index out of range
        public const val TLN_ERR_REF_TILESET = 6 // Invalid TLN_Tileset reference
        public const val TLN_ERR_REF_TILEMAP = 7 // Invalid TLN_Tilemap reference
        public const val TLN_ERR_REF_SPRITESET = 8 // Invalid TLN_Spriteset reference
        public const val TLN_ERR_REF_PALETTE = 9 // Invalid TLN_Palette reference
        public const val TLN_ERR_REF_SEQUENCE = 10 // Invalid TLN_SequencePack reference
        public const val TLN_ERR_REF_SEQPACK = 11 // Invalid TLN_Sequence reference
        public const val TLN_ERR_REF_BITMAP = 12 // Invalid TLN_Bitmap reference
        public const val TLN_ERR_NULL_POINTER = 13 // Null pointer as argument
        public const val TLN_ERR_FILE_NOT_FOUND = 14 // Resource file not found
        public const val TLN_ERR_WRONG_FORMAT = 15 // Resource file has invalid format
        public const val TLN_ERR_WRONG_SIZE = 16 // A width or height parameter is invalid
        public const val TLN_ERR_UNSUPPORTED = 17 // Unsupported function
        public const val TLN_ERR_REF_LIST = 18 // Invalid TLN_ObjectList reference

        // SetLayerBlendMode & SetSpriteBlendMode modes
        public const val BLEND_NONE = 0
        public const val BLEND_MIX = 1
        public const val BLEND_ADD = 2
        public const val BLEND_SUB = 3
        public const val BLEND_MOD = 4

        // Player
        public const val PLAYER1 = 0
        public const val PLAYER2 = 1
        public const val PLAYER3 = 2
        public const val PLAYER4 = 3

        // GetInput
        public const val INPUT_NONE = 0
        public const val INPUT_UP = 1
        public const val INPUT_DOWN = 2
        public const val INPUT_LEFT = 3
        public const val INPUT_RIGHT = 4
        public const val INPUT_BUTTON1 = 5
        public const val INPUT_BUTTON2 = 6
        public const val INPUT_BUTTON3 = 7
        public const val INPUT_BUTTON4 = 8
        public const val INPUT_BUTTON5 = 9
        public const val INPUT_BUTTON6 = 10
        public const val INPUT_START = 11
        public const val INPUT_QUIT = 12
        public const val INPUT_CRT = 13

        // player request modifier flag
        public const val INPUT_P1 = PLAYER1 shl 5
        public const val INPUT_P2 = PLAYER2 shl 5
        public const val INPUT_P3 = PLAYER3 shl 5
        public const val INPUT_P4 = PLAYER4 shl 5

        // compatibility symbols for pre-1.18 input model
        public const val INPUT_A = INPUT_BUTTON1
        public const val INPUT_B = INPUT_BUTTON2
        public const val INPUT_C = INPUT_BUTTON3
        public const val INPUT_D = INPUT_BUTTON4
        public const val INPUT_E = INPUT_BUTTON5
        public const val INPUT_F = INPUT_BUTTON6

        // overlays for CRT effect
        public const val TLN_OVERLAY_NONE = 0
        public const val TLN_OVERLAY_SHADOWMASK = 1
        public const val TLN_OVERLAY_APERTURE = 2
        public const val TLN_OVERLAY_SCANLINES = 3
        public const val TLN_OVERLAY_CUSTOM = 4
        public const val TLN_MAX_OVERLAY = 5
    }

    // affine transform
    public final class Affine {
        public var angle: Float = 0f // rotation
        public var dx: Float = 0f // translation
        public var dy: Float = 0f // translation
        public var sx: Float = 0f // scale
        public var sy: Float = 0f // scale
    }

    // tile
    public final class Tile {
        public var index: Short = 0 // tile index
        public var flags: Short = 0 // attributes
    }

    // color strip
    public final class ColorStrip {
        public var delay: Int = 0 // time delay between frames
        public var first: Short = 0 // index of first color to cycle
        public var count: Short = 0 // number of colors in the cycle
        public var dir: Byte = 0 // direction: 0=descending, 1=ascending
    }

    // sequence info returned by GetSequenceInfo()
    public final class SequenceInfo {
        public var name: String = "" // sequence name
        public var num_frames: Int = 0 // number of frames
    }

    // Sprite creation info for CreateSpriteset()
    public final class SpriteData {
        public var name: String = "" // entry name
        public var x: Int = 0 // horizontal position
        public var y: Int = 0 // vertical position
        public var w: Int = 0 // width
        public var h: Int = 0 // height
    }

    public final class SpriteInfo {
        public var offset: Int = 0
        public var w: Int = 0
        public var h: Int = 0
    }

    // Tile information returned by GetLayerTile()
    public final class TileInfo {
        public var index: Short = 0
        public var flags: Short = 0
        public var row: Int = 0
        public var col: Int = 0
        public var xoffset: Int = 0
        public var yoffset: Int = 0
        public var color: Byte = 0
        public var type: Byte = 0
        public var empty: Boolean = false
    }

    // Object item info returned by GetObjectInfo()
    public final class ObjectInfo {
        public var id: Short = 0 // unique ID
        public var gid: Short = 0 // graphic ID (tile index)
        public var flags: Short = 0 // attributes (FLAG_FLIPX, FLAG_FLIPY, FLAG_PRIORITY)
        public var x: Int = 0 // horizontal position
        public var y: Int = 0 // vertical position
        public var width: Int = 0 // horizontal size
        public var height: Int = 0 // vertical size
        public var type: Byte = 0 // type property
        public var visible: Boolean = false // visible property
        public var name: String = "" // name property
    }

    // basic management
    public external fun Init(hres: Int, vres: Int, numlayers: Int, numsprites: Int, numanimations: Int): Int
    public external fun Deinit()
    public external fun GetNumObjects(): Int
    public external fun GetUsedMemory(): Int
    public external fun GetVersion(): Int
    public external fun GetNumLayers(): Int
    public external fun GetNumSprites(): Int
    public external fun SetBGColor(r: Int, g: Int, b: Int)
    public external fun SetBGBitmap(bitmap: Int): Boolean
    public external fun SetBGPalette(palette: Int): Boolean
    public external fun SetRasterCallback(obj: Any, methodname: String)
    public external fun SetRenderTarget(data: IntArray, pitch: Int)
    public external fun UpdateFrame(frame: Int)
    public external fun SetLoadPath(path: String)
    public external fun SetWindowTitle(title: String)

    // error handling
    public external fun SetLastError(error: Int)
    public external fun GetLastError(): Int
    public external fun GetErrorString(error: Int): String

    // window management
    public external fun CreateWindow(overlay: String, flags: Int): Boolean
    public external fun CreateWindowThread(overlay: String, flags: Int): Boolean
    public external fun ProcessWindow(): Boolean
    public external fun IsWindowActive(): Boolean
    public external fun GetInput(id: Int): Boolean
    public external fun DrawFrame(frame: Int)
    public external fun WaitRedraw()
    public external fun DeleteWindow()
    public external fun EnableBlur(mode: Boolean)
    public external fun GetTicks(): Int
    public external fun Delay(time: Int)
    public external fun BeginWindowFrame()
    public external fun EndWindowFrame()
    public external fun GetWindowHeight(): Int
    public external fun GetWindowWidth(): Int

    // spritesets management
    public external fun LoadSpriteset(name: String): Int
    public external fun CloneSpriteset(src: Int): Int
    public external fun GetSpriteInfo(spriteset: Int, entry: Int, info: SpriteInfo): Boolean
    public external fun GetSpritesetPalette(spriteset: Int): Int
    public external fun DeleteSpriteset(spriteset: Int): Boolean

    // tilesets management
    public external fun LoadTileset(filename: String): Int
    public external fun CloneTileset(src: Int): Int
    public external fun SetTilesetPixels(tileset: Int, entry: Int, srcdata: ByteArray, srcpitch: Int): Boolean
    public external fun CopyTile(tileset: Int, src: Int, dst: Int): Boolean
    public external fun GetTileWidth(tileset: Int): Int
    public external fun GetTileHeight(tileset: Int): Int
    public external fun GetTilesetPalette(tileset: Int): Int
    public external fun DeleteTileset(tileset: Int): Boolean
    public external fun GetTilesetNumTiles(tileset: Int): Int

    // tilemaps management
    public external fun LoadTilemap(filename: String, layername: String): Int
    public external fun CloneTilemap(src: Int): Int
    public external fun GetTilemapRows(tilemap: Int): Int
    public external fun GetTilemapCols(tilemap: Int): Int
    public external fun GetTilemapTile(tilemap: Int, row: Int, col: Int, tile: Tile): Boolean
    public external fun SetTilemapTile(tilemap: Int, row: Int, col: Int, tile: Tile): Boolean
    public external fun CopyTiles(src: Int, srcrow: Int, srccol: Int, rows: Int, cols: Int, dst: Int, dstrow: Int, dstcol: Int)
    public external fun DeleteTilemap(tilemap: Int): Boolean

    // color tables management
    public external fun CreatePalette(entries: Int): Int
    public external fun LoadPalette(filename: String): Int
    public external fun ClonePalette(src: Int): Int
    public external fun DeletePalette(palette: Int)
    public external fun SetPaletteColor(palette: Int, color: Int, r: Byte, g: Byte, b: Byte)
    public external fun MixPalettes(src1: Int, src2: Int, dst: Int, f: Byte)

    // bitmaps
    public external fun CreateBitmap(width: Int, height: Int, bpp: Int): Int
    public external fun LoadBitmap(filename: String): Int
    public external fun CloneBitmap(src: Int): Int
    public external fun GetBitmapWidth(bitmap: Int): Int
    public external fun GetBitmapHeight(bitmap: Int): Int
    public external fun GetBitmapDepth(bitmap: Int): Int
    public external fun GetBitmapPitch(bitmap: Int): Int
    public external fun GetBitmapPalette(bitmap: Int): Int
    public external fun SetBitmapPalette(bitmap: Int, palette: Int): Boolean
    public external fun DeleteBitmap(bitmap: Int): Boolean

    // layer management
    public external fun SetLayer(nlayer: Int, tileset: Int, tilemap: Int): Boolean
    public external fun SetLayerPalette(nlayer: Int, palette: Int): Boolean
    public external fun SetLayerPosition(nlayer: Int, hstart: Int, vstart: Int): Boolean
    public external fun SetLayerScaling(nlayer: Int, xfactor: Float, yfactor: Float): Boolean
    public external fun SetLayerAffineTransform(nlayer: Int, affine: Affine): Boolean
    public external fun SetLayerTransform(layer: Int, angle: Float, dx: Float, dy: Float, sx: Float, sy: Float): Boolean
    public external fun SetLayerBlendMode(nlayer: Int, mode: Int, factor: Byte): Boolean
    public external fun SetLayerColumnOffset(nlayer: Int, offset: IntArray): Boolean
    public external fun SetLayerClip(nlayer: Int, x1: Int, y1: Int, x2: Int, y2: Int): Boolean
    public external fun DisableLayerClip(nlayer: Int): Boolean
    public external fun ResetLayerMode(nlayer: Int): Boolean
    public external fun DisableLayer(nlayer: Int): Boolean
    public external fun GetLayerPalette(nlayer: Int): Int
    public external fun GetLayerTile(nlayer: Int, x: Int, y: Int, info: TileInfo): Boolean
    public external fun SetLayerPriority(nlayer: Int, enable: Boolean): Boolean
    public external fun SetLayerParent(nlayer: Int, parent: Int): Boolean
    public external fun DisableLayerParent(nlayer: Int): Boolean
    public external fun SetLayerTilemap(nlayer: Int, tilemap: Int): Boolean
    public external fun SetLayerMosaic(nlayer: Int, width: Int, height: Int): Boolean
    public external fun DisableLayerMosaic(nlayer: Int): Boolean
    public external fun GetLayerWidth(nlayer: Int): Int
    public external fun GetLayerHeight(nlayer: Int): Int

    // sprites management
    public external fun ConfigSprite(nsprite: Int, spriteset: Int, flags: Short): Boolean
    public external fun SetSpriteSet(nsprite: Int, spriteset: Int): Boolean
    public external fun SetSpriteFlags(nsprite: Int, flags: Short): Boolean
    public external fun SetSpritePosition(nsprite: Int, x: Int, y: Int): Boolean
    public external fun SetSpritePicture(nsprite: Int, entry: Int): Boolean
    public external fun SetSpritePalette(nsprite: Int, palette: Int): Boolean
    public external fun SetSpriteBlendMode(nsprite: Int, mode: Int, factor: Byte): Boolean
    public external fun SetSpriteScaling(nsprite: Int, sx: Float, sy: Float): Boolean
    public external fun ResetSpriteScaling(nsprite: Int): Boolean
    public external fun GetSpritePicture(nsprite: Int): Int
    public external fun GetAvailableSprite(): Int
    public external fun EnableSpriteCollision(nsprite: Int, enable: Boolean): Boolean
    public external fun GetSpriteCollision(nsprite: Int): Boolean
    public external fun DisableSprite(nsprite: Int): Boolean
    public external fun GetSpritePalette(nsprite: Int): Int
    public external fun EnableSpriteFlag(nsprite: Int, flag: Short, enable: Boolean): Boolean
    public external fun SetFirstSprite(nsprite: Int): Boolean
    public external fun SetNextSprite(nsprite: Int, next: Int): Boolean
    public external fun EnableSpriteMasking(nsprite: Int, enable: Boolean): Boolean
    public external fun SetSpritesMaskingRegion(top_line: Int, bottom_line: Int)
    public external fun SetSpriteAnimation(nsprite: Int, sequence: Int, loop: Int): Boolean
    public external fun DisableSpriteAnimation(nsprite: Int): Boolean

    // sequences management
    public external fun CloneSequence(src: Int): Int
    public external fun DeleteSequence(sequence: Int): Boolean

    // sequence pack management
    public external fun CreateSequencePack(): Int
    public external fun LoadSequencePack(filename: String): Int
    public external fun FindSequence(sp: Int, name: String): Int
    public external fun AddSequenceToPack(sp: Int, sequence: Int): Boolean
    public external fun DeleteSequencePack(sp: Int): Boolean

    // animation engin
    public external fun SetPaletteAnimation(index: Int, palette: Int, sequence: Int, blend: Boolean): Boolean
    public external fun SetPaletteAnimationSource(index: Int, palette: Int): Boolean
    public external fun GetAnimationState(index: Int): Boolean
    public external fun SetAnimationDelay(index: Int, frame: Int, delay: Int): Boolean
    public external fun GetAvailableAnimation(): Int
    public external fun DisablePaletteAnimation(index: Int): Boolean
}