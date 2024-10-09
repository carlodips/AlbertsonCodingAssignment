package dev.carlodips.albertsoncodingassignment.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dev.carlodips.albertsoncodingassignment.api.RandomUsersAPI
import dev.carlodips.albertsoncodingassignment.model.repository.RandomUsersRepository
import dev.carlodips.albertsoncodingassignment.model.repository.RandomUsersRepositoryImpl
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {
    @Provides
    @Singleton
    fun provideRandomUsersRepository(
        api: RandomUsersAPI
    ): RandomUsersRepository {
        return RandomUsersRepositoryImpl(api)
    }
}