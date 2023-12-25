package com.mneybox.calculator.x

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import com.mneybox.calculator.x.databinding.FragmentTransactionBinding
import java.time.LocalDate
import java.time.format.DateTimeFormatter

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [FragmentTransaction.newInstance] factory method to
 * create an instance of this fragment.
 */
class FragmentTransaction : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    private lateinit var binding: FragmentTransactionBinding
    private lateinit var preferences: SharedPreferences

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentTransactionBinding.inflate(inflater,container,false)
        binding.imageView3.setOnClickListener {
            val navController = Navigation.findNavController(requireActivity(),R.id.nav_host_fragment_activity_main2)
            navController.popBackStack()
        }
        var balance = 0
        preferences = requireActivity().getSharedPreferences("prefs", Context.MODE_PRIVATE)
        val ind = requireContext().getSharedPreferences("prefs",Context.MODE_PRIVATE).getInt("currency",0)
        val cur = if(ind==0) "€" else "$"
        val formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy")
        val list = preferences.getStringSet("trans",HashSet<String>())!!.toMutableList()
        list.sortByDescending { LocalDate.from(formatter.parse(it.split("|")[0])) }
        list.forEach {
            balance += it.split("|")[1].toInt()
        }
        binding.textView2.text = "$balance $cur"
        val adapter = TransactionAdapter(list,requireContext())
        binding.history.adapter = adapter
        binding.add.setOnClickListener {
            val dialog = AddDialog(true) {
                val now = LocalDate.now()
                list.add(0,"${formatter.format(now)}|$it")
                adapter.notifyItemInserted(0)
                preferences.edit()
                    .putStringSet("trans",list.toSet()).apply()
                balance += it
                binding.textView2.text = "$balance $cur"
            }
            dialog.show(requireActivity().supportFragmentManager,"ADD")
        }
        binding.minus.setOnClickListener {
            val dialog = AddDialog(false) {
                val now = LocalDate.now()
                list.add(0,"${formatter.format(now)}|$it")
                adapter.notifyItemInserted(0)
                preferences.edit()
                    .putStringSet("trans",list.toSet()).apply()
                balance += it
                binding.textView2.text = "$balance $cur"
            }
            dialog.show(requireActivity().supportFragmentManager,"ADD")
        }
        return binding.root
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment FragmentTransaction.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            FragmentTransaction().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}