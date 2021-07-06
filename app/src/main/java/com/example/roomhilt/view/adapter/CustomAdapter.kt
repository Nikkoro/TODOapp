package com.example.roomhilt.view.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.roomhilt.R
import com.example.roomhilt.databinding.CustomBinder
import com.example.roomhilt.model.CustomModel
import com.example.roomhilt.view.CustomListeners
import com.example.roomhilt.view.holder.BaseViewHolder
import com.example.roomhilt.view.holder.CustomViewHolder

class CustomAdapter : RecyclerView.Adapter<BaseViewHolder> {

    companion object {
        private val TAG : String = CustomAdapter.javaClass::class.java.simpleName
    }

    private lateinit var context : Context
    private lateinit var customListeners: CustomListeners
    private lateinit var customBinder : CustomBinder
    private var list : MutableList<CustomModel> = mutableListOf()

    constructor(context : Context, customListeners: CustomListeners) : super() {
        this.context = context
        this.customListeners = customListeners
        setHasStableIds(false)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder {
         customBinder = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.design,
            parent,
            false
        )
        return CustomViewHolder(context,customListeners, customBinder)
    }

    override fun onBindViewHolder(holder : BaseViewHolder, position : Int) {
        customBinder.setCustomModel(list.get(position))
        holder.bindDataToViewHolder(list[position],position)
    }

    override fun getItemId(position : Int) : Long {
        return super.getItemId(position)
        //return list[position].id?.toLong()?:RecyclerView.NO_ID
    }

    override fun getItemCount(): Int {
        return list.size
    }

    fun setItems(items : List<CustomModel>) {
        list.clear()
        list.addAll(items)
        notifyDataSetChanged()
    }
}