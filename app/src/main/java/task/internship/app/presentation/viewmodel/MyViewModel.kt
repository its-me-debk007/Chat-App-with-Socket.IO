package task.internship.app.presentation.viewmodel

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import io.socket.emitter.Emitter
import task.internship.app.domain.usecase.*
import javax.inject.Inject

@HiltViewModel
class MyViewModel @Inject constructor(
    private val closeConnectionUseCase: CloseConnectionUseCase,
    private val listenEventConnectUseCase: ListenEventConnectUseCase,
    private val listenEventDisconnectUseCase: ListenEventDisconnectUseCase,
    private val listenNewMsgUseCase: ListenNewMsgUseCase,
    private val listenStopTypingUseCase: ListenStopTypingUseCase,
    private val listenTypingUseCase: ListenTypingUseCase,
    private val listenUserJoinUseCase: ListenUserJoinUseCase,
    private val listenUserLeaveUseCase: ListenUserLeaveUseCase,
    private val startConnectionUseCase: StartConnectionUseCase,
    private val addUserUseCase: AddUserUseCase,
    private val listenOnLoginUseCase: ListenOnLoginUseCase,
    private val addTypingUseCase: AddTypingUseCase,
    private val sendMessageUseCase: SendMessageUseCase
) : ViewModel() {
    init {
        startConnectionUseCase()
    }

    fun addUser(username: String) = addUserUseCase(username)

    fun listenOnLogin(listener: Emitter.Listener) = listenOnLoginUseCase(listener)

    fun addTyping() = addTypingUseCase()

    fun sendMessage(message: String) = sendMessageUseCase(message)

    fun listenNewMessage(listener: Emitter.Listener) = listenNewMsgUseCase(listener)
}
