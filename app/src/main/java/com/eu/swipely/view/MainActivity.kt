package com.eu.swipely.view

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.lifecycleScope
import com.eu.swipely.R
import com.eu.swipely.databinding.ActivityMainBinding
import com.eu.swipely.view.adapter.CharacterAdapter
import com.eu.swipely.view.adapter.LoadingAndErrorStateAdapter
import com.eu.swipely.viewmodel.MainActivityViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val viewModel: MainActivityViewModel by viewModels()
    private val characterAdapter by lazy { CharacterAdapter() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.lifecycleOwner = this
        initUi()
        initObserver()
    }

    private fun initUi() {
        with(binding.rvCards) {
            adapter =
                characterAdapter.withLoadStateFooter(LoadingAndErrorStateAdapter { characterAdapter.retry() })
        }
    }

    private fun initObserver() {
        lifecycleScope.launch {
            viewModel.pagingData.collectLatest(characterAdapter::submitData)
        }
    }
}