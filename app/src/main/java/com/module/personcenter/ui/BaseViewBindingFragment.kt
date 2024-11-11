package com.module.personcenter.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.IdRes
import androidx.annotation.NonNull
import androidx.annotation.Nullable
import androidx.fragment.app.Fragment
import androidx.lifecycle.LiveData
import androidx.navigation.NavController
import androidx.navigation.NavDirections
import androidx.navigation.fragment.findNavController
import androidx.viewbinding.ViewBinding

/**
 * Copyright (c) 2022 Raysharp.cn. All rights reserved
 * 使用ViewBinding 相比 DataBinding更轻量，编译也更快。
 * 适合数据绑定少的界面，xml布局也无需写<layout></layout>
 * 需要在app gradle添加对viewBinding的支持
 * @see[ViewBinding](https://developer.android.com/topic/libraries/view-binding)
 * BaseViewBindingFragment
 * @author longyanghe
 * @date 2022-01-11
 */
abstract class BaseViewBindingFragment<T: ViewBinding>: Fragment() {

    private var mArgs: ResultArgs? = null
    private var _binding: T? = null
    //断言不为空，避免大量?.的写法
    protected val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = inflateViewBinding(inflater)
        return _binding!!.root
    }
    //需要置空节约内存
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mArgs = ResultArgs(arguments)
        initView()
    }
    /**
     * 加载ViewBinding布局
     * 调用xxxBinding.inflate(inflater)
     */
    abstract fun inflateViewBinding(inflater: LayoutInflater):T

    /**
     * 初始化UI
     */
    abstract fun initView()

    fun go(@IdRes destination:Int, bundle: Bundle? = null) {
        navController.navigate(destination, bundle)
    }

    private val navController: NavController
        get() = findNavController()

    fun back() {
        navController.popBackStack()
    }

    fun <T> setResult(data:T) {
        if (null == mArgs) {
            return
        }
        navController.getBackStackEntry(mArgs!!.recipientId).savedStateHandle.getLiveData<T>(mArgs!!.requestCode.toString()).postValue(
            Pair(mArgs!!.requestCode, data) as T
        )
    }
    /**
     * @param destination 要迁移到的页面
     * @param requestCode 与StartActivityForResult 的RequestCode 相同
     * @param <T>         返回数据类型
     * @return [LiveData] Pair.first: requestCode; Pair.second: resultData
    </T> */
    protected fun  startFragmentForResult(
        @IdRes destination: Int,
        requestCode: Int
    ): LiveData<Pair<Int, Any>> {
        return startFragmentForResult(destination, requestCode, null)
    }

    protected fun  startFragmentForResult(
        requestCode: Int,
        @NonNull destination: NavDirections
    ): LiveData<Pair<Int, Any>> {
        return startFragmentForResult(destination.actionId, requestCode, destination.arguments)
    }

    private fun startFragmentForResult(
        @IdRes destination: Int,
        requestCode: Int,
        @Nullable bundle: Bundle?
    ): LiveData<Pair<Int, Any>> {
        val args =
            ResultArgs(navController.currentDestination!!.id, requestCode).setBusinessArgs(bundle)
        val liveData: LiveData<Pair<Int, Any>> =
            navController.currentBackStackEntry!!.savedStateHandle.getLiveData(requestCode.toString())
        navController.navigate(destination, args.toBundle())
        return liveData
    }

}


