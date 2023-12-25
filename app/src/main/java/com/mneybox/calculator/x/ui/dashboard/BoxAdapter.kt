package com.mneybox.calculator.x.ui.dashboard

import android.content.Context
import android.graphics.PorterDuff
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import antonkozyriatskyi.circularprogressindicator.CircularProgressIndicator
import com.mneybox.calculator.x.databinding.MoneyboxItemBinding

class BoxAdapter(val list: MutableList<String>, val context: Context,val callback: (String) -> Unit):
    RecyclerView.Adapter<BoxAdapter.Companion.BoxHolder>() {


    companion object {
        class BoxHolder(val binding: MoneyboxItemBinding): ViewHolder(binding.root)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BoxHolder {
        return BoxHolder(MoneyboxItemBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: BoxHolder, position: Int) {
        val s = list[position]
        val arr = s.split("|")
        holder.binding.icon.setImageResource(arr[1].toInt())
        holder.binding.icon.setColorFilter(context.getColor(arr[2].toInt()), PorterDuff.Mode.SRC_IN)
        holder.binding.textView10.text = arr[3]
        holder.binding.circularProgress.progressColor = context.getColor(arr[2].toInt())
        var sum = 0
        context.getSharedPreferences("prefs",Context.MODE_PRIVATE).getStringSet(arr[3],HashSet<String>())!!.forEach { sum+=  it.split("|")[1].toInt() }
        holder.binding.circularProgress.maxProgress = arr[4].toInt().toDouble()
        holder.binding.circularProgress.setProgress(sum.toDouble(),arr[4].toInt().toDouble())
        holder.binding.root.setOnClickListener {
            callback(list[position])
        }
        holder.binding.circularProgress.setProgressTextAdapter { currentProgress ->
            "${((currentProgress / arr[4].toInt().toDouble()) * 100f).toInt()}%"
        }
        //holder.binding.circularProgress.setProgress(sum.toDouble())
    }
}