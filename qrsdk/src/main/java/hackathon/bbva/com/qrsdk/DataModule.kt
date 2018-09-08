package hackathon.bbva.com.qrsdk

import android.app.Application
import com.google.gson.FieldNamingPolicy
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import io.reactivex.schedulers.Schedulers
import okhttp3.*
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Named
import javax.inject.Singleton
import java.security.KeyManagementException
import java.security.NoSuchAlgorithmException


/**
 * Created by Dinorah Tovar on 2/14/17.
 * Provides Retrofit and Repositories
 */

@Module
class DataModule(private val mBaseUrl: String) {

    /**
     * CompanionObject
     * RxJavaAdapter
     * @link https://github.com/square/retrofit/tree/master/retrofit-adapters/rxjava
     */
    companion object {
        private val rxAdapter = RxJava2CallAdapterFactory.createWithScheduler(Schedulers.io())
    }

    /**
     * OkHttpClient
     */
    @Provides
    @Singleton
    fun getUnsafeOkHttpClient(): OkHttpClient {
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.HEADERS
        interceptor.level = HttpLoggingInterceptor.Level.BODY
        val builder = OkHttpClient.Builder()
        builder.addInterceptor(interceptor)
                .connectTimeout(20, TimeUnit.SECONDS)
                .readTimeout(20, TimeUnit.SECONDS)
        return builder.build()
    }

    /**
     * Cache Provider
     */
    @Provides
    @Singleton
    internal fun provideHttpCache(application: Application): Cache {
        val cacheSize = 10 * 1024 * 1024
        return Cache(application.cacheDir, cacheSize.toLong())
    }

    /**
     * Gson Provider
     */
    @Provides
    @Singleton
    internal fun provideGson(): Gson {
        val gsonBuilder = GsonBuilder()
        gsonBuilder.setFieldNamingPolicy(FieldNamingPolicy.IDENTITY)
        return gsonBuilder.create()
    }

    /**
     * Retrofit Provider
     */
    @Provides
    @Singleton
    internal fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
                .baseUrl(mBaseUrl)
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(rxAdapter)
                .build()
    }

    /**
     * Realm Provider
     */
    @Provides
    @Singleton
    internal fun provideRealm(): Realm {
        return Realm.getDefaultInstance()
    }

    /**
     * Firebase Database Provider
     */
    /*@Provides
    @Singleton
    internal fun providerDatabaseFirebase (): FirebaseDatabase {
        return FirebaseDatabase.getInstance();
    }*/

}