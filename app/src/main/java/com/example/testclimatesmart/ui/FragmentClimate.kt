package com.example.testclimatesmart.ui

import android.Manifest
import android.content.pm.PackageManager
import android.location.Location
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.View
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.os.bundleOf
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.testclimatesmart.R
import com.example.testclimatesmart.core.Constantes
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
                llenarRecylcerView(true)
            } else {
                llenarRecylcerView(false)
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

    private fun llenarRecylcerView(permiso: Boolean) {
        if (permiso) {
            fusedLocationClient = LocationServices.getFusedLocationProviderClient(activity)
            fusedLocationClient.lastLocation
                .addOnSuccessListener { location: Location? ->
                    var lat: Double = location?.latitude ?: Constantes.LAT_DEFAULT
                    var lon: Double = location?.longitude ?: Constantes.LON_DEFAULT
                    LlenarViewModel(lat, lon)
                }
        } else {
            LlenarViewModel(Constantes.LAT_DEFAULT, Constantes.LON_DEFAULT)
        }

    }

    private fun ObtenerDistintosDias(data: List<DayClimate>): List<String> {
        var listDays = mutableListOf<String>()
        data.forEach { listDays.add(it.dt_txt_short) }
        return listDays.distinct()
    }

    private fun LlenarViewModel(lat: Double, lon: Double) {
        viewModel.getClimateLastDays(lat, lon, "metric")
            .observe(viewLifecycleOwner, { x ->
                when (x) {
                    is ResultsState.Loading -> {
                        binding.llProgressBar.root.visibility = View.VISIBLE
                        binding.rcvClimateDay.visibility = View.GONE
                        Log.d("Cargando", "Cargando datos")
                    }
                    is ResultsState.Success -> {

                        if (x.data.size > 0) {
                            var listString: List<String> = ObtenerDistintosDias(x.data)
                            climateLastDaysAdapter =
                                ClimateLastDaysAdapter(listString, this, x.data)
                            binding.rcvClimateDay.adapter = climateLastDaysAdapter
                        } else {
                            Toast.makeText(
                                requireContext(),
                                "No hay datos necesitas tener conexion a Internet.",
                                Toast.LENGTH_LONG
                            ).show()
                        }

                        binding.llProgressBar.root.visibility = View.GONE
                        binding.rcvClimateDay.visibility = View.VISIBLE

                    }
                    is ResultsState.Failure -> {
                        binding.llProgressBar.root.visibility = View.GONE
                        binding.rcvClimateDay.visibility = View.VISIBLE
                        Toast.makeText(
                            requireContext(),
                            "Ocurrio un error en la carga de datos",
                            Toast.LENGTH_LONG
                        ).show()
                        Log.d("Succes", "usuario ${x.exception.message}")
                    }
                }

            })
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
                llenarRecylcerView(true)
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
        findNavController().navigate(
            R.id.action_fragmentClimate_to_detailDayClimateFragment,
            bundleOf(
                "Dt" to dayClimate.dt
            )

        )
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