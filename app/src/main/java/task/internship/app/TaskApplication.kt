package task.internship.app

import android.app.Application
import android.util.Log
import dagger.hilt.android.HiltAndroidApp
import io.socket.client.IO
import io.socket.client.Socket
import task.internship.app.common.TAG
import java.net.URISyntaxException

@HiltAndroidApp
class TaskApplication : Application() {
    private lateinit var mSocket: Socket

    init {
        try {
            mSocket = IO.socket(BuildConfig.BASE_URL)
            Log.d(TAG, "initialised")

        } catch (e: URISyntaxException) {
            Log.d(TAG, e.message.toString())
        }
    }

    fun getSocket() : Socket = mSocket
}
