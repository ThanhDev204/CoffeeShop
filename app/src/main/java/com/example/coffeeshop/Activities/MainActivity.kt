package com.example.coffeeshop.Activities

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.example.coffeeshop.Adapter.CategoryAdapter
import com.example.coffeeshop.Adapter.PopularAdapter
import com.example.coffeeshop.ViewModel.MainViewModel
import com.example.coffeeshop.databinding.ActivityMainBinding

internal class MainActivity : AppCompatActivity() {
var TAG="zzzzzzzzzzzzzzzzzzzzzzz"
    lateinit var binding: ActivityMainBinding
    private val viewModel = MainViewModel()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        this.enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initBanner()
        initCategory()
        initPopular()
    }

    private fun initBanner() {
        binding.progressBarBanner.visibility = View.VISIBLE
        viewModel.loadBanner().observeForever {
            Glide.with(this@MainActivity)
                .load(it[0].url)
                .into(binding.banner)
            binding.progressBarBanner.visibility = View.GONE
        }
        viewModel.loadBanner()

    }

    private fun initCategory() {
        binding.progressBarCat.visibility = View.VISIBLE
        viewModel.loadCategory().observeForever {
            binding.recyclerViewCat.layoutManager =
                LinearLayoutManager(this@MainActivity, LinearLayoutManager.HORIZONTAL, false);
            binding.recyclerViewCat.adapter = CategoryAdapter(it)
            binding.progressBarCat.visibility = View.GONE
        }
        viewModel.loadCategory()
    }

    private fun initPopular() {
        binding.progressBarPopular.visibility = View.VISIBLE
        viewModel.loadPopular().observeForever {
            binding.recyclerViewPopular.layoutManager =
                GridLayoutManager(this@MainActivity, 2);
            binding.recyclerViewPopular.adapter = PopularAdapter(it)
            binding.progressBarPopular.visibility = View.GONE
        }
        viewModel.loadPopular()
    }

    override fun onPause() {
        super.onPause()
        Log.e("zzzzzzzzzzzzzz", "onPause: Paused", )
    }

    override fun onRestart() {
        super.onRestart()
        Log.e(TAG, "onRestart: on Restart", )
    }

    override fun onResume() {
        super.onResume()
        Log.e(TAG, "onResume: onResume", )
    }
}