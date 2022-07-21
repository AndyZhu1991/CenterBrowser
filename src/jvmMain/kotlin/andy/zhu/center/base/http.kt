package andy.zhu.center.base

import okhttp3.OkHttpClient
import okhttp3.Response

private val client = OkHttpClient()

fun httpGet(url: String): Response {
    val request = okhttp3.Request.Builder()
            .url(url)
            .build()
    return client.newCall(request).execute()
}