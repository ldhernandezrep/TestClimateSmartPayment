package com.example.testclimatesmart.ui

import android.content.Context
import android.graphics.drawable.Drawable
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import coil.ImageLoader
import coil.decode.SvgDecoder
import coil.load
import coil.request.ImageRequest
import com.example.testclimatesmart.core.BaseViewHolder
import com.example.testclimatesmart.core.Constantes
import com.example.testclimatesmart.data.DayClimate
import com.example.testclimatesmart.databinding.ClimateGeneralRowBinding
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.example.testclimatesmart.R
import java.io.File
import java.security.PrivateKey


class ClimateDayAdapter(
    private val climateDays: List<DayClimate>,
    private val onDayClimateClickListener: OnDayClimateClickListener,
    private val url: String
) : RecyclerView.Adapter<BaseViewHolder<*>>() {


    interface OnDayClimateClickListener {
        fun onDayClimateClick(dayClimate: DayClimate)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<*> {
        val itemBinding =
            ClimateGeneralRowBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        val holder = ClimateGeneralViewHolder(itemBinding, parent.context)

        itemBinding.root.setOnClickListener {
            val position = holder.adapterPosition.takeIf { it != DiffUtil.DiffResult.NO_POSITION }
                ?: return@setOnClickListener
            onDayClimateClickListener.onDayClimateClick(
                climateDays[position]
            )
        }

        return holder;
    }

    override fun onBindViewHolder(holder: BaseViewHolder<*>, position: Int) {
        when (holder) {
            is ClimateDayAdapter.ClimateGeneralViewHolder -> holder.bind(climateDays[position])
        }
    }

    override fun getItemCount(): Int = climateDays.size

    inner class ClimateGeneralViewHolder(
        var binding: ClimateGeneralRowBinding,
        var context: Context
    ) :
        BaseViewHolder<DayClimate>(binding.root) {

        override fun bind(item: DayClimate) {

            binding.tvWeatherDescripcion.text =
                item.weather.filter { x -> x.dt == item.dt }.firstOrNull()?.description ?: ""
            binding.tvHumidity.text = "${item.main.humidity.toString()} %"
            binding.tvTemperatureMain.text = "${item.main.feels_like.toString()}° C"



        }
    }

}

private fun ImageView.loadUrl(url: String) {

    val imageLoader = ImageLoader.Builder(this.context)
        .componentRegistry { add(SvgDecoder(this@loadUrl.context)) }
        .build()

    val request = ImageRequest.Builder(this.context)
        .crossfade(true)
        .crossfade(100)
        .error(R.drawable.storm)
        .data(url)
        .target(this)
        .build()

    imageLoader.enqueue(request)

}
