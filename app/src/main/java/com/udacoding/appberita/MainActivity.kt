package com.udacoding.appberita

import android.content.Context
import android.net.ConnectivityManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import com.udacoding.appberita.adapter.BeritaAdapter
import com.udacoding.appberita.model.Berita
import com.udacoding.appberita.model.ResponseServer
import com.udacoding.appberita.network.ConfigNetwork
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if(isConnect()) {


            ConfigNetwork.getRetrofit().getDataBerita().enqueue(object : Callback<ResponseServer> {
                override fun onResponse(
                    call: Call<ResponseServer>,
                    response: Response<ResponseServer>
                ) {
                    Log.d("Response Server", response.message())
                    if (response.isSuccessful) {
                        val status = response.body()?.status
                        progress.visibility = View.GONE
                        if (status == "ok") {
                            val articles = response.body()?.articles

                            showArticles(articles)
                        }
                        else{
                            Log.d("no data","no data")
                        }

                    }

                }

                override fun onFailure(call: Call<ResponseServer>, t: Throwable) {
                    Log.d("Error Connection", t.message)
                    progress.visibility = View.GONE
                }
            })
        }else{
            progress.visibility = View.GONE
            Toast.makeText(this,"No Internet Connection", Toast.LENGTH_SHORT).show()
        }
    }

    fun isConnect() : Boolean{
        val connect : ConnectivityManager = getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        return connect.activeNetworkInfo != null && connect.activeNetworkInfo.isConnected
    }

    private fun showArticles(articles: ArrayList<Berita>?) {
        listBerita.adapter = BeritaAdapter(articles)

    }
}