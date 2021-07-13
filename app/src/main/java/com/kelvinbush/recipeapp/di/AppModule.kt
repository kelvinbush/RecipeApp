package com.kelvinbush.recipeapp.di

import com.kelvinbush.recipeapp.presentation.BaseApplication
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.android.scopes.FragmentScoped
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

//    @Provides
//    @Singleton
//    fun provideApplication(@ApplicationContext app: BaseApplication): BaseApplication {
//        return app
//    }

}