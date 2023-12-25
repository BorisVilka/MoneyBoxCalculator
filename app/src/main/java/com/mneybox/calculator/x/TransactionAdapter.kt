package com.mneybox.calculator.x

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.mneybox.calculator.x.databinding.TransactionItemBinding
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import kotlin.math.abs
import kotlin.math.max

class TransactionAdapter(val list: MutableList<String>, val context: Context): RecyclerView.Adapter<TransactionAdapter.Companion.TransHolder>() {

    var months = mutableListOf("January","February","March","April","May","June","July","August","September","October","November","December")
    val ind = context.getSharedPreferences("prefs",Context.MODE_PRIVATE).getInt("currency",0)
    val cur = if(ind==0) "€" else "$"

    companion object {
        class TransHolder(val binding: TransactionItemBinding): ViewHolder(binding.root)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TransHolder {
        return TransHolder(TransactionItemBinding.inflate(LayoutInflater.from(parent.context),parent,false))
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: TransHolder, position: Int) {
        val s  = list[position]
        val formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy")
        val date = LocalDate.from(formatter.parse(s.split("|")[0]))
        holder.binding.textView9.text = "${months[max(0,date.month.value-1)].substring(0,3)} ${date.dayOfMonth}, ${date.year}"
        val sum = s.split("|")[1].toInt()
        holder.binding.textView10.text = if(sum>0) "+${sum} $cur" else "-${abs(sum)} $cur"
        holder.binding.textView10.setTextColor(context.getColor(if(sum>0) R.color.green else R.color.red))
    }
}