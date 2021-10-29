package com.example.restapitutorial.retrofit

import android.util.Log
import com.example.restapitutorial.utils.API
import com.example.restapitutorial.utils.Constants.TAG
import com.example.restapitutorial.utils.RESPONSE_STATE
import com.example.yanadu.retrofit_practice.retrofit_data.Sample
import com.example.yanadu.retrofit_practice.retrofit.IRetrofit


import retrofit2.Call
import retrofit2.Response

class RetrofitManager {

    companion object {
        val instance= RetrofitManager()
    }

    //레트로핏 인터페이스 가져오기
    private val iRetrofit: IRetrofit?=RetrofitClient.getClient(API.BASE_URL)?.create(IRetrofit::class.java)


    //쿼리 필요할
    fun getdata(searchTerm:String?,completion:(RESPONSE_STATE, String)->Unit){
        val term:String=searchTerm.let{
            it
        }?:""    //searchTerm이 null 일 수도 있기때문에 검사해주고 null인 경우 "" 넣기
        //val term=searchTerm?:""  위에를 축약해서 적은 것
//        val call:Call<JsonElement> =iRetrofit?.searchPhotos(searchTerm=term).let{
//            it
//        }?: return
        val call:Call<Sample> = iRetrofit?.getdata(searchTerm=term) ?: return

        call.enqueue(object :retrofit2.Callback<Sample>{

            //응답 성공
            override fun onResponse(call: Call<Sample>, response: Response<Sample>) {
                Log.d(TAG,"RetrofitManager-onResponse() called/Response: ${response.body()}")

                response.body()?.let { completion(RESPONSE_STATE.OKAY, it.toString()) }
            }
            //응답 실패
            override fun onFailure(call: Call<Sample>, t: Throwable) {
                Log.d(TAG,"RetrofitMangager-onFaiure() called /t:$t")
                completion(RESPONSE_STATE.FAIL,t.toString())

            }
        })

    }

    /*쿼리 필요없을시*/
    fun getdatas(completion:(RESPONSE_STATE,String)->Unit){

        val call:Call<Sample> = iRetrofit?.getdatas() ?: return

        call.enqueue(object :retrofit2.Callback<Sample>{

            //응답 성공
            override fun onResponse(call: Call<Sample>, response: Response<Sample>) {
                Log.d(TAG,"RetrofitManager-onResponse() called/Response: ${response.body()}")

                completion(RESPONSE_STATE.OKAY,response.body().toString())
            }
            //응답 실패
            override fun onFailure(call: Call<Sample>, t: Throwable) {
                Log.d(TAG,"RetrofitMangager-onFaiure() called /t:$t")
                completion(RESPONSE_STATE.FAIL,t.toString())

            }
        })

    }



}