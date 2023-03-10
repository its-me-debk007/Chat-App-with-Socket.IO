package task.internship.app.data.repository

import io.socket.client.Socket
import io.socket.emitter.Emitter
import task.internship.app.common.*
import task.internship.app.domain.repository.Repository
import javax.inject.Inject

class RepositoryImpl @Inject constructor(private val socket: Socket) : Repository {
    override fun startConnection() {
        socket.connect()
    }

    override fun closeConnection() {
        socket.disconnect()
    }

    override fun listenNewMessage(listener: Emitter.Listener) {
        socket.on(NEW_MESSAGE, listener)
    }

    override fun listenUserJoining(listener: Emitter.Listener) {
        socket.on(USER_JOINED, listener)
    }

    override fun listenUserLeaving(listener: Emitter.Listener) {
        socket.on(USER_LEFT, listener)
    }

    override fun stopListeningEverything() {
        socket.off()
    }

    override fun addUser(username: String) {
        socket.emit(ADD_USER, username)
    }

    override fun listenOnLogin(listener: Emitter.Listener) {
        socket.on(LOGIN, listener)
    }

    override fun sendMessage(message: String) {
        socket.emit(NEW_MESSAGE, message)
    }
}
