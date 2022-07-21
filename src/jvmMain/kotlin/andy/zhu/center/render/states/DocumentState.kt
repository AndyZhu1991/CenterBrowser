package andy.zhu.center.render.states

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import org.jsoup.nodes.Document

class DocumentState {

    private val mDocument = MutableStateFlow<Document?>(null)
    val document: StateFlow<Document?> = mDocument
}