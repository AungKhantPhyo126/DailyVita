package com.akpdev.dailyvita.healthConcerns

import android.os.Bundle
import android.text.Html
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.akpdev.dailyvita.databinding.FragmentHealthConcernSelectionBinding

class HealthConcernsSelectionFragment :Fragment(){
    private lateinit var binding: FragmentHealthConcernSelectionBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return FragmentHealthConcernSelectionBinding.inflate(inflater).also {
            binding  = it
        }.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val labelText = "Select the top health concerns." // Replace with your actual label
        val requiredLabel = "$labelText <font color='red'>*</font>"

        binding.tvTitle.text = Html.fromHtml(requiredLabel, Html.FROM_HTML_MODE_LEGACY)
    }
}