package andy.zhu.center.base


fun readResource(resource: String): String {
    val inputStream = object {}.javaClass.classLoader.getResourceAsStream(resource)
    val sb = StringBuilder()
    inputStream.bufferedReader().use {
        it.readLines().forEach {
            sb.append(it)
        }
    }
    return sb.toString()
}