package com.example.appfood.ui.base

import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import androidx.viewbinding.ViewBinding
import com.example.appfood.utils.widget.LoadingDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

abstract class BaseBottomSheetFragment<VB : ViewBinding> : BottomSheetDialogFragment() {

    val TAG = this.javaClass.simpleName

    private var _binding: ViewBinding? = null
    abstract val bindingInflater: (LayoutInflater, ViewGroup?, Boolean) -> VB

    @Suppress("UNCHECKED_CAST")
    protected val binding: VB
        get() = _binding as VB

    lateinit var loadingDialog: Dialog

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = bindingInflater.invoke(inflater, container, false)

        return requireNotNull(_binding).root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        loadingDialog = LoadingDialog.default(requireContext())
    }

    open fun VB.initUI() {

    }

    open fun VB.initEvent() {

    }

    open fun VB.initObserve() {

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null

        if (loadingDialog.isShowing)
            loadingDialog.dismiss()
    }

    fun setupFullHeight(bottomSheet: View) {
        val layoutParams = bottomSheet.layoutParams
        layoutParams.height = WindowManager.LayoutParams.MATCH_PARENT
        bottomSheet.layoutParams = layoutParams
    }


    fun showLoading(cancelable: Boolean = false) {
        loadingDialog.setCancelable(cancelable)
        loadingDialog.show()
    }

    fun dismissLoading() {
        if (loadingDialog.isShowing)
            loadingDialog.dismiss()
    }
}