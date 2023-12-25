package com.mneybox.calculator.x.ui.home

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import com.mneybox.calculator.x.R
import com.mneybox.calculator.x.databinding.FragmentHomeBinding
import com.mneybox.calculator.x.ui.dashboard.BoxAdapter
import java.time.LocalDate
import kotlin.math.max

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    var months = mutableListOf("January","February","March","April","May","June","July","August","September","October","November","December")
    lateinit var prefs: SharedPreferences
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        prefs = requireActivity().getSharedPreferences("prefs",Context.MODE_PRIVATE)
        val name = prefs.getString("name","")
        binding.textView.text = "Hi , $name"
        val now = LocalDate.now()
        binding.textView4.text = "${now.dayOfMonth}, ${months[max(0,now.month.value-1)]}"
        binding.textView3.setOnClickListener {
            val navController = Navigation.findNavController(requireActivity(),R.id.nav_host_fragment_activity_main2)
            navController.navigate(R.id.fragmentTransaction)
        }
        binding.imageView6.setOnClickListener {
            val navController = Navigation.findNavController(requireActivity(),R.id.nav_host_fragment_activity_main2)
            navController.navigate(R.id.calculatorFragment)
        }
        val list = requireContext().getSharedPreferences("prefs",Context.MODE_PRIVATE).getStringSet("moneybox",HashSet<String>())!!.toMutableList()
        val adapter = BoxAdapter(list.take(3).toMutableList(),requireContext()) {
            val navController = Navigation.findNavController(requireActivity(), R.id.nav_host_fragment_activity_main2)
            navController.navigate(R.id.boxFragment,Bundle().apply { putString("box",it) })
        }
        binding.first.adapter = adapter
        return binding.root
    }

    override fun onResume() {
        val list = prefs.getStringSet("trans",HashSet<String>())!!.toMutableList()
        val ind = prefs.getInt("currency",0)
        val cur = if(ind==0) "€" else "$"
        var balance = 0
        list.forEach {
            balance += it.split("|")[1].toInt()
        }
        binding.textView2.text = "$balance $cur"
        val list1 = requireContext().getSharedPreferences("prefs",Context.MODE_PRIVATE).getStringSet("moneybox",HashSet<String>())!!.toMutableList()
        var active = 0
        var comp = 0
        for(i in list1.indices) {
            var sum = 0
            requireContext().getSharedPreferences("prefs",Context.MODE_PRIVATE).getStringSet(list1[i].split("|")[3],HashSet<String>())!!.forEach { sum+=  it.split("|")[1].toInt() }
            if(sum>=list1[i].split("|")[4].toInt()) comp++
            else active++
        }
        binding.textView11.text = active.toString()
        binding.textView12.text = comp.toString()
        super.onResume()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}