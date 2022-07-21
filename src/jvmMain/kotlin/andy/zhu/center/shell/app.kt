package andy.zhu.center.shell

import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import andy.zhu.center.render.RenderDocument
import andy.zhu.center.render.states.BrowserState
import andy.zhu.center.render.states.DocumentState

@Composable
@Preview
fun App() {
    val browserState by remember { mutableStateOf(BrowserState()) }
    val documentState by remember { mutableStateOf(DocumentState()) }
    Column {
        val inputText = browserState.inputText.collectAsState()
        TextField(
            value = inputText.value,
            label = { Text("Input the website address") },
            onValueChange = browserState::onInputTextChanged,
            modifier = Modifier.fillMaxWidth()
        )
        val document = documentState.document.collectAsState()
        Box(modifier = Modifier.weight(1f)) {
            val documentValue = document.value
            if (documentValue != null) {
                RenderDocument(documentValue)
            } else {
                EmptyDocument()
            }
        }
    }
}


@Preview
@Composable
fun EmptyDocument() {
    Text("No document")
}
