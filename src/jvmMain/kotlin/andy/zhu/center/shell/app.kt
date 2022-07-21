package andy.zhu.center.shell

import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.*
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.key.Key
import androidx.compose.ui.input.key.KeyEvent
import androidx.compose.ui.input.key.key
import androidx.compose.ui.input.key.onKeyEvent
import androidx.compose.ui.text.input.ImeAction
import andy.zhu.center.render.RenderDocument
import andy.zhu.center.render.states.BrowserState
import andy.zhu.center.render.states.DocumentState

@OptIn(ExperimentalComposeUiApi::class)
@Composable
@Preview
fun App() {
    val browserState by remember { mutableStateOf(BrowserState()) }

    Column {
        val inputText = browserState.inputText.collectAsState()
        TextField(
            value = inputText.value,
            label = { Text("Input the website address") },
            onValueChange = browserState::onInputTextChanged,
            singleLine = true,
            keyboardOptions = KeyboardOptions(imeAction = ImeAction.Done),
            keyboardActions = KeyboardActions(
                onDone = { browserState.onEnter() }
            ),
            modifier = Modifier.fillMaxWidth().onKeyEvent {
                if (it.key == Key.Enter) {
                    browserState.onEnter()
                    true
                } else false
            },
        )
        val document = browserState.document.collectAsState()
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
