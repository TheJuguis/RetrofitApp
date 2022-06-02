package com.example.basicretrofitapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.basicretrofitapp.remote.PokemonEntry
import com.example.basicretrofitapp.remote.RetrofitBuilder
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val retrofit = RetrofitBuilder.create().getPokemonById("6")

        retrofit.enqueue(object: Callback<PokemonEntry>{
            override fun onResponse(call: Call<PokemonEntry>, response: Response<PokemonEntry>) {
                val resBody = response.body()
                if(resBody != null){
                    Log.d("retrofitResponse", "res: ${resBody}")
                    Log.d("retrofitResponse", "name: ${resBody.name}")
                    for(stat in resBody.stats){
                        Log.d("retrofitResponse", "${stat.stat.stat_value}: ${stat.base_stat}")
                    }
                    Log.d("retrofitResponse","type: ${resBody.types[0].type.name}")


                    Log.d("retrofitResponse", "front_default:${resBody.sprites.front_default}")



                }
            }

            override fun onFailure(call: Call<PokemonEntry>, t: Throwable) {
                Log.e("retrofitResponse","Error: ${t.message}")
            }
        })




    }
}