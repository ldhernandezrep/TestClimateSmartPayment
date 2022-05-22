package com.example.testclimatesmart.ui

import android.Manifest
import android.content.pm.PackageManager
import android.location.Location
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.View
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.viewModels
import com.example.testclimatesmart.R
import com.example.testclimatesmart.core.ResultsState
import com.example.testclimatesmart.data.DayClimate
import com.example.testclimatesmart.databinding.FragmentClimateBinding
import com.example.testclimatesmart.presentation.DayClimateVIewModel
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint


/**
 * A simple [Fragment] subclass.
 * Use the [FragmentClimate.newInstance] factory method to
 * create an instance of this fragment.
 */

@AndroidEntryPoint
class FragmentClimate : Fragment(R.layout.fragment_climate),
    ClimateDayAdapter.OnDayClimateClickListener {


    private lateinit var binding: FragmentClimateBinding
    private val viewModel: DayClimateVIewModel by viewModels()
    private lateinit var fusedLocationClient: FusedLocationProviderClient
    private lateinit var climateLastDaysAdapter: ClimateLastDaysAdapter

    private val requestPermissionLauncher =
        registerForActivityResult(
            ActivityResultContracts.RequestPermission()
        ) { isGranted: Boolean ->
            if (isGranted) {
                llenarRecylcerView()
            } else {
                Log.i("Permission: ", "Denied")
            }
        }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentClimateBinding.bind(view)
        onClickRequestPermission(view)
    }

    private fun llenarRecylcerView() {
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(activity)
        fusedLocationClient.lastLocation
            .addOnSuccessListener { location: Location? ->
                var lat: Double = location?.latitude ?: 19.300
                var lon: Double = location?.longitude ?: -99.0844
                viewModel.getClimateLastDays(lat, lon, "metric")
                    .observe(viewLifecycleOwner, { x ->
                        when (x) {
                            is ResultsState.Loading -> {
                                Log.d("Cargando", "Cargando datos")
                            }
                            is ResultsState.Success -> {

                                var listString: List<String> = ObtenerDistintosDias(x.data)

                                climateLastDaysAdapter =
                                    ClimateLastDaysAdapter(listString, this, x.data)
                                binding.rcvClimateDay.adapter = climateLastDaysAdapter

                            }
                            is ResultsState.Failure -> {
                                Log.d("Succes", "usuario ${x.exception.message}")
                            }
                        }

                    })
            }
    }

    private fun ObtenerDistintosDias(data: List<DayClimate>): List<String> {
        var listDays = mutableListOf<String>()
        data.forEach { listDays.add(it.dt_txt_short) }
        return listDays.distinct()
    }

    fun onClickRequestPermission(view: View) {
        when {
            activity?.let {
                ContextCompat.checkSelfPermission(
                    it,
                    Manifest.permission.ACCESS_FINE_LOCATION
                )
            } == PackageManager.PERMISSION_GRANTED -> {
                binding.root.showSnackbar(
                    view,
                    "Permiso concedido",
                    Snackbar.LENGTH_LONG,
                    null
                ) {

                }
                llenarRecylcerView()
            }

            ActivityCompat.shouldShowRequestPermissionRationale(
                activity!!,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) -> {
                binding.root.showSnackbar(
                    view,
                    "Permiso requerido",
                    Snackbar.LENGTH_INDEFINITE,
                    "OK"
                ) {
                    requestPermissionLauncher.launch(
                        Manifest.permission.ACCESS_FINE_LOCATION
                    )
                }
            }

            else -> {
                requestPermissionLauncher.launch(
                    Manifest.permission.ACCESS_FINE_LOCATION
                )
            }
        }
    }

    override fun onDayClimateClick(dayClimate: DayClimate) {
        TODO("Not yet implemented")
    }


}

fun View.showSnackbar(
    view: View,
    msg: String,
    length: Int,
    actionMessage: CharSequence?,
    action: (View) -> Unit
) {
    val snackbar = Snackbar.make(view, msg, length)
    if (actionMessage != null) {
        snackbar.setAction(actionMessage) {
            action(this)
        }.show()
    } else {
        snackbar.show()
    }
}