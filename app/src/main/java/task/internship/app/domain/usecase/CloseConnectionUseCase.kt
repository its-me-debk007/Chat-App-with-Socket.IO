package task.internship.app.domain.usecase

import task.internship.app.domain.repository.Repository

class CloseConnectionUseCase(private val repository: Repository) {

    operator fun invoke() {
        repository.closeConnection()
        repository.stopListeningEverything()
    }
}
