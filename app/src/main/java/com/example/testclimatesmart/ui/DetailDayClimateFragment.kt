package com.example.testclimatesmart.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.testclimatesmart.R
import com.example.testclimatesmart.databinding.FragmentClimateBinding
import com.example.testclimatesmart.databinding.FragmentDetailDayClimateBinding


class DetailDayClimateFragment : Fragment(R.layout.fragment_detail_day_climate) {
    // TODO: Rename and change types of parameters

    private lateinit var binding: FragmentDetailDayClimateBinding
    private var dt: Int? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let { bundle ->
            dt = bundle.getInt("Dt")
        }


    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentDetailDayClimateBinding.bind(view)

    }


}