package com.tochukwu.mollocurrencyconverterapp.presentation

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.core.view.isVisible
import androidx.lifecycle.lifecycleScope
import com.tochukwu.mollocurrencyconverterapp.R
import com.tochukwu.mollocurrencyconverterapp.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest

//The Activity which displays the result of the conversion

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private val viewModel: CurrencyViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)

        setContentView(binding.root)

        binding.btnConvert.setOnClickListener {
            viewModel.convert(binding.etFrom.text.toString(),
                binding.spFromCurrency.selectedItem.toString(),
                binding.spToCurrency.selectedItem.toString(),
            )
        }

        lifecycleScope.launchWhenStarted{
            viewModel.conversion.collectLatest{event ->

                when(event){
                    is CurrencyViewModel.CurrencyEvent.Success ->{
                        binding.progressBar.isVisible = false
                        binding.tvResult.setTextColor(Color.BLUE)
                        binding.tvResult.text= event.resultText
                    }

                    is CurrencyViewModel.CurrencyEvent.Failure ->{
                        binding.progressBar.isVisible = false
                        binding.tvResult.setTextColor(Color.RED)
                        binding.tvResult.text = event.errorText
                    }

                    is CurrencyViewModel.CurrencyEvent.Loading -> {
                        binding.progressBar.isVisible = true
                    }
                    else -> Unit
                }
            }
        }
    }
}



