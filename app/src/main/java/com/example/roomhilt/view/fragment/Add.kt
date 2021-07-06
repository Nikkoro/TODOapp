package com.example.roomhilt.view.fragment

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import androidx.databinding.DataBindingUtil.inflate
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.example.roomhilt.MainViewModel
import com.example.roomhilt.R
import com.example.roomhilt.databinding.AddBinder
import com.example.roomhilt.model.CustomModel

class Add : Fragment() {

    companion object {
        private val TAG : String =Add.javaClass::class.java.simpleName

        fun getTag() : String {
            return TAG
        }

        fun newInstance() : Add {
            return Add()
        }
    }

    private lateinit var binding : AddBinder
    private val mainAndroidViewModel : MainViewModel by activityViewModels()

    override fun onCreateView(inflater : LayoutInflater, container : ViewGroup?, savedInstanceState : Bundle?) : View? {
        binding = inflate(inflater, R.layout.add,container,false)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        @Suppress("DEPRECATION")
        super.onActivityCreated(savedInstanceState)

        binding.setMainViewModel(mainAndroidViewModel)
        binding.setLifecycleOwner(viewLifecycleOwner)

        binding.textTitle.requestFocus()
        showSoftKeyboard()

        binding.button2.setOnClickListener(object : View.OnClickListener {
            override fun onClick(view : View) {
                binding.getMainViewModel()?.insertItem(CustomModel(binding.textTitle.text.toString(),binding.textDescription.text.toString(),binding.textDate.text.toString()))
                hideSoftKeyboard()
                activity!!.supportFragmentManager.popBackStack()
            }
        })
    }

    private fun showSoftKeyboard() {
        val inputMethodManager : InputMethodManager = requireContext().getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.toggleSoftInput(InputMethodManager.SHOW_FORCED, 0)
    }

    private fun hideSoftKeyboard() {
        val inputMethodManager : InputMethodManager = requireContext().getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.toggleSoftInput(InputMethodManager.HIDE_IMPLICIT_ONLY, 0)
    }
}