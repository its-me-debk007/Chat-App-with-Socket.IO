package task.internship.app.domain.repository

import io.socket.emitter.Emitter

interface Repository {
    fun startConnection()

    fun closeConnection()

    fun listenNewMessage(listener: Emitter.Listener)

    fun listenUserJoining(listener: Emitter.Listener)

    fun listenUserLeaving(listener: Emitter.Listener)

    fun stopListeningEverything()

    fun addUser(username: String)

    fun listenOnLogin(listener: Emitter.Listener)

    fun sendMessage(message: String)
}
