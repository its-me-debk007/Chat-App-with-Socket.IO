package task.internship.app.domain.usecase

import task.internship.app.domain.repository.Repository

class AddUserUseCase(private val repository: Repository) {

    operator fun invoke(username: String) {
        repository.addUser(username)
    }
}
