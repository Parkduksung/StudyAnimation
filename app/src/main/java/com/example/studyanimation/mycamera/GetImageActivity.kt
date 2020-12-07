package com.example.studyanimation.mycamera

import android.Manifest
import android.content.Intent
import android.os.Bundle
import android.provider.MediaStore
import androidx.databinding.DataBindingUtil
import com.example.studyanimation.BaseActivity
import com.example.studyanimation.R
import com.example.studyanimation.databinding.ActivityGetimageBinding
import com.gun0912.tedpermission.PermissionListener
import com.gun0912.tedpermission.TedPermission


class GetImageActivity : BaseActivity(R.layout.activity_getimage) {

    private lateinit var binding: ActivityGetimageBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_getimage)
        binding.lifecycleOwner = this
        setContentView(binding.root)

        binding.getImage.setOnClickListener {
            tedPermission()
//            Toast.makeText(this, "나옴", Toast.LENGTH_LONG).show()
        }

    }




    private fun goToAlbum() {
        val intent = Intent(Intent.ACTION_PICK)
        intent.type = MediaStore.Images.Media.CONTENT_TYPE
        startActivityForResult(intent, PICK_FROM_ALBUM)
    }

    private fun tedPermission() {
        val permissionListener: PermissionListener = object : PermissionListener {
            override fun onPermissionGranted() {
                // 권한 요청 성공
                goToAlbum()
            }

            override fun onPermissionDenied(deniedPermissions: ArrayList<String?>?) {
                // 권한 요청 실패
            }
        }
        TedPermission.with(this)
            .setPermissionListener(permissionListener)
//            .setRationaleMessage(resources.getString(R.string.permission_2))
//            .setDeniedMessage(resources.getString(R.string.permission_1))
            .setPermissions(Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.CAMERA)
            .check()
    }


    companion object {
        private const val PICK_FROM_ALBUM = 1
    }
}