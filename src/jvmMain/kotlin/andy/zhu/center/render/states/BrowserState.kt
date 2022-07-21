package andy.zhu.center.render.states

import androidx.compose.ui.text.intl.Locale
import androidx.compose.ui.text.toLowerCase
import andy.zhu.center.base.httpGet
import andy.zhu.center.base.readResource
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import org.jsoup.Jsoup
import org.jsoup.nodes.Document
import java.io.File
import java.io.FileInputStream
import java.net.URI

class BrowserState {

    private val mInputText = MutableStateFlow("")
    val inputText: StateFlow<String> = mInputText

    private val mDocument = MutableStateFlow<Document?>(null)
    val document: StateFlow<Document?> = mDocument


    fun onInputTextChanged(text: String) {
        mInputText.value = text
    }


    fun onEnter() {
        loadContent(mInputText.value)
    }


    private fun fixSlash(uri: String): String {
        return if (uri.startsWith("file://") && File.separator == "\\") {
            uri.replace("\\", "/")
        } else {
            uri
        }
    }


    private fun loadContent(inputString: String) {
        if (inputString.isEmpty()) {
            return
        }
        val uri = URI(fixSlash(inputString))
        when (uri.scheme?.toLowerCase(Locale.current)) {
            "http", "https" -> {
                loadHttpContent(inputString)
            }
            "file" -> {
                loadFileContent(inputString.substring("file://".length))
            }
            "resource" -> {
                onContentLoaded(readResource(inputString.substring("resource://".length)))
            }
            null -> {
                loadHttpContent("https://$inputString")
            }
            else -> {
            }
        }
    }


    private fun loadHttpContent(url: String) {
        CoroutineScope(Dispatchers.IO).launch {
            httpGet(url).let {
                if (it.isSuccessful) {
                    onContentLoaded(it.body?.string().orEmpty())
                }
            }
        }
    }


    private fun loadFileContent(filePath: String) {
        CoroutineScope(Dispatchers.IO).launch {
            FileInputStream(filePath).use {
                onContentLoaded(it.readBytes().toString(Charsets.UTF_8))
            }
        }
    }


    private fun onContentLoaded(content: String) {
        mDocument.value = Jsoup.parse(content)
    }
}