// Copyright 2000-2021 JetBrains s.r.o. and contributors. Use of this source code is governed by the Apache 2.0 license that can be found in the LICENSE file.
import androidx.compose.ui.window.Window
import androidx.compose.ui.window.application
import andy.zhu.center.preview.PreviewDocument
import andy.zhu.center.preview.PreviewLinks
import andy.zhu.center.shell.App


fun main() = application {
    Window(onCloseRequest = ::exitApplication) {
        PreviewLinks()
    }
}
