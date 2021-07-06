package com.example.roomhilt

import androidx.lifecycle.*
import com.example.roomhilt.model.CustomModel
import com.example.roomhilt.repo.BaseRepo
import com.example.roomhilt.utils.ConvertList
import com.example.roomhilt.utils.Coroutines
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel : ViewModel, LifecycleObserver {

   /*stary sposób @ViewModelInject
    constructor(baseRepository : BaseRepo) {
        this.baseRepository = baseRepository
    }*/

    private val baseRepository: BaseRepo
    private val savedStateHandle: SavedStateHandle

    @Inject
    constructor(baseRepository: BaseRepo, savedStateHandle: SavedStateHandle) : super() {
        this.baseRepository = baseRepository
        this.savedStateHandle = savedStateHandle
    }

    private val liveList : MutableLiveData<MutableList<CustomModel>> by lazy(LazyThreadSafetyMode.NONE, initializer = {
        MutableLiveData<MutableList<CustomModel>>()
    })
    private val liveUpdate : MutableLiveData<CustomModel> by lazy(LazyThreadSafetyMode.NONE, initializer = {
        MutableLiveData<CustomModel>()
    })

    fun getInstance() : String {
        return this.toString()
    }

    fun getRepositoryInstance() : String {
        return baseRepository.giveRepository()
    }

    fun setUpdate(item : CustomModel) {
        liveUpdate.value = item
    }

    fun getUpdate() : LiveData<CustomModel> {
        return liveUpdate
    }

    fun insertItem(item : CustomModel) {
        Coroutines.io(this@MainViewModel) {
            baseRepository.insert(
                ConvertList.toEntity(item)
            )
        }
    }

    fun updateItem() {
        liveUpdate.value?.let {
            Coroutines.io(this@MainViewModel) {
                baseRepository.update(
                    ConvertList.toEntity(it)
                )
            }
        }
    }

    fun deleteItem(item : CustomModel) {
        Coroutines.io(this@MainViewModel) {
            baseRepository.delete(
                ConvertList.toEntity(item)
            )
        }
    }

    fun deleteAll() {
        Coroutines.io(this@MainViewModel) {
            baseRepository.deleteAll()
        }
    }

    fun getItems() : LiveData<MutableList<CustomModel>> { //return  liveList
        return ConvertList.toLiveDataListModel(
            baseRepository.getAll()
        )
    }



}