package hackathon.bbva.com.qrsdk

import android.app.Application
import com.google.gson.FieldNamingPolicy
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import hackathon.bbva.com.qrsdk.qr.QRRemoteStorage
import hackathon.bbva.com.qrsdk.transactions.TransactionsRemoteStorage
import hackathon.bbva.com.qrsdk.user.LoginLocalStorage
import hackathon.bbva.com.qrsdk.user.LoginRemoteStorage
import io.reactivex.schedulers.Schedulers
import io.realm.Realm
import okhttp3.*
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton


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
                .addInterceptor { chain ->
                    val newRequest = chain.request().newBuilder()
                            .addHeader("Content-Type", "application/json")
                            .build()
                    chain.proceed(newRequest)
                }
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
     * Login Local Storage Database
     */
    @Provides
    @Singleton
    internal fun providerLoginLocalStorage (): LoginLocalStorage {
        return LoginLocalStorage()
    }

    /**
     * Login Remote Storage Database
     */
    @Provides
    @Singleton
    internal fun providerLoginRemoteStorage (): LoginRemoteStorage {
        return LoginRemoteStorage()
    }

    /**
     * QR Remote Storage Database
     */
    @Provides
    @Singleton
    internal fun providerQRRemoteStorage (): QRRemoteStorage {
        return QRRemoteStorage()
    }

    @Provides
    @Singleton
    internal fun providesTransactionsStorage (): TransactionsRemoteStorage {
        return TransactionsRemoteStorage()
    }

}