package com.akpdev.dailyvita.allergies

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.akpdev.dailyvita.R
import com.akpdev.dailyvita.databinding.FragmentAllergiesBinding
import com.akpdev.dailyvita.util.createChip
import com.akpdev.dailyvita.util.showDropdown

import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AllergiesFragment : Fragment() {
    private var _binding: FragmentAllergiesBinding? = null
    private val viewModel by viewModels<AllergiesViewModel>()
    private val args by navArgs<AllergiesFragmentArgs>()

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
        viewModel.getAllergiesData(requireActivity().applicationContext)
        viewModel.allergiesLiveData.observe(viewLifecycleOwner) { allergyList ->
            val unSelectedAllergies = allergyList.filter { !it.isSelected }
            val arrayAdapter = ArrayAdapter(requireContext(), R.layout.item_drop_down_text,unSelectedAllergies.map { it.name } )
            binding.actAllergy.setAdapter(arrayAdapter)
            binding.actAllergy.setOnClickListener {
                binding.actAllergy.showDropdown(arrayAdapter)
            }
            binding.actAllergy.setOnItemClickListener() { parent, _, position, _ ->
                val selectedItem = parent.getItemAtPosition(position).toString()
                viewModel.checkChangeDietData(selectedItem)

                binding.actAllergy.text.clear()
            }
            val selectedAllergies = allergyList.filter { it.isSelected }
            binding.btnNext.isEnabled = selectedAllergies.isEmpty()

            if (selectedAllergies.isEmpty().not()){
                binding.chipContainer.removeAllViews()
               selectedAllergies.forEach {
                   val chip  = requireContext().createChip(it.name)
                   binding.chipContainer.addView(chip)
               }
            }
        }
        binding.btnNext.setOnClickListener {
            findNavController().navigate(AllergiesFragmentDirections.actionAllergiesFragmentToGetPersonalizedVitaminFragment(
                args.selectedHealthConcerns,
                args.selectedDiets,
                viewModel.allergiesLiveData.value?.filter { it.isSelected }?.map { it.name }.orEmpty().toTypedArray()
            ))
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