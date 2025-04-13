package com.example.coffeeshop.Activities

import android.os.Bundle
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.coffeeshop.Adapter.ItemsListCategoryAdapter
import com.example.coffeeshop.R
import com.example.coffeeshop.ViewModel.MainViewModel
import com.example.coffeeshop.databinding.ActivityItemListBinding

class ItemListActivity : AppCompatActivity() {
    lateinit var  binding:ActivityItemListBinding
    private  val viewModel=MainViewModel()
    private var id:String =""
    private  var title:String=""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_item_list)
      binding=ActivityItemListBinding.inflate(layoutInflater)
        setContentView(binding.root)
        getBundle()
        initList()
    }

    private fun initList() {
        binding.apply {
            progressBar2.visibility= View.VISIBLE
            viewModel.loadItems(id).observe(this@ItemListActivity, Observer {
                listView.layoutManager=
                    LinearLayoutManager(this@ItemListActivity,LinearLayoutManager.VERTICAL,false)
                listView.adapter=ItemsListCategoryAdapter(it)
                progressBar2.visibility=View.GONE
            })
            backBtn.setOnClickListener {
                finish()
            }
        }
    }

    private fun getBundle() {
        id= intent.getStringExtra("id")!!
        title=intent.getStringExtra("title")!!
        binding.categoryTxt.text=title
    }
}