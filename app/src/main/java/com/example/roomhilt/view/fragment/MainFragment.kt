@file:Suppress("DEPRECATION")

package com.example.roomhilt.view.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil.inflate
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.roomhilt.MainViewModel
import com.example.roomhilt.R
import com.example.roomhilt.databinding.MainBinder
import com.example.roomhilt.model.CustomModel
import com.example.roomhilt.view.CustomListeners
import com.example.roomhilt.view.MainActivity
import com.example.roomhilt.view.adapter.CustomAdapter

class MainFragment : Fragment(), CustomListeners{

    companion object {
        private val TAG : String = MainFragment.javaClass::class.java.getSimpleName()

        fun getTag() : String {
            return TAG
        }

        fun newInstance() : MainFragment {
            return MainFragment()
        }
    }

    private lateinit var binding : MainBinder
    private val mainAndroidViewModel : MainViewModel by activityViewModels()
    private lateinit var adapter : CustomAdapter


    override fun onCreateView(inflater : LayoutInflater, container : ViewGroup?, savedInstanceState : Bundle?) : View? {
        binding = inflate(inflater, R.layout.main_fragment,container,false)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        binding.setMainViewModel(mainAndroidViewModel)
        binding.lifecycleOwner = viewLifecycleOwner

        setRecyclerView()
        setFloatingActionButton()
    }

    private fun setRecyclerView() {
        adapter = CustomAdapter(requireContext(), this@MainFragment)

        binding.recyclerViewMain.layoutManager = LinearLayoutManager(requireContext(), RecyclerView.VERTICAL,false)
        binding.recyclerViewMain.adapter = adapter

        (binding.recyclerViewMain.layoutManager as LinearLayoutManager).setAutoMeasureEnabled(false)

        binding.getMainViewModel()?.getItems()?.observe(getViewLifecycleOwner(), object :
            Observer<List<CustomModel>> {
            override fun onChanged(list : List<CustomModel>) {
                Log.d("MainFragment","ID ${list.map { it.id }}, Title ${list.map { it.title  }}")
                binding.recyclerViewMain.removeAllViews()
                adapter.setItems(list)
            }
        })
        binding.recyclerViewMain.setHasFixedSize(true)
    }

    private fun setFloatingActionButton() {
        binding.floatingActionButtonAdd.setOnClickListener(object : View.OnClickListener {
            override fun onClick(view : View?) {
                onAdd()
            }
        })
        binding.floatingActionButtonDelete.setOnClickListener(object : View.OnClickListener {
            override fun onClick(view : View?) {
                binding.getMainViewModel()?.deleteAll()
                Toast.makeText(context,"All notes deleted!", Toast.LENGTH_SHORT).show()
            }
        })
    }

    private fun onAdd() {
        (activity as MainActivity).callAddFragment()
    }

    override fun onUpdate(item : CustomModel, position : Int) {
        binding.getMainViewModel()?.setUpdate(item)
        (activity as MainActivity).callUpdateFragment()
    }

    override fun onDelete(item : CustomModel, position : Int) {
        binding.getMainViewModel()?.deleteItem(item)
        Toast.makeText(context,"Note deleted!", Toast.LENGTH_SHORT).show()
    }
}