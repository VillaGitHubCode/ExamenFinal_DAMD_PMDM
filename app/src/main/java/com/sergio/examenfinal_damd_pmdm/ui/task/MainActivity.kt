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

    private val adapter= TaskAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.rvTasks.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        binding.rvTasks.adapter = adapter

    }

}