package com.example.roomhilt

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.example.roomhilt.repo.BaseRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainAndroidViewModel : AndroidViewModel {

    @Inject
    constructor(
        baseRepository: BaseRepo,
        application: Application,
    ) : super(application){
        this.baseRepository = baseRepository
    }

    private val  baseRepository : BaseRepo

   /*stary sposób @ViewModelInject
    constructor(baseRepository : BaseRepo, application: Application) : super(application) {
        this.baseRepository = baseRepository
    }*/

    fun getInstance() : String {
        return this.toString()
    }

    fun getRepositoryInstance() : String {
        return baseRepository.giveRepository()
    }
}