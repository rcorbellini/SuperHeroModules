package feature.superhero.data

import androidx.room.Room
import com.google.gson.FieldNamingPolicy
import com.google.gson.GsonBuilder
import com.google.gson.JsonSyntaxException
import com.google.gson.TypeAdapter
import com.google.gson.internal.bind.TypeAdapters
import com.google.gson.stream.JsonReader
import com.google.gson.stream.JsonToken
import com.google.gson.stream.JsonWriter
import feature.superhero.data.cache.SuperHeroDatabase
import feature.superhero.data.cache.dao.HeroDao
import feature.superhero.data.remote.services.HeroService
import feature.superhero.data.repositories.HeroRepositoryImp
import feature.superhero.domain.repositories.HeroRepository
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

import org.koin.android.ext.koin.androidContext

val heroCacheModule = module {

    single {
        Room
            .databaseBuilder(androidContext(), SuperHeroDatabase::class.java, "super_hero_db")
            .build()
    }

    single { heroDao(db = get()) }


}

internal fun heroDao(db: SuperHeroDatabase): HeroDao = db.heroDao()

val heroRemoteModule = module {
    single<HeroRepository> {
        HeroRepositoryImp(heroService = get(), heroDao = get())
    }
    single { provideHeroService(retrofit = get()) }

    single {
        provideRetrofit(
            okHttpClient = get(),
            url = "https://www.superheroapi.com/api.php/4006935012700536/"
        )
    }

    single { provideOkHttpClient() }
}

internal fun provideOkHttpClient(): OkHttpClient {
    val httpLoggingInterceptor = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }
    return OkHttpClient.Builder()
        .connectTimeout(60L, TimeUnit.SECONDS)
        .readTimeout(60L, TimeUnit.SECONDS)
        .addInterceptor(httpLoggingInterceptor)
        .build()
}

internal fun provideRetrofit(okHttpClient: OkHttpClient, url: String): Retrofit {
    val gson = GsonBuilder()
        .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_DASHES)
        .serializeNulls()
        .registerTypeAdapter(Int::class.java, EmptyToNullTypeAdapter())
        .create()

    return Retrofit.Builder()
        .baseUrl(url)
        .client(okHttpClient)
        .addConverterFactory(GsonConverterFactory.create(gson))
        .build()
}

internal fun provideHeroService(retrofit: Retrofit): HeroService =
    retrofit.create(HeroService::class.java)

//todo move to DATA-core lib
internal class EmptyToNullTypeAdapter : TypeAdapter<Int>() {
    override fun write(out: JsonWriter, value: Int?) {
        TypeAdapters.INTEGER.write(out, value)
    }

    override fun read(input: JsonReader): Int? {
        if (input.peek() == JsonToken.NULL) {
            input.nextNull()
            return null
        }
        try {
            if (input.peek() == JsonToken.STRING) {
                val strValue = input.nextString()
                if (strValue.isEmpty() || strValue.equals("null")) {
                    return null
                }
                return strValue.toInt()
            }
            return input.nextInt()
        } catch (e: NumberFormatException) {
            throw JsonSyntaxException(e)
        }
    }
}
