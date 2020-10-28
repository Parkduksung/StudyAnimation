package com.example.studyanimation.opengl.lecture

import android.graphics.Bitmap
import android.opengl.GLES20
import android.opengl.Matrix
import com.example.studyanimation.opengl.lecture.OpenGLUtil.createProgram
import java.nio.ByteBuffer
import java.nio.ByteOrder
import java.nio.FloatBuffer
import java.nio.ShortBuffer

class Tex(bitmap: Bitmap) {

    private val mVertexBuffer: FloatBuffer
    private val mDrawListBuffer: ShortBuffer
    protected var mUvBuffer: FloatBuffer
    private val mMtrxView = FloatArray(16)
    private val mHandleBitmap: Int
    private var buffer : ByteBuffer ?= null

    private

    //vertex 를 위한 사각형 출력하기 위해 이렇게 선언.
    var mSquareCoords = floatArrayOf(
        -0.5f, 0.5f, 0.0f,  // top left
        -0.5f, -0.5f, 0.0f,  // bottom left
        0.5f, -0.5f, 0.0f,  // bottom right
        0.5f, 0.5f, 0.0f
    )

    // top right
    private val mDrawOrder = shortArrayOf(0, 1, 2, 0, 2, 3)

    // order to draw vertices
    private val mProgram: Int
    private var mPositionHandle = 0
    private var mTextureHandel = 0
    private var mMatrixHandle = 0

    fun draw() {

        GLES20.glEnable(GLES20.GL_BLEND)
        GLES20.glBlendFunc(GLES20.GL_ONE, GLES20.GL_ONE_MINUS_SRC_ALPHA)


        mPositionHandle = GLES20.glGetAttribLocation(mProgram, "vPosition")
        mTextureHandel = GLES20.glGetAttribLocation(mProgram, "a_texCoord")
        mMatrixHandle = GLES20.glGetUniformLocation(mProgram, "uMVPMatrix")


        //생성한 프로그램 사용.
        GLES20.glUseProgram(mProgram)

        GLES20.glEnableVertexAttribArray(mPositionHandle)
        GLES20.glVertexAttribPointer(mPositionHandle, 3, GLES20.GL_FLOAT, false, 0, mVertexBuffer)

        GLES20.glEnableVertexAttribArray(mTextureHandel)
        GLES20.glVertexAttribPointer(mTextureHandel, 2, GLES20.GL_FLOAT, false, 0, mUvBuffer)

        GLES20.glBindTexture(GLES20.GL_TEXTURE_2D, mHandleBitmap)
//        GLES20.glTexSubImage2D(
//            GLES20.GL_TEXTURE_2D,
//            0,
//            0,
//            0,
//            500,
//            500,
//            GLES20.GL_RGBA,
//            GLES20.GL_UNSIGNED_BYTE,
//            buffer
//        )

        Matrix.setIdentityM(mMtrxView, 0)
        GLES20.glUniformMatrix4fv(mMatrixHandle, 1, false, mMtrxView, 0)
//        GLES20.glDrawElements(GLES20.GL_TRIANGLES, 6, GLES20.GL_UNSIGNED_SHORT, mUvBuffer)


        GLES20.glDrawElements(
            GLES20.GL_TRIANGLES, mDrawOrder.size,
            GLES20.GL_UNSIGNED_SHORT, mDrawListBuffer
        )
        GLES20.glDisableVertexAttribArray(mPositionHandle)
        GLES20.glDisableVertexAttribArray(mTextureHandel)
    }

