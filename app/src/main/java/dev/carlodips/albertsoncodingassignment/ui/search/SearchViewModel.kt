package dev.carlodips.albertsoncodingassignment.ui.search

import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dev.carlodips.albertsoncodingassignment.utils.Event
import dev.carlodips.albertsoncodingassignment.utils.toEvent

class SearchViewModel : ViewModel() {

    val ldInput = MutableLiveData<String>()

    val ldNavigate = MutableLiveData<Event<String>>()

    private val _ldInputError = MediatorLiveData<String?>()
    val ldInputError: LiveData<String?>
        get() = _ldInputError


    init {
        _ldInputError.addSource(ldInput) {
            isInputValid()
        }
    }

    fun onButtonClick() {
        if (!isInputValid()) return

        ldNavigate.value = ldInput.value!!.toEvent()
    }

    fun isInputValid(): Boolean {
        val pair = when {
            ldInput.value.isNullOrBlank() -> false to "Input cannot be blank"

            ldInput.value == "0" -> false to "Input should not be 0"

            ldInput.value?.toInt()!! > 5000 -> false to "Input should only be between 1-5000"

            else -> true to null
        }

        _ldInputError.value = pair.second

        return pair.first
    }
}