package com.akpdev.dailyvita.healthConcerns

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.text.parseAsHtml
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.akpdev.dailyvita.databinding.FragmentHealthConcernSelectionBinding
import com.akpdev.dailyvita.util.createChip
import java.util.Collections


class HealthConcernsSelectionFragment : Fragment() {
    private val viewModel by viewModels<HealthConcernsViewModel>()
    private var _binding: FragmentHealthConcernSelectionBinding? = null
    val binding: FragmentHealthConcernSelectionBinding
        get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return FragmentHealthConcernSelectionBinding.inflate(inflater).also {
            _binding = it
        }.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val labelText = "Select the top health concerns." // Replace with your actual label
        val requiredLabel = "$labelText <font color='red'>*</font>"
        binding.tvTitle.text = requiredLabel.parseAsHtml()

        viewModel.getHealthConcernsData(requireActivity().applicationContext)

        viewModel.healthConcerns.observe(viewLifecycleOwner) {
            binding.chipGpHealthConcerns.removeAllViews()
            for (item in it) {
                val chip = requireContext().createChip(item.name)
                chip.id = item.id.toInt()
                chip.isChecked = item.isSelected
                binding.chipGpHealthConcerns.addView(chip)
            }
        }

        binding.chipGpHealthConcerns.setOnCheckedStateChangeListener { group, checkedIds ->
            viewModel.addPrioritizeData(checkedIds.map { it.toString() })
        }

        val adapter = PrioritizeRecyclerAdapter()
        binding.rvPrioritize.adapter = adapter
        viewModel.prioritizeData.observe(viewLifecycleOwner) {
            adapter.submitList(it)
            Log.i("myCustomList", it.toString())
        }
        var draggedItemIndex = 0
        val itemTouchHelper = ItemTouchHelper(object : ItemTouchHelper.Callback() {
            override fun getMovementFlags(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder
            ): Int {
                return makeMovementFlags(
                    ItemTouchHelper.UP or ItemTouchHelper.DOWN,
                    ItemTouchHelper.START or ItemTouchHelper.END
                )
            }

            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                draggedItemIndex = viewHolder.adapterPosition
                val targetIndex = target.adapterPosition

                viewModel.swapPosition(draggedItemIndex, targetIndex)
                adapter.notifyItemMoved(draggedItemIndex, targetIndex)
                return false
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {

            }

        })
        itemTouchHelper.attachToRecyclerView(binding.rvPrioritize)



        binding.btnNext.setOnClickListener {
            findNavController().navigate(HealthConcernsSelectionFragmentDirections.actionHealthConcernsSelectionFragmentToDietSelectionFragment())
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