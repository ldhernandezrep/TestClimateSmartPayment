package com.example.testclimatesmart.ui

import android.content.Context
import android.graphics.drawable.Drawable
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide.with
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.example.testclimatesmart.core.BaseViewHolder
import com.example.testclimatesmart.core.Constantes
import com.example.testclimatesmart.data.DayClimate
import com.example.testclimatesmart.databinding.ClimateDayRowBinding
import com.example.testclimatesmart.databinding.ClimateGeneralRowBinding
import com.bumptech.glide.Glide
import com.example.testclimatesmart.R


class ClimateDayAdapter(
    private val climateDays: List<DayClimate>,
    private val onDayClimateClickListener: OnDayClimateClickListener
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

            var url: String =
                Constantes.URL_IMAGE_BASE + item.weather.filter { x -> x.dt == item.dt }
                    .first().icon + Constantes.EXTENSION

            binding.tvTemperature.text =
                item.weather.filter { x -> x.dt == item.dt }.first().description

            //binding.imvClimate.setImageResource(R.drawable.storm)

            Log.d("URL INAGE", url)

            var urlImage = url

            Glide.with(context)
                .load(Constantes.URL_IMAGE_BASE + item.weather.filter { x -> x.dt == item.dt }
                    .first().icon + Constantes.EXTENSION)
                .centerCrop()
                .diskCacheStrategy(DiskCacheStrategy.RESOURCE)
                .skipMemoryCache(true)
                .error(R.drawable.storm)
                .into(binding.imvClimate)

        }
    }

}