package task.internship.app.domain.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import task.internship.app.domain.repository.Repository
import task.internship.app.domain.usecase.*
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object DomainModule {

    @Provides
    @Singleton
    fun provideListenNewMsgUseCase(repository: Repository): ListenNewMsgUseCase =
        ListenNewMsgUseCase(repository)

    @Provides
    @Singleton
    fun provideListenUserJoinUseCase(repository: Repository): ListenUserJoinUseCase =
        ListenUserJoinUseCase(repository)

    @Provides
    @Singleton
    fun provideListenUserLeaveUseCase(repository: Repository): ListenUserLeaveUseCase =
        ListenUserLeaveUseCase(repository)

    @Provides
    @Singleton
    fun provideStartConnectionUseCase(repository: Repository): StartConnectionUseCase =
        StartConnectionUseCase(repository)

    @Provides
    @Singleton
    fun provideCloseConnectionUseCase(repository: Repository): CloseConnectionUseCase =
        CloseConnectionUseCase(repository)

    @Provides
    @Singleton
    fun provideAddUserUseCase(repository: Repository): AddUserUseCase = AddUserUseCase(repository)

    @Provides
    @Singleton
    fun provideListenOnLoginUseCase(repository: Repository): ListenOnLoginUseCase =
        ListenOnLoginUseCase(repository)

    @Provides
    @Singleton
    fun provideSendMessageUseCase(repository: Repository): SendMessageUseCase =
        SendMessageUseCase(repository)
}
