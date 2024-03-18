package com.sergio.examenfinal_damd_pmdm.ui.task

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.sergio.examenfinal_damd_pmdm.databinding.ActivityMainBinding
import com.sergio.examenfinal_damd_pmdm.ui.adapter.TaskAdapter
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    private lateinit var _binding: ActivityMainBinding
    private val binding: ActivityMainBinding get() = _binding

    private val viewModel: MainViewModel by viewModels()
    private val adapter= TaskAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.rvTasks.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        binding.rvTasks.adapter = adapter

        viewModel.getDepartment(this)
        observeViewModelStates()
    }

    private fun observeViewModelStates() {

        lifecycleScope.launch {
            viewModel.department.collect{ department ->
                if (department != null) {
                    viewModel.getTask(department)
                }
            }
        }

        lifecycleScope.launch {

            viewModel.mainState.collect{ homeState ->
                if (homeState.response != null) {
                    adapter.submitList(homeState.response)
                }
                if (homeState.isLoading) {
                    binding.pbMain.visibility = View.VISIBLE
                } else {
                    binding.pbMain.visibility = View.GONE
                }

                if (homeState.isError) {
                    binding.tvErrorRequest.visibility = View.VISIBLE
                } else{
                    binding.tvErrorRequest.visibility = View.GONE
                }
            }
        }

    }

}