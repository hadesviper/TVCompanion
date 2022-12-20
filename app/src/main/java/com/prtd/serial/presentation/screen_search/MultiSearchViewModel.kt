package com.prtd.serial.presentation.screen_search

import android.view.KeyEvent
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.android.material.textfield.MaterialAutoCompleteTextView
import com.prtd.serial.common.HelperMethods.startMediaActivity
import com.prtd.serial.common.HelperMethods.startSearchActivity
import com.prtd.serial.common.Resources
import com.prtd.serial.domain.models.MultiResult
import com.prtd.serial.domain.use_cases.MultiSearchUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class MultiSearchViewModel @Inject constructor(
    private val multiSearchUseCase: MultiSearchUseCase
) :ViewModel() {
    private val loading      = MutableLiveData<Boolean>()
    private val result       = MutableLiveData<MultiResult>()
    private val error        = MutableLiveData<String>()

    fun getIsLoading():LiveData <Boolean>{
        return loading
    }
    fun getResult():LiveData <MultiResult>{
        return result
    }
    fun getError():LiveData <String>{
        return error
    }
    fun searchAll(name :String,page:Int){
        multiSearchUseCase(name,page).onEach {
            when (it) {
                is Resources.Loading -> {
                    loading.value = true
                }
                is Resources.Success -> {
                    loading.value = false
                    result.value = it.data
                }
                is Resources.Error -> {
                    loading.value = false
                    error.value = it.message
                }
            }
        }.launchIn(viewModelScope)
    }

    fun registerAutoComplete(searchView:MaterialAutoCompleteTextView){
        searchView.setOnKeyListener { _, p1, _ ->
            if (p1 == KeyEvent.KEYCODE_ENTER) {
                startSearchActivity(searchView.context,searchView)
            }
            true
        }
        getResult().observe(searchView.context as LifecycleOwner){
            searchView.setSimpleItems(
                it.results.map { results ->
                    results.autoComplete
                }.toTypedArray(),
            )
            searchView.setOnItemClickListener { _, _, i, _ ->
                startMediaActivity(
                    context = searchView.context,
                    mediaId = it.results[i].id,
                    mediaName = it.results[i].name,
                    type = it.results[i].type,
                )
            }
        }
        getIsLoading().observe(searchView.context as LifecycleOwner){
            if (it){
                searchView.dismissDropDown()
            }
            else{
                searchView.showDropDown()
            }

        }
    }
    fun getAutoComplete(text: String){
        searchAll(text,1)
    }

}