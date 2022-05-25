package com.example.testclimatesmart.ui

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.bumptech.glide.Glide
import com.example.testclimatesmart.R
import com.example.testclimatesmart.core.ResultsState
import com.example.testclimatesmart.databinding.FragmentClimateBinding
import com.example.testclimatesmart.databinding.FragmentDetailDayClimateBinding
import com.example.testclimatesmart.presentation.DayClimateVIewModel
import com.example.testclimatesmart.presentation.DetailClimateViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailDayClimateFragment : Fragment(R.layout.fragment_detail_day_climate) {
    // TODO: Rename and change types of parameters

    private lateinit var binding: FragmentDetailDayClimateBinding
    private val viewModel: DetailClimateViewModel by viewModels()
    private var dt: Long? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let { bundle ->
            dt = bundle.getLong("Dt")
        }


    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentDetailDayClimateBinding.bind(view)

        viewModel.getClimateDetailByDt(dt ?: 0)
            .observe(viewLifecycleOwner, { x ->
                when (x) {
                    is ResultsState.Loading -> {
                        Log.d("Cargando", "Cargando datos")
                    }
                    is ResultsState.Success -> {
                        binding.tvTemperature.text = "${x.data.main.feels_like.toString()}° C"
                        binding.tvHumedadData.text = "${x.data.main.humidity}%"
                        binding.tvTemperaturaData.text = "${x.data.main.feels_like.toString()}° C"
                        binding.tvPresionData.text = x.data.main.pressure.toString()
                        binding.tvClimateMain.text =
                            x.data.weather.filter { it.dt == dt }.first().description

                        Glide.with(requireContext())
                            .load(x.data.weather.filter { it.dt == dt }.first().icon)
                            .centerCrop()
                            .error(R.drawable.storm)
                            .into(binding.tvIconClimate)


                    }
                    is ResultsState.Failure -> {
                        Log.d("Succes", "usuario ${x.exception.message}")
                    }
                }

            })


        /* binding.imvClimate.load(item.weather.filter { x -> x.dt == item.dt }.first().icon) {
             listener(
                 // pass two arguments
                 onSuccess = { _, _ ->

                 },
                 onError = { request: ImageRequest, throwable: Throwable ->
                     request.error
                     Log.d("Error image", throwable.message.toString())
                 })
             // setup error image
             error(R.drawable.storm)

         }*/


    }


}