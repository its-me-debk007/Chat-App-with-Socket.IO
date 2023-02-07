package task.internship.app.domain.repository

import io.socket.emitter.Emitter

interface Repository {
    fun startConnection()

    fun closeConnection()

    fun listenNewMessage(listener: Emitter.Listener)

    fun listenUserJoining(listener: Emitter.Listener)

    fun listenUserLeaving(listener: Emitter.Listener)

    fun listenTyping(listener: Emitter.Listener)

    fun listenStopTyping(listener: Emitter.Listener)

    fun listenEventDisconnect(listener: Emitter.Listener)

    fun listenEventConnect(listener: Emitter.Listener)

    fun stopListeningEverything()
}
