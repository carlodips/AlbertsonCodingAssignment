package dev.carlodips.albertsoncodingassignment.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dev.carlodips.albertsoncodingassignment.api.RandomUsersAPI
import dev.carlodips.albertsoncodingassignment.model.remote_source.UsersRemoteDataSource
import dev.carlodips.albertsoncodingassignment.model.remote_source.UsersRemoteDataSourceImpl
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataSourceModule {
    @Provides
    @Singleton
    fun provideRandomUsersRemoteDataSource(
        api: RandomUsersAPI
    ): UsersRemoteDataSource {
        return UsersRemoteDataSourceImpl(api)
    }
}