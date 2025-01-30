package com.example.material3navigationdrawer

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.fragment.app.viewModels
import com.example.material3navigationdrawer.databinding.FragmentSettingBinding
import com.example.material3navigationdrawer.utils.LocaleHelper
import com.example.material3navigationdrawer.utils.SharedPrefManager
import com.example.material3navigationdrawer.viewmodel.MainViewModel
import com.example.material3navigationdrawer.viewmodel.MainViewModelFactory

class SettingFragment : Fragment() {

    private var _binding: FragmentSettingBinding? = null
    private val binding get() = _binding!!

    private val viewModel: MainViewModel by viewModels {
        MainViewModelFactory(SharedPrefManager(requireContext()))
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSettingBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnEnglish.setOnClickListener { changeLanguage("en") }
        binding.btnArabic.setOnClickListener { changeLanguage("ar") }
        binding.btnKhmer.setOnClickListener { changeLanguage("km") }
    }

//    private fun changeLanguage(lang: String) {
//        viewModel.setLanguage(lang)
//        LocaleHelper.setLocale(requireContext(), lang)
//        activity?.recreate() // Restart activity to apply changes
//    }

//    private fun changeLanguage(lang: String) {
//        viewModel.setLanguage(lang)
//        LocaleHelper.setLocale(requireContext(), lang)
//
//        requireActivity().recreate()
//    }

    private fun changeLanguage(lang: String) {
        viewModel.setLanguage(lang)
        LocaleHelper.setLocale(requireContext(), lang)

        val intent = requireActivity().intent
        requireActivity().finish()
        requireActivity().startActivity(intent)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}