package com.example.appstore.activity

import android.Manifest
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Recycler
import com.example.appstore.R
import com.example.appstore.data.AppDetailsListing
import com.example.appstore.utils.DownloadController
import com.example.appstore.utils.Utils
import com.example.appstore.utils.checkSelfPermissionCompat
import com.example.appstore.utils.requestPermissionsCompat
import com.example.appstore.utils.shouldShowRequestPermissionRationaleCompat
import com.google.android.material.snackbar.Snackbar

class AppListActivity : AppCompatActivity() {

    var appListing = ArrayList<AppDetailsListing>()
    lateinit var recyclerView: RecyclerView
    lateinit var mAdapter: AppListAdapter
    lateinit var downloadController:DownloadController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_app_list)
        initViews()
    }

    private fun initViews() {
        recyclerView = findViewById(R.id.recyclerView)
        val app = AppDetailsListing()
        app.appName = "EzePosDemo"
        app.versionName = "3.9"
        app.packageName = "com.ezetap.epos.demo"
        app.launcherPackageName = "com.ezetap.epos.demo.splash.SplashActivity"
        app.appUrl =
            "https://demo.ezetap.com/ezetap/apps/demo/EZETAP/ANDROID/EZEPOS/73/ezetap_android_epos.apk"
        appListing.add(app)
        initRecyclerView()
    }

    private fun initRecyclerView() {
        mAdapter = AppListAdapter(appListing, this, object : AppListAdapter.ClickListener {
            override fun onInstall(position: Int) {
                 downloadController =
                    DownloadController(this@AppListActivity, appListing[position].appUrl!!)
                // check storage permission granted if yes then start downloading file
                checkStoragePermission()
            }

            override fun onOpen(position: Int) {
                Utils.openInstalledApplication(
                    this@AppListActivity,
                    appListing[position].packageName!!,
                    appListing[position].launcherPackageName!!
                )
            }
        })

        recyclerView.adapter = mAdapter
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == SecondActivity.PERMISSION_REQUEST_STORAGE) {
            // Request for camera permission.
            if (grantResults.size == 1 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                // start downloading
                downloadController.enqueueDownload()
            } else {
                // Permission request was denied.
                Toast.makeText(this,R.string.storage_permission_denied,Toast.LENGTH_SHORT).show()
            }
        }
    }
    private fun checkStoragePermission() {
        // Check if the storage permission has been granted
        if (checkSelfPermissionCompat(Manifest.permission.WRITE_EXTERNAL_STORAGE) ==
            PackageManager.PERMISSION_GRANTED
        ) {
            // start downloading
            downloadController.enqueueDownload()
        } else {
            // Permission is missing and must be requested.
            requestStoragePermission()
        }
    }

    private fun requestStoragePermission() {
        if (shouldShowRequestPermissionRationaleCompat(Manifest.permission.WRITE_EXTERNAL_STORAGE)) {

        } else {
            requestPermissionsCompat(
                arrayOf(Manifest.permission.WRITE_EXTERNAL_STORAGE),
                SecondActivity.PERMISSION_REQUEST_STORAGE
            )
        }
    }
}