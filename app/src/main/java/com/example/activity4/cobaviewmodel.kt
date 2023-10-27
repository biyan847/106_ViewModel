package com.example.activity4
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.activity4.Data.DataFrom
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update

class cobaviewmodel : ViewModel() {
    var namausr: String by mutableStateOf("")
        private set
    var noTLP: String by mutableStateOf("")
        private set
    var Email: String by mutableStateOf("")
        private set
    var jeniskl: String by mutableStateOf("")
        private set
    var status: String by mutableStateOf("")
        private set
    var alamat: String by mutableStateOf("")
        private set

    private val _uiState = MutableStateFlow(DataFrom())
    val uiState:StateFlow<DataFrom> = _uiState.asStateFlow()


    fun insertData(nm: String, tlp:String, jk:String,email: String,st:String , at:String,){
        namausr = nm;
        noTLP = tlp;
        Email = email;
        jeniskl = jk;
        status = st;
        alamat = at;

    }
    fun setjenisk(pilihjk:String){
        _uiState.update { currentState -> currentState.copy(sex = pilihjk) }
    }
    fun setstatus(pilihstatus:String){
        _uiState.update { currentSate -> currentSate.copy(status = pilihstatus) }
    }
}