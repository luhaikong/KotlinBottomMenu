package com.lhk.kotlinbottommenu.retrofit

import android.content.Context
import com.lhk.kotlinbottommenu.entity.*
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.IOException
import java.io.InputStream
import java.lang.AssertionError
import java.security.GeneralSecurityException
import java.security.KeyStore
import java.security.cert.Certificate
import java.security.cert.CertificateFactory
import java.util.*
import java.util.concurrent.TimeUnit
import javax.net.ssl.*


class ApiRepository(context: Context){
    companion object {
        const val DEFAULT_TIMEOUT:Long = 10
    }

    val retrofit = Retrofit.Builder()
        .baseUrl(ApiService.BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .client(ssl(context))
        .build()
        .create(ApiService::class.java)

    private fun ssl(context: Context):OkHttpClient{
        val okHttpBuilder = OkHttpClient.Builder()
        okHttpBuilder.retryOnConnectionFailure(true)
            .connectTimeout(DEFAULT_TIMEOUT,TimeUnit.SECONDS)

        val trustManager = trustManagerForCertificates(trustedCertificatesInputStream(context))
        val sslContext = SSLContext.getInstance("TLS")
        sslContext.init(null, arrayOf<TrustManager>(trustManager), null)
        val sslSocketFactory = sslContext.socketFactory

        okHttpBuilder.sslSocketFactory(sslSocketFactory,trustManager)
        return okHttpBuilder.build()
    }

    private fun trustedCertificatesInputStream(context: Context): InputStream? {
        var inputStream: InputStream? = null
        try {
            inputStream = context.getAssets().open("wanandroid.cer")
        } catch (e: IOException) {
            e.printStackTrace()
        }
        return inputStream
    }

    @Throws(GeneralSecurityException::class)
    private fun trustManagerForCertificates(`in`: InputStream?): X509TrustManager {
        val certificateFactory: CertificateFactory = CertificateFactory.getInstance("X.509")
        val certificates: Collection<Certificate?> =
            certificateFactory.generateCertificates(`in`)
        require(!certificates.isEmpty()) { "expected non-empty set of trusted certificates" }
        // Put the certificates a key store.
        val pw = "password".toCharArray() // Any password will work.
        val keyStore: KeyStore = newEmptyKeyStore(pw)
        var index = 0
        for (certificate in certificates) {
            val certificateAlias = Integer.toString(index++)
            keyStore.setCertificateEntry(certificateAlias, certificate)
        }
        // Use it to build an X509 trust manager.
        val keyManagerFactory: KeyManagerFactory = KeyManagerFactory.getInstance(
            KeyManagerFactory.getDefaultAlgorithm()
        )
        keyManagerFactory.init(keyStore, pw)
        val trustManagerFactory: TrustManagerFactory = TrustManagerFactory.getInstance(
            TrustManagerFactory.getDefaultAlgorithm()
        )
        trustManagerFactory.init(keyStore)
        val trustManagers: Array<TrustManager> = trustManagerFactory.getTrustManagers()
        check(!(trustManagers.size != 1 || trustManagers[0] !is X509TrustManager)) {
            ("Unexpected default trust managers:" + Arrays.toString(trustManagers))
        }
        return trustManagers[0] as X509TrustManager
    }

    @Throws(GeneralSecurityException::class)
    private fun newEmptyKeyStore(password: CharArray): KeyStore {
        return try {
            val keyStore: KeyStore = KeyStore.getInstance(KeyStore.getDefaultType())
            val `in`: InputStream? = null // By convention, 'null' creates an empty key store.
            keyStore.load(`in`, password)
            keyStore
        } catch (e: IOException) {
            throw AssertionError(e)
        }
    }

    suspend fun login(name: String,password: String): WanResponse<User> {
        return retrofit.login(name,password)
    }

    suspend fun treeJson(): WanResponse<MutableList<TypeTree>> {
        return retrofit.treeJson()
    }

    suspend fun listJson(cid:Int):WanResponse<TypeTreeListContent>{
        return retrofit.listJson(cid)
    }

    suspend fun wxarticle():WanResponse<MutableList<Subscriptions>>{
        return retrofit.wxarticle()
    }
}