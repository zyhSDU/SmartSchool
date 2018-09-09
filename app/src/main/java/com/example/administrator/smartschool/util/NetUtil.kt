package com.example.administrator.tiaozhanbei.util

import com.example.administrator.smartschool.temp.Temp
import java.io.IOException
import java.util.ArrayList
import java.util.HashMap

import okhttp3.Call
import okhttp3.Callback
import okhttp3.Cookie
import okhttp3.CookieJar
import okhttp3.FormBody
import okhttp3.HttpUrl
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody
import okhttp3.Response

/**
 * Created by Administrator on 2016/7/11.
 */
class NetUtil private constructor() {

    /**
     * 利用Okhttp的同步GET方法，
     * 用于获取json等大小较小的数据
     * 不能再主线程中使用
     *
     * @param url
     * @return string
     */

    private fun mGet(url: String, callBackForResult: CallBackForResult) {
        //创建一个Request
        val request = Request.Builder()
                .url(url)
                .build()
        try {
            val response = mOkHttpClient.newCall(request).execute()
            if (response.isSuccessful) {
                callBackForResult.onSuccess(response)
            }
        } catch (e: IOException) {
            e.printStackTrace()
            callBackForResult.onFailure(e)
        }

    }

    private fun mGet(url: String, headers: Map<String, String>, callBackForResult: CallBackForResult) {
        val a = Request.Builder()

        for (key in headers.keys) {
            a.addHeader(key, headers[key])
        }

        val request = a.url(url).build()
        try {
            val response = mOkHttpClient.newCall(request).execute()
            if (response.isSuccessful) {
                callBackForResult.onSuccess(response)
            }
        } catch (e: IOException) {
            e.printStackTrace()
            callBackForResult.onFailure(e)
        }

    }

    @Throws(IOException::class)
    private fun mPost(url: String, params: Map<String, String>, callBackForResult: CallBackForResult) {
        val mBuilder = FormBody.Builder()
        val body: RequestBody
        for (key in params.keys) {
            mBuilder.add(key, params[key])
        }
        body = mBuilder.build()
        val a = Request.Builder()


        val request = a.url(url).post(body).build()
        mOkHttpClient.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                callBackForResult.onFailure(e)
            }

            @Throws(IOException::class)
            override fun onResponse(call: Call, response: Response) {
                callBackForResult.onSuccess(response)
            }
        })
    }

    @Throws(IOException::class)
    private fun mPost(url: String, params: Map<String, String>, headers: Map<String, String>, callBackForResult: CallBackForResult) {
        val mBuilder = FormBody.Builder()
        val body: RequestBody
        for (key in params.keys) {
            mBuilder.add(key, params[key])
        }
        body = mBuilder.build()

        val a = Request.Builder()

        for (key in headers.keys) {
            a.addHeader(key, headers[key])
        }

        val request = a.url(url).post(body).build()
        mOkHttpClient.newCall(request).enqueue(object : Callback {

            override fun onFailure(call: Call, e: IOException) {
                callBackForResult.onFailure(e)
            }

            @Throws(IOException::class)
            override fun onResponse(call: Call, response: Response) {
                callBackForResult.onSuccess(response)
            }
        })
    }

    interface CallBackForResult {
        fun onFailure(e: IOException) {}

        fun onSuccess(response: Response)
    }

    companion object {
        private val cookieStore = HashMap<String, List<Cookie>>()

        private var mInstance: NetUtil? = null
        private val mOkHttpClient = OkHttpClient.Builder().cookieJar(object : CookieJar {
            override fun saveFromResponse(httpUrl: HttpUrl, list: List<Cookie>) {
                cookieStore.put(httpUrl.host(), list)
            }

            override fun loadForRequest(httpUrl: HttpUrl): List<Cookie> {
                val cookies = cookieStore[httpUrl.host()]
                return cookies ?: ArrayList<Cookie>()
            }
        }).build()

        private val instance: NetUtil
            get() {
                if (mInstance == null) {
                    synchronized(NetUtil::class.java) {
                        if (mInstance == null) {
                            mInstance = NetUtil()
                        }
                    }
                }
                return this.mInstance!!
            }

        /**
         * 对外公布的方法1.0
         */
        private fun post(url: String, params: Map<String, String>, headers: Map<String, String>, callBackForResult: CallBackForResult) {
            Temp.startThread {
                instance.mPost(url, params, headers, callBackForResult)
            }
        }

        private fun post(url: String, params: Map<String, String>, callBackForResult: CallBackForResult) {
            Temp.startThread {
                instance.mPost(url, params, callBackForResult)
            }
        }

        private fun get(url: String, callBackForResult: CallBackForResult) {
            Temp.startThread {
                instance.mGet(url, callBackForResult)
            }
        }

        private fun get(url: String, headers: Map<String, String>, callBackForResult: CallBackForResult) {
            Temp.startThread {
                instance.mGet(url, headers, callBackForResult)
            }
        }

        /**
         * 对外公布的方法2.0
         */
        fun post(url: String, params: Map<String, String>, headers: Map<String, String>, initOnSuccess: (response: Response) -> Unit) {
            val callBackForResult = object : NetUtil.CallBackForResult {
                override fun onSuccess(response: Response) {
                    initOnSuccess(response)
                }
            }
            post(url, params, headers, callBackForResult)
        }

        fun post(url: String, params: Map<String, String>, initOnSuccess: (response: Response) -> Unit) {
            val callBackForResult = object : NetUtil.CallBackForResult {
                override fun onSuccess(response: Response) {
                    initOnSuccess(response)
                }
            }
            post(url, params, callBackForResult)
        }

        fun get(url: String, initOnSuccess: (response: Response) -> Unit) {
            val callBackForResult = object : NetUtil.CallBackForResult {
                override fun onSuccess(response: Response) {
                    initOnSuccess(response)
                }
            }
            get(url, callBackForResult)
        }

        fun get(url: String, headers: Map<String, String>, initOnSuccess: (response: Response) -> Unit) {
            val callBackForResult = object : NetUtil.CallBackForResult {
                override fun onSuccess(response: Response) {
                    initOnSuccess(response)
                }
            }
            get(url, headers, callBackForResult)
        }
    }
}
