package task.internship.app.domain.usecase

import task.internship.app.domain.repository.Repository

class SendMessageUseCase(private val repository: Repository) {

    operator fun invoke(message: String) {
        repository.sendMessage(message)
    }
}
