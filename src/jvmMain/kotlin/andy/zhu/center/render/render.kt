package andy.zhu.center.render

import androidx.compose.foundation.layout.Column
import androidx.compose.material.MaterialTheme
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
    when (element.tag().name) {
        Tag.TAG_BODY.tagName -> {
            RenderBody(element)
        }
        Tag.TAG_P.tagName -> {
            RenderParagraph(element)
        }
        Tag.TAG_H1.tagName -> {
            RenderHeading1(element)
        }
        Tag.TAG_H2.tagName -> {
            RenderHeading2(element)
        }
        Tag.TAG_H3.tagName -> {
            RenderHeading3(element)
        }
        Tag.TAG_H4.tagName -> {
            RenderHeading4(element)
        }
        Tag.TAG_H5.tagName -> {
            RenderHeading5(element)
        }
        Tag.TAG_H6.tagName -> {
            RenderHeading6(element)
        }
        Tag.TAG_A.tagName -> {
            RenderAnchor(element)
        }
        else -> {
            System.err.println("Unsupported tag: ${element.tag().name}")
        }
    }
}


@Composable
fun RenderBody(body: Element) {
    Column {
        body.children().forEach {
            RenderElement(it)
        }
    }
}


@Composable
fun RenderParagraph(paragraph: Element) {
    Text(paragraph.text())
}


@Composable
fun RenderHeading1(heading1: Element) {
    Text(heading1.text(), style = MaterialTheme.typography.h1)
}


@Composable
fun RenderHeading2(heading2: Element) {
    Text(heading2.text(), style = MaterialTheme.typography.h2)
}


@Composable
fun RenderHeading3(heading3: Element) {
    Text(heading3.text(), style = MaterialTheme.typography.h3)
}


@Composable
fun RenderHeading4(heading4: Element) {
    Text(heading4.text(), style = MaterialTheme.typography.h4)
}


@Composable
fun RenderHeading5(heading5: Element) {
    Text(heading5.text(), style = MaterialTheme.typography.h5)
}


@Composable
fun RenderHeading6(heading6: Element) {
    Text(heading6.text(), style = MaterialTheme.typography.h6)
}


@Composable
fun RenderAnchor(anchor: Element) {
    // anchor.attr("href")
    Text(anchor.text(), style = linkText)
}
