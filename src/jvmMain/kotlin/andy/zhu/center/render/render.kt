package andy.zhu.center.render

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import org.jsoup.Jsoup
import org.jsoup.nodes.Document
import org.jsoup.nodes.Element


@Composable
fun RenderHtmlString(html: String) {
    RenderDocument(Jsoup.parse(html))
}


@Composable
fun RenderDocument(document: Document) {
    RenderElement(document.body())
}


@Composable
fun RenderElement(element: Element) {
    Text(element.text())
}