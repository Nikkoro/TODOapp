package com.example.roomhilt.view.fragment

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import androidx.databinding.DataBindingUtil.inflate
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import com.example.roomhilt.MainViewModel
import com.example.roomhilt.R
import com.example.roomhilt.databinding.UpdateBinder
import com.example.roomhilt.model.CustomModel

class UpdateFragment : DialogFragment() {

    companion object {
        private val TAG : String = UpdateFragment.javaClass::class.java.simpleName

        fun getTag() : String {
            return TAG
        }

        fun newInstance() : UpdateFragment {
            return UpdateFragment()
        }
    }

    private lateinit var binding : UpdateBinder
    private val mainAndroidViewModel : MainViewModel by activityViewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setStyle(DialogFragment.STYLE_NORMAL, R.style.DialogFragment)
    }

    override fun onCreateView(inflater : LayoutInflater, container : ViewGroup?, savedInstanceState : Bundle?) : View {
        binding = inflate(inflater, R.layout.update_fragment,container,false)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState : Bundle?) {
        @Suppress("DEPRECATION")
        super.onActivityCreated(savedInstanceState)

        binding.setMainViewModel(mainAndroidViewModel)
        binding.setLifecycleOwner(getViewLifecycleOwner())

        binding.getMainViewModel()?.getUpdate()?.observe(getViewLifecycleOwner(), object :
            Observer<CustomModel> {
            override fun onChanged(item : CustomModel) {
                binding.textTitle.setText(item.title)
                binding.textTitle.requestFocus()
                binding.textTitle.selectAll()
                showSoftKeyboard()
            }
        })

        binding.button3.setOnClickListener(object : View.OnClickListener {
            override fun onClick(view : View) {
                binding.getMainViewModel()?.updateItem()
                hideSoftKeyboard()
                dismiss()
            }
        })
    }

    private fun showSoftKeyboard() {
        val inputMethodManager: InputMethodManager = requireContext().getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.toggleSoftInput(InputMethodManager.SHOW_FORCED, 0)
    }

    private fun hideSoftKeyboard() {
        val inputMethodManager: InputMethodManager = requireContext().getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.toggleSoftInput(InputMethodManager.HIDE_IMPLICIT_ONLY, 0)
    }

}