package task.internship.app.data.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.socket.client.Socket
import task.internship.app.TaskApplication
import task.internship.app.data.repository.RepositoryImpl
import task.internship.app.domain.repository.Repository
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object DataModule {

    @Provides
    @Singleton
    fun provideSocket(): Socket = TaskApplication().getSocket()

    @Provides
    @Singleton
    fun provideRepository(socket: Socket): Repository = RepositoryImpl(socket)
}