    private fun getImageHandle(bitmap: Bitmap): Int {

        //이미지를 파라메터로 받음.

        val textureNames = IntArray(1)

        //제러네이트 텍스쳐 생성
        GLES20.glGenTextures(1, textureNames, 0)
        //엑티브 텍스쳐 생성  , 뒤에 0인 이유가 이미지가 1개이기 때문에 만약 2개면 0,1 이런식으로 붙이면 된다.
//        GLES20.glActiveTexture(GLES20.GL_TEXTURE0)

        // 텍스쳐를 생성한걸 바인드 시킨다.
        GLES20.glBindTexture(GLES20.GL_TEXTURE_2D, textureNames[0])

        // 텍스쳐 파라메터인데 작으면 크게하고 크면 작게하는 그런 형태의 명령문.
        GLES20.glTexParameterf(
            GLES20.GL_TEXTURE_2D,
            GLES20.GL_TEXTURE_MIN_FILTER,
            GLES20.GL_NEAREST.toFloat()
        )
        GLES20.glTexParameterf(
            GLES20.GL_TEXTURE_2D,
            GLES20.GL_TEXTURE_MAG_FILTER,
            GLES20.GL_LINEAR.toFloat()
        )
        GLES20.glTexParameteri(
            GLES20.GL_TEXTURE_2D,
            GLES20.GL_TEXTURE_WRAP_S,
            GLES20.GL_CLAMP_TO_EDGE
        )
        GLES20.glTexParameteri(
            GLES20.GL_TEXTURE_2D,
            GLES20.GL_TEXTURE_WRAP_T,
            GLES20.GL_CLAMP_TO_EDGE
        )



        // Load the data from the buffer into the texture handle.
//        GLES20.glTexImage2D(
//            GLES20.GL_TEXTURE_2D,  /*level*/0, format,
//            width, height,  /*border*/0, format, GLES20.GL_UNSIGNED_BYTE, data
//        )

//        GLES20.glTexImage2D(GLES20.GL_TEXTURE_2D)
        GLES20.glTexImage2D(
            GLES20.GL_TEXTURE_2D,
            0,
            GLES20.GL_RGBA,
            500,
            500,
            0,
            GLES20.GL_RGBA,
            GLES20.GL_UNSIGNED_BYTE,
            null
        )
        //이미지를 비트맵의 이미지를 받아서 최종적으로 int 형태인 이미지 핸들을 반환함.
//        GLUtils.texImage2D(GLES20.GL_TEXTURE_2D, 0, bitmap, 0)
        return textureNames[0]
    }


    init {
        val bb = ByteBuffer.allocateDirect(
            mSquareCoords.size * 4
        )
        bb.order(ByteOrder.nativeOrder())
        mVertexBuffer = bb.asFloatBuffer()
        mVertexBuffer.put(mSquareCoords)
        mVertexBuffer.position(0)
        val dlb = ByteBuffer.allocateDirect(
            mDrawOrder.size * 4
        )
        dlb.order(ByteOrder.nativeOrder())
        mDrawListBuffer = dlb.asShortBuffer()
        mDrawListBuffer.put(mDrawOrder)
        mDrawListBuffer.position(0)

        //좌표계를 설정.
        mUvs = floatArrayOf(
            0.0f, 0.0f,
            0.0f, 1.0f,
            1.0f, 1.0f,
            1.0f, 0.0f
        )

        //실수형이기 때문에 버퍼를 4만큼 잡아놓음.
        val bbUvs = ByteBuffer.allocateDirect(mUvs.size * 4)
        bbUvs.order(ByteOrder.nativeOrder())
        mUvBuffer = bbUvs.asFloatBuffer()
        mUvBuffer.put(mUvs)
        mUvBuffer.position(0)

        //프로그램을 이용하여 쉐이더 프로그를 컴파일하고 링크한다음.
        mProgram = createProgram(vs_Image, fs_Image)

        buffer = ByteBuffer.allocate(bitmap.byteCount)
        bitmap.copyPixelsToBuffer(buffer)
        mHandleBitmap = getImageHandle(bitmap)
    }


    companion object {
        protected lateinit var mUvs: FloatArray

        // 쉐이더 이미지
        const val vs_Image = "uniform mat4 uMVPMatrix;" +
                "attribute vec4 vPosition;" +
                "attribute vec2 a_texCoord;" +
                "varying vec2 v_texCoord;" +
                "void main() {" +
                "  gl_Position = uMVPMatrix * vPosition;" +
                "  v_texCoord = a_texCoord;" + "}"
        const val fs_Image = "precision mediump float;" +
                "varying vec2 v_texCoord;" +
                "uniform sampler2D s_texture;" +
                "void main() {" +
                "  gl_FragColor = texture2D( s_texture, v_texCoord );" +
                "}"
    }

}