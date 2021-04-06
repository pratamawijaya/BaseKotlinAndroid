package pratama.library.logger

import timber.log.Timber

class LoggerImpl : Logger {
    override fun d(tag: String, message: String) {
        Timber.d("$tag : $message")
    }

    override fun e(tag: String, message: String) {
        Timber.e("$tag : $message")
    }

    override fun v(tag: String, message: String) {
        Timber.v("$tag : $message")
    }
}