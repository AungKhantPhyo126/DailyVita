package com.akpdev.dailyvita.onboarding

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.akpdev.dailyvita.databinding.FragmentOnboardingBinding

class FragmentOnBoarding:Fragment() {
    private var _binding:FragmentOnboardingBinding? = null
    val binding: FragmentOnboardingBinding
        get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return FragmentOnboardingBinding.inflate(inflater).also {
            _binding  = it
        }.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.btnGetStarted.setOnClickListener {
            findNavController().navigate(FragmentOnBoardingDirections.actionFragmentOnBoardingToHealthConcernsSelectionFragment())
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding=null
    }
}