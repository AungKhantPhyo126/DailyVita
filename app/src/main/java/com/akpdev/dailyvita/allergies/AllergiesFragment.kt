package com.akpdev.dailyvita.allergies

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.akpdev.dailyvita.databinding.FragmentAllergiesBinding
import com.akpdev.dailyvita.diet.DietSelectionFragmentDirections

class AllergiesFragment:Fragment() {
    private var _binding: FragmentAllergiesBinding? = null
    val binding: FragmentAllergiesBinding
        get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return FragmentAllergiesBinding.inflate(inflater).also {
            _binding = it
        }.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.btnNext.setOnClickListener {
            findNavController().navigate(AllergiesFragmentDirections.actionAllergiesFragmentToGetPersonalizedVitaminFragment())
        }
        binding.btnBack.setOnClickListener {
            findNavController().popBackStack()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}