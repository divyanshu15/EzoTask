package com.example.ezotask

import android.app.ProgressDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.bumptech.glide.Glide
import com.example.ezotask.databinding.ActivityMainBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.btn.setOnClickListener {
            getData()
        }

    }

    private fun getData() {

        RetrofitInstance.apiInterface.getData().enqueue(object : Callback<ResponseDataClass?> {
            override fun onResponse(
                call: Call<ResponseDataClass?>,
                response: Response<ResponseDataClass?>
            ) {
                val authorname= response.body()!![0]?.author
                binding.authorname.text="Author : "+authorname
                Glide.with(this@MainActivity).load(response.body()!![0]?.download_url).into(binding.authorimg)
            }

            override fun onFailure(call: Call<ResponseDataClass?>, t: Throwable) {
                Toast.makeText(this@MainActivity,"Unable to get response from server",Toast.LENGTH_SHORT).show()
            }
        })
    }
}