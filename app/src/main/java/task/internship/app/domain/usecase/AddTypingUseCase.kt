package task.internship.app.domain.usecase

import task.internship.app.domain.repository.Repository

class AddTypingUseCase(private val repository: Repository) {

    operator fun invoke() {
        repository.addTyping()
    }
}
