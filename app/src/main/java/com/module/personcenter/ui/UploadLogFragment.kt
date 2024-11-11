package com.module.personcenter.ui

import android.content.Intent
import android.content.pm.PackageManager.NameNotFoundException
import android.net.Uri
import android.os.Build
import android.view.LayoutInflater
import android.view.View
import android.widget.CheckBox
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.FileProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.blankj.utilcode.util.TimeUtils
import com.blankj.utilcode.util.ZipUtils
import com.module.personcenter.R
import com.module.personcenter.adapter.CameraInfo
import com.module.personcenter.adapter.CameraInfoListAdapter
import com.module.personcenter.adapter.checkItemIsNotAllSelected
import com.module.personcenter.databinding.UploadLogFragmentBinding
import java.io.File


/**
 * Copyright (c) 2022 Raysharp.cn. All rights reserved
 * 上传奔溃记录页面
 * @author longyanghe
 * @date 2022-01-22
 */
class UploadLogFragment : BaseViewBindingFragment<UploadLogFragmentBinding>() {

    companion object {
        const val TAG = "UploadLogFragment"
        const val DATA_FORMAT = "yyyyMMdd_HHmmss"
        const val ZIP_FILE_SUFFIX = ".zip"
        const val SEND_FILE_TYPE = "application/zip"
    }

    override fun inflateViewBinding(inflater: LayoutInflater): UploadLogFragmentBinding {
        return UploadLogFragmentBinding.inflate(inflater)
    }

    override fun initView() {
        binding. appBar.apply {
            tvTitle.setText(R.string.person_center_feedback)
            ivStart.setOnClickListener {
                findNavController().navigateUp()
            }
        }


//        DeviceRepository.getAllDevices().observeOn(AndroidSchedulers.mainThread()).subscribe {
        val cameraInfoList = arrayListOf<CameraInfo>()
        (0..25).forEach {
            cameraInfoList.add(CameraInfo(it.toString(),false))
        }
        binding.selectitemtable.loadData(cameraInfoList)
//            it.forEach { device ->
//                device.devName?.run {
//                    cameraInfoList.add(CameraInfo(this, false))
//                }
//            }
//            binding.devicesRecyclerView.apply {
//                layoutManager = LinearLayoutManager(context)
//                addItemDecoration(DividerItemDecoration(context, LinearLayoutManager.VERTICAL))
//
//                var cameraInfoListAdapter =
//                    CameraInfoListAdapter(R.layout.upload_log_cameras_list, cameraInfoList)
//                cameraInfoListAdapter.addChildClickViewIds(R.id.checkbox_camera_name)
//                cameraInfoListAdapter.setOnItemChildClickListener{
//                        adapter,view,pos->
//                        val isChecked = (view as CheckBox).isChecked
//                        cameraInfoList[pos].isSelect = isChecked
//                        binding.deviceLogs.isChecked = !checkItemIsNotAllSelected(cameraInfoList)
//                        adapter.notifyItemChanged(0)
//                }
//                adapter = cameraInfoListAdapter
//            }
//        }

        /**
         * 点击上传app记录按键
         */
//        binding.tabletUploadLog.appLogAct = {
//            shareLog()
//        }

        /**
         * 点击上传设备记录按键
         */
//        binding.tabletUploadLog.deviceLogAct = {
//            RSLog.d("UploadLogFragment", "setAuthorize ")
//        }
    }

    private fun shareLog() {
        context?.let { context ->
            val logPath =
                context.filesDir.absolutePath + File.separator + "log" + File.separator

            // 获取目录下所有日志文件
            val listFiles = File(logPath).listFiles()
            if (listFiles != null) {
            } else {
                return@let
            }

            // 构建日志文件名称(AppName、VersionName、VersionCode、年月日、时分秒)
            val timeStr = TimeUtils.getNowString(TimeUtils.getSafeDateFormat(DATA_FORMAT))
            val logFileName = try {
                val packageInfo = context.packageManager.getPackageInfo(context.packageName, 0)
                val versionName = packageInfo.versionName
                val versionCode = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.P) {
                    packageInfo.longVersionCode
                } else {
                    packageInfo.versionCode
                }
                "android_getString(R.string.app_name)}_${versionName}_${versionCode}_${timeStr}$ZIP_FILE_SUFFIX"
            } catch (e: NameNotFoundException) {
                "android_getString(R.string.app_name)}_$}_${timeStr}$ZIP_FILE_SUFFIX"
            }

//            val zipFile = File("${FileUtils.getUserShareFilePath(BaseApplication.getInstance().userId)}${logFileName}")
            val zipFile = File("")
            if (!zipFile.parentFile.exists()) {
                zipFile.parentFile.mkdir()
            }
            if (!zipFile.exists()) {
                zipFile.createNewFile()
            }

            val temFileList = mutableListOf<File>()
            temFileList.addAll(listFiles)
            val zipSuccess = ZipUtils.zipFiles(temFileList, zipFile)

            if (zipSuccess) {
                // 获取文件的uri
                val uri = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                    FileProvider.getUriForFile(context, "${context.packageName}.fileprovider", zipFile)
                } else {
                    Uri.fromFile(zipFile)
                }

                // 调用原生分享
                val shareIntent = Intent().apply {
                    action = Intent.ACTION_SEND
                    type = SEND_FILE_TYPE
                    putExtra(Intent.EXTRA_STREAM, uri)
                    flags = Intent.FLAG_GRANT_READ_URI_PERMISSION or Intent.FLAG_GRANT_WRITE_URI_PERMISSION or Intent.FLAG_ACTIVITY_NEW_TASK
                }
                val chooser = Intent.createChooser(shareIntent, context.getString(R.string.person_center_share_devices))
                context.startActivity(chooser)
            } else {
            }
        }
    }

    private val openZipDocument =
        registerForActivityResult(ActivityResultContracts.OpenDocumentTree()) {}
}