package andy.zhu.center.preview

import androidx.compose.desktop.ui.tooling.preview.Preview
import androidx.compose.runtime.Composable
import andy.zhu.center.base.readResource
import andy.zhu.center.render.RenderHtmlString


@Composable
private fun PreviewFromResource(htmlName: String) {
    RenderHtmlString(readResource("htmls/$htmlName.html"))
}


@Preview
@Composable
fun PreviewHello() = PreviewFromResource("hello")
