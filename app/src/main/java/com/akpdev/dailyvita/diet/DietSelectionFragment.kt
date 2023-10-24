package com.akpdev.dailyvita.diet

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.findViewTreeLifecycleOwner
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.akpdev.dailyvita.R
import com.akpdev.dailyvita.databinding.FragmentDietSelectionBinding
import com.skydoves.balloon.ArrowOrientation
import com.skydoves.balloon.ArrowOrientationRules
import com.skydoves.balloon.ArrowPositionRules
import com.skydoves.balloon.Balloon
import com.skydoves.balloon.BalloonAnimation
import com.skydoves.balloon.BalloonSizeSpec
import com.skydoves.balloon.showAlignEnd
import com.skydoves.balloon.showAlignStart
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DietSelectionFragment : Fragment() {
    private val viewModel by viewModels<DietSelectionViewModel>()
    private val args by navArgs<DietSelectionFragmentArgs>()
    private var _binding: FragmentDietSelectionBinding? = null
    val binding: FragmentDietSelectionBinding
        get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return FragmentDietSelectionBinding.inflate(inflater).also {
            _binding = it
        }.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val adapter = DietRecyclerAdapter({toolTips,view ->
            val balloon = Balloon.Builder(binding.root.context)
                .setWidthRatio(0.5f)
                .setHeight(BalloonSizeSpec.WRAP)
                .setText(toolTips)
                .setTextColorResource(R.color.dark_black)
                .setTextSize(15f)
                .setArrowPositionRules(ArrowPositionRules.ALIGN_ANCHOR) // Align the arrow with the anchor view
                .setArrowOrientation(ArrowOrientation.START) // Set the arrow at the bottom
                .setArrowSize(10)
                .setArrowPosition(0.5f)
                .setPadding(12)
                .setCornerRadius(8f)
                .setBackgroundColorResource(R.color.white)
                .setBalloonAnimation(BalloonAnimation.ELASTIC)
                .setLifecycleOwner(viewLifecycleOwner)
                .build()
            balloon.showAlignEnd(view)
        },{id->
            viewModel.checkChangeDietData(id)
        })

        binding.rvDiets.adapter = adapter
        viewModel.getDietData(requireActivity().applicationContext)

        viewModel.dietLiveData.observe(viewLifecycleOwner) {
            binding.btnNext.isEnabled = it.any { it.isSelected }
            adapter.submitList(it)
        }

        binding.btnNext.setOnClickListener {
            findNavController().navigate(DietSelectionFragmentDirections.actionDietSelectionFragmentToAllergiesFragment(
                args.selectedHealthConcerns,
                viewModel.dietLiveData.value?.filter { it.isSelected }?.map { it.name }.orEmpty().toTypedArray()))
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