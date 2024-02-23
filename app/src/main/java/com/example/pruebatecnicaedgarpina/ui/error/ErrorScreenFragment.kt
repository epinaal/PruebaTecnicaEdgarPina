package com.example.pruebatecnicaedgarpina.ui.error

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.pruebatecnicaedgarpina.databinding.ErrorScreenHandleBinding

class ErrorScreenFragment : Fragment() {

    private lateinit var binding: ErrorScreenHandleBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = ErrorScreenHandleBinding.inflate(inflater, container, false)
        return binding.root
    }
}