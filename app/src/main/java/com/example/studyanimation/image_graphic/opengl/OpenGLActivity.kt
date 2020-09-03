package com.example.studyanimation.image_graphic.opengl

import android.opengl.GLES20
import android.opengl.GLSurfaceView
import android.os.Bundle
import com.example.studyanimation.BaseActivity
import com.example.studyanimation.R
import javax.microedition.khronos.egl.EGLConfig
import javax.microedition.khronos.opengles.GL10

class OpenGLActivity : BaseActivity(R.layout.activity_opengl){

    //Android에서는 Open Graphic Library(OpenGL®), 특히 OpenGL ES API가 포함된 고성능 2D 및 3D 그래픽을 지원합니다.
    // OpenGL은 3D 그래픽 처리 하드웨어의 표준 소프트웨어 인터페이스를 지정하는 크로스 플랫폼 그래픽 API입니다.
    // OpenGL ES는 삽입 기기용 OpenGL 사양의 특성입니다.

    // Android에서는 다음과 같은 여러 버전의 OpenGL ES API를 지원합니다.
    //OpenGL ES 1.0 및 1.1 - 이 API 사양은 Android 1.0 이상에서 지원됩니다.
    //OpenGL ES 2.0 - 이 API 사양은 Android 2.2(API 레벨 8) 이상에서 지원됩니다.
    //OpenGL ES 3.0 - 이 API 사양은 Android 4.3(API 레벨 18) 이상에서 지원됩니다.
    //OpenGL ES 3.1 - 이 API 사양은 Android 5.0(API 레벨 21) 이상에서 지원됩니다.


    //Android 프레임워크에는 OpenGL ES API로 그래픽을 만들고 조작할 수 있는 두 가지 기본 클래스,
    // GLSurfaceView 및 GLSurfaceView.Renderer가 있습니다.

    //GLSurfaceView
    //이 클래스는 OpenGL API 호출을 사용하여 객체를 그리고 조작할 수 있는 View이며 SurfaceView와 기능이 비슷합니다.
    //GLSurfaceView의 인스턴스를 만들고 여기에 Renderer를 추가하여 이 클래스를 사용할 수 있습니다.
    //그러나 터치 스크린 이벤트를 캡처하려면 OpenGL 학습 과정 터치 이벤트에 응답에 표시된 대로 GLSurfaceView 클래스를 확장하여 터치 리스너를 구현해야 합니다.

    //GLSurfaceView.Renderer
    //이 인터페이스를 통해서는 GLSurfaceView에 그래픽을 그리는 데 필요한 메서드를 정의합니다.
    //개별 클래스로 구현된 이 인터페이스를 제공하고 GLSurfaceView.setRenderer()를 사용하여 GLSurfaceView 인스턴스에 연결해야 합니다.
    //onSurfaceCreated(): GLSurfaceView를 만들 때 시스템에서 이 메서드를 한 번 호출합니다.
    //이 메서드를 사용하여 OpenGL 환경 매개변수 설정 또는 OpenGL 그래픽 객체 초기화와 같이 한 번만 실행해야 하는 작업을 완료합니다.
    //onDrawFrame(): 시스템에서 GLSurfaceView를 다시 그릴 때마다 이 메서드를 호출합니다.
    //이 메서드를 그래픽 객체 그리기(및 다시 그리기)의 기본 실행 지점으로 사용합니다.
    //onSurfaceChanged(): GLSurfaceView의 크기 변경 또는 기기 화면의 방향 변경을 비롯하여 GLSurfaceView 도형이 변경될 때 시스템에서 이 메서드를 호출합니다.
    //예를 들어 기기가 세로 모드에서 가로 모드로 방향을 변경하면 시스템에서 이 메서드를 호출합니다.
    //이 메서드를 사용하여 GLSurfaceView 컨테이너의 변경사항에 맞게 대응합니다


    //Android 기기에서 그래픽을 표시할 때 기본 문제 중 하나는 화면의 크기와 모양이 다양할 수 있다는 점입니다.
    // OpenGL에서는 정사각형의 균일 좌표계를 가정하며, 대체로 정사각형이 아닌 화면에서 완전히 정사각형인 것처럼 좌표를 그립니다.


    private lateinit var gLView : GLSurfaceView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        gLView = MyGLSurfaceView(this)
        setContentView(gLView)

    }
}