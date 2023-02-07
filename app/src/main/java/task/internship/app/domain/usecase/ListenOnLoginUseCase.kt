package task.internship.app.domain.usecase

import io.socket.emitter.Emitter
import task.internship.app.domain.repository.Repository

class ListenOnLoginUseCase(private val repository: Repository) {

    operator fun invoke(listener: Emitter.Listener) {
        repository.listenOnLogin(listener)
    }
}
