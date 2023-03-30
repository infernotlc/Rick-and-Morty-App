package com.example.rickam

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: LocationAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // RecyclerView'ı ve LayoutManager'ı tanımla
        recyclerView = findViewById(R.id.locationList)
        recyclerView.layoutManager = LinearLayoutManager(this)

        // Retrofit nesnesini oluştur
        val retrofit = Retrofit.Builder()
            .baseUrl("https://rickandmortyapi.com/api/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        // API arayüzünü oluştur
        val api = retrofit.create(RickAndMortyAPI::class.java)

        // API'den lokasyonları getir
        api.getLocations().enqueue(object : Callback<LocationResponse> {
            override fun onResponse(call: Call<LocationResponse>, response: Response<LocationResponse>) {
                if (response.isSuccessful) {
                    // Adapter'i oluştur ve RecyclerView'a ekle
                    adapter = LocationAdapter(response.body()?.results ?: emptyList())
                    recyclerView.adapter = adapter
                }
            }

            override fun onFailure(call: Call<LocationResponse>, t: Throwable) {
                // Hata durumunda mesaj göster
                Toast.makeText(this@MainActivity, "Hata oluştu: ${t.message}", Toast.LENGTH_SHORT).show()

            }
        })
    }
}
