package com.example.studyanimation.orientation

import android.app.Application
import android.content.pm.ActivityInfo
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class OrientationViewModel(private val context: Application) : AndroidViewModel(context) {

    private val _viewStateLiveData = MutableLiveData<ViewState>(ViewState.RotationLock)
    val viewStateLiveData: LiveData<ViewState> = _viewStateLiveData


    private var orientation = context.resources.configuration.orientation


    private val coroutineScope = CoroutineScope(Dispatchers.Main)
    fun changeOrientation() {

        coroutineScope.launch(Dispatchers.IO) {
            when (orientation) {
                ActivityInfo.SCREEN_ORIENTATION_PORTRAIT -> {
                    withContext(Dispatchers.Main) {
                        onViewStateChanged(ViewState.RotationLand)
                    }
                    orientation = ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE
                }

                ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE -> {
                    withContext(Dispatchers.Main) {
                        onViewStateChanged(ViewState.RotationPort)
                    }
                    orientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
                }

                else -> {
                    onViewStateChanged(ViewState.RotationLock)
                }
            }


        }

    }

    private suspend fun onViewStateChanged(viewState: ViewState) = withContext(Dispatchers.Main) {
        _viewStateLiveData.value = viewState
        _viewStateLiveData.value = null
    }

    sealed class ViewState {
        object RotationLand : ViewState()
        object RotationPort : ViewState()
        object RotationLock : ViewState()
    }
}