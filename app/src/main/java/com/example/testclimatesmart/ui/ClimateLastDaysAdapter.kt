package com.example.testclimatesmart.ui

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.testclimatesmart.core.BaseViewHolder
import com.example.testclimatesmart.data.DayClimate
import com.example.testclimatesmart.databinding.ClimateDayRowBinding

class ClimateLastDaysAdapter(
    private val listDays: List<String>,
    private val onDayClimateClickListener: ClimateDayAdapter.OnDayClimateClickListener,
    private val listDayClimate: List<DayClimate>
) : RecyclerView.Adapter<BaseViewHolder<*>>() {

    private lateinit var adapterClimateDay: ClimateDayAdapter

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder<*> {
        val itemBinding =
            ClimateDayRowBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        val holder = ClimateDayViewHolder(itemBinding, parent.context)
        return holder;
    }

    override fun onBindViewHolder(holder: BaseViewHolder<*>, position: Int) {
        when (holder) {
            is ClimateDayViewHolder -> holder.bind(listDays[position])
        }
    }

    override fun getItemCount(): Int = listDays.size


    inner class ClimateDayViewHolder(var binding: ClimateDayRowBinding, var context: Context) :
        BaseViewHolder<String>(binding.root) {

        override fun bind(item: String) {

            adapterClimateDay = ClimateDayAdapter(listDayClimate.filter { x -> x.dt_txt_short == item }, onDayClimateClickListener,"http://openweathermap.org/img/wn/10d@2x.png")
            binding.rcvClimateDay.adapter = adapterClimateDay
            binding.tvTitleDay.text = item.toString()
        }
    }


}