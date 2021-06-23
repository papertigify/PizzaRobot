package com.example.robotdeliveryman.ui


import android.os.Bundle
import android.util.Log
import androidx.core.view.isVisible
import androidx.lifecycle.ViewModelProvider
import com.example.robotdeliveryman.databinding.ActivityMainBinding
import com.example.robotdeliveryman.ui.viewmodel.MainViewModel
import com.example.robotdeliveryman.ui.viewmodel.ViewModelProviderFactory
import com.example.robotdeliveryman.utils.Resource
import dagger.android.support.DaggerAppCompatActivity
import javax.inject.Inject


class MainActivity : DaggerAppCompatActivity() {

    @Inject
    lateinit var viewModelProviderFactory: ViewModelProviderFactory
    lateinit var viewModel: MainViewModel

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        viewModel = ViewModelProvider(this, viewModelProviderFactory)
            .get(MainViewModel::class.java)

        binding.buttonFind.setOnClickListener {
            val inputString = binding.editText.text.toString()
            viewModel.getRoute(inputString)
        }

        viewModel.route.observe(this){
            when(it.status){
                Resource.Status.LOADING -> binding.progressBar.isVisible = true
                Resource.Status.SUCCESS -> {
                    binding.progressBar.isVisible = false
                    binding.textViewOutput.text = it.data.toString()
                }
                Resource.Status.ERROR -> {
                    binding.progressBar.isVisible = false
                    Log.e(TAG, "An error occurred, please, try again")
                }
            }
        }
    }
}

private const val TAG = "MainActivity"












