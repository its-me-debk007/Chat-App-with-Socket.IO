package task.internship.app.presentation.viewmodel

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import io.socket.emitter.Emitter.Listener
import task.internship.app.domain.usecase.*
import javax.inject.Inject

@HiltViewModel
class MyViewModel @Inject constructor(
    private val closeConnectionUseCase: CloseConnectionUseCase,
    private val listenNewMsgUseCase: ListenNewMsgUseCase,
    private val listenUserJoinUseCase: ListenUserJoinUseCase,
    private val listenUserLeaveUseCase: ListenUserLeaveUseCase,
    private val startConnectionUseCase: StartConnectionUseCase,
    private val addUserUseCase: AddUserUseCase,
    private val listenOnLoginUseCase: ListenOnLoginUseCase,
    private val sendMessageUseCase: SendMessageUseCase
) : ViewModel() {

    init {
        startConnectionUseCase()
    }

    fun addUser(username: String) = addUserUseCase(username)

    fun listenUserJoining(listener: Listener) = listenUserJoinUseCase(listener)

    fun listenOnLogin(listener: Listener) = listenOnLoginUseCase(listener)

    fun sendMessage(message: String) = sendMessageUseCase(message)

    fun listenNewMessage(listener: Listener) = listenNewMsgUseCase(listener)

    fun listenUserLeaving(listener: Listener) = listenUserLeaveUseCase(listener)

    fun closeConnection() = closeConnectionUseCase()
}
