package ru.startandroid.develop.study_recycleview

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import kotlin.random.Random

class MainActivity : AppCompatActivity() {
    private val imdbBaseUrl = "https://tv-api.com"
    private val retrofit2 = Retrofit.Builder()
            .baseUrl(imdbBaseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .build()

    private val imdbService = retrofit2.create(ImdbApi::class.java)

    private lateinit var searchButton: Button
    private lateinit var queryInput: EditText
    private lateinit var placeholderMessage: TextView
    private lateinit var locationsList: RecyclerView
    private val infos = ArrayList<MovieInfo>()

    private val adapter = LocationsAdapter()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        placeholderMessage = findViewById(R.id.placeholderMessage)
        searchButton = findViewById(R.id.searchButton)
        queryInput = findViewById(R.id.queryInput)
        locationsList = findViewById(R.id.locations)

        adapter.infos = infos

        locationsList.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)

        locationsList.adapter = adapter

        searchButton.setOnClickListener {if (queryInput.text.isNotEmpty())
            search()
        }


    }

    private fun showMessage(message:String, additionalMessage:String){
        if(message.isNotEmpty()){
            placeholderMessage.visibility = View.VISIBLE
            infos.clear()
            adapter.notifyDataSetChanged()
            placeholderMessage.text = message
            if (additionalMessage.isNotEmpty()){
                Toast.makeText(applicationContext, additionalMessage, Toast.LENGTH_SHORT).show()
            }
        }
    }
    private fun search() {
        imdbService.getLocations(queryInput.text.toString())
            .enqueue(object : Callback<ImdbResponse> {
                override fun onResponse(
                    call: Call<ImdbResponse>, response: Response<ImdbResponse>) {
                    when (response.code()) {
                        200 -> {
                            if (response.body()?.results?.isNotEmpty() == true) {
                                infos.clear()
                                infos.addAll(response.body()?.results!!)
                                adapter.notifyDataSetChanged()
                            } else {
                                showMessage(getString(R.string.nothing_found), "")
                            }

                        }

                        else -> showMessage(
                            getString(R.string.something_went_wrong), response.code().toString()
                        )
                    }
                }

                override fun onFailure(call: Call<ImdbResponse>, t: Throwable) {
                    showMessage(getString(R.string.something_went_wrong), t.message.toString())
                }

            })
    }
}