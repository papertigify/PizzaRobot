package com.example.robotdeliveryman.ui


import android.os.Bundle
import android.util.Log
import android.view.inputmethod.InputMethodManager
import androidx.core.view.isVisible
import androidx.lifecycle.ViewModelProvider
import com.example.robotdeliveryman.R
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
            // option to drop multiple pizza if destination mentioned multiple times
            val shouldDropMultiplePizza = binding.checkBox.isChecked
            viewModel.getRoute(inputString, shouldDropMultiplePizza)

            // close the keyboard
            val inputMethodManager = getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager
            inputMethodManager.hideSoftInputFromWindow(currentFocus?.windowToken, 0)
        }

        // automatically fills EtText with the check string from the task
        binding.buttonFillAutomatically.setOnClickListener {
            binding.editText.setText(getString(R.string.check_string))
        }

        viewModel.route.observe(this){
            when(it.status){
                Resource.Status.LOADING -> {
                    binding.progressBar.isVisible = true
                    binding.textViewRobotRoute.isVisible = false
                    binding.textViewOutput.isVisible = false
                }
                Resource.Status.SUCCESS -> {
                    binding.progressBar.isVisible = false
                    binding.textViewOutput.isVisible = true
                    binding.textViewRobotRoute.isVisible = true

                    binding.textViewOutput.text = it.data.toString()
                }
                Resource.Status.ERROR -> {
                    binding.textViewRobotRoute.isVisible = true
                    binding.textViewOutput.isVisible = true
                    binding.progressBar.isVisible = false
                    binding.textViewOutput.text = it.message
                    // errors are also displayed in the log to match TR
                    Log.e(TAG, it.message ?: "")
                }
            }
        }
    }
}

private const val TAG = "MainActivity"












