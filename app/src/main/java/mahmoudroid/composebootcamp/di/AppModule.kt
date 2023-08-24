package mahmoudroid.composebootcamp.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import mahmoudroid.composebootcamp.network.QuestionApi
import mahmoudroid.composebootcamp.repository.QuestionRepository
import mahmoudroid.composebootcamp.util.Constants
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideRetrofit(): Retrofit{
        return Retrofit.Builder()
            .baseUrl(Constants.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Singleton
    @Provides
    fun provideQuestionApi(retrofit: Retrofit): QuestionApi{
        return retrofit.create(QuestionApi::class.java)
    }

    @Singleton
    @Provides
    fun provideQuestionRepository(questionApi: QuestionApi) = QuestionRepository(questionApi)

}