package com.sergio.examenfinal_damd_pmdm.ui.initial

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import com.sergio.examenfinal_damd_pmdm.R
import com.sergio.examenfinal_damd_pmdm.databinding.ActivityInitialBinding
import com.sergio.examenfinal_damd_pmdm.ui.task.MainActivity

class InitialActivity : AppCompatActivity() {
    private lateinit var _binding: ActivityInitialBinding
    private val binding: ActivityInitialBinding get() = _binding

    private val viewModel: InitialViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityInitialBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnAccess.setOnClickListener {
            saveDepartment()
        }
    }

    private fun saveDepartment() {
        val department = binding.etDepartment.text.toString().trim()
        if (!department.isNullOrEmpty()) {
            viewModel.saveDepartment(this, department)
            goToMainScreen()
        } else {
            Toast.makeText(this, "Campo de texto vacío", Toast.LENGTH_SHORT).show()
        }
    }

    private fun goToMainScreen() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
        finish()
    }
}