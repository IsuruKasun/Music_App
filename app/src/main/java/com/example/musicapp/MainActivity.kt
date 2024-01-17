package com.example.musicapp

import android.content.ContentValues.TAG
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {

    lateinit var myRecyclerView: RecyclerView // initialized here and give values later
    lateinit var myAdapter: MyAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        myRecyclerView = findViewById(R.id.recycleView)

        val retrofitBuilder = Retrofit.Builder()
            .baseUrl("https://deezerdevs-deezer.p.rapidapi.com")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(APIinterface::class.java)

        val retrofitData = retrofitBuilder.getData("eminem") // got the API data to main activity

        // now, we need to the the API is work success or not. control + shift + space = to get the call back
        retrofitData.enqueue(object : Callback<MyData?> {
            override fun onResponse(call: Call<MyData?>, response: Response<MyData?>) {
                // if the API is success, this method is executed
                val dataList = response.body()?.data!! // ? = if the data is null, the system will not crash
                //val textView = findViewById<TextView>(R.id.textHello)
                //textView.text = dataList.toString()

                myAdapter = MyAdapter(this@MainActivity, dataList)
                myRecyclerView.adapter=myAdapter
                myRecyclerView.layoutManager = LinearLayoutManager(this@MainActivity)
                Log.d("TAG: onResponse", "onResponse: " + response.body())


            }

            override fun onFailure(call: Call<MyData?>, t: Throwable) {
                // if the API is not success because of internet or host error, this method is executed
                Log.d("TAG: onFailure", "onFailure: " + t.message)
            }
        })

    }
}