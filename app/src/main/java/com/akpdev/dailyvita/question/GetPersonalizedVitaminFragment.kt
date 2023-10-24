package com.akpdev.dailyvita.question

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.akpdev.dailyvita.databinding.FragmentGetPersonalizedVitaminBinding
import timber.log.Timber

class GetPersonalizedVitaminFragment:Fragment() {
    private val viewModel by viewModels<GetPersonalizedVitaminViewModel>()
    private val args by navArgs<GetPersonalizedVitaminFragmentArgs>()
    private var _binding: FragmentGetPersonalizedVitaminBinding? = null
    val binding: FragmentGetPersonalizedVitaminBinding
        get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return FragmentGetPersonalizedVitaminBinding.inflate(inflater).also {
            _binding = it
        }.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnFinish.setOnClickListener {
            val isDailyExposure = if (binding.rbtnQ1Yes.isChecked) true else if (binding.rbtnQ1No.isChecked) false else null
            val isSmoke = if (binding.rbtnQ2Yes.isChecked) true else if (binding.rbtnQ2No.isChecked) false else null
            val alcohol = if (binding.rbtnFirst.isChecked) "0-1" else if (binding.rbtnSecond.isChecked) "2-5" else if (binding.rbtnThird.isChecked) "5+" else ""
            viewModel.answerLastQuestion(isDailyExposure, isSmoke, alcohol)
        }
        viewModel.lastQuestionLiveData.observe(viewLifecycleOwner){
            val healthConcerns = "healthConcerns:${args.selectedHealthConcerns.toList()}\n"
            val diets = "diets:${args.selectedDiets.toList()}\n"
            val allergies = "allergies:${args.selectedAllergies.toList()}\n"
            val isExposure = "isExposure:${it.isDailyExposure?:""}\n"
            val isSmoke = "isSmoke:${it.isSmoke?:""}\n"
            val alcohol = "alcohol:${it.alcohol}\n"
            Timber.i(healthConcerns+diets+allergies+isExposure+isSmoke+alcohol)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}