package com.akpdev.dailyvita.onboarding

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.akpdev.dailyvita.databinding.FragmentOnboardBinding

class FragmentOnBoarding:Fragment() {
    private lateinit var binding:FragmentOnboardBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return FragmentOnboardBinding.inflate(inflater).also {
            binding  = it
        }.root
    }

}