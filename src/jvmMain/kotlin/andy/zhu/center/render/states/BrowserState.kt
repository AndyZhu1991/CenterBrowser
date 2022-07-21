package andy.zhu.center.render.states

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class BrowserState {

    private val mInputText = MutableStateFlow("")
    val inputText: StateFlow<String> = mInputText


    fun onInputTextChanged(text: String) {
        mInputText.value = text
    }
}