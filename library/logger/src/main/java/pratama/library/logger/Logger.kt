package pratama.library.logger

interface Logger {
    fun d(tag: String, message: String)
    fun e(tag: String, message: String)
    fun v(tag: String, message: String)
}