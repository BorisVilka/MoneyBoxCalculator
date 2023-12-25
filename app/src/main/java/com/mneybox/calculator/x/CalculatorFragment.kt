package com.mneybox.calculator.x

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import com.mneybox.calculator.x.databinding.FragmentCalculatorBinding
import java.lang.Exception

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [CalculatorFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class CalculatorFragment : Fragment() {
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

    private lateinit var binding: FragmentCalculatorBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentCalculatorBinding.inflate(inflater,container,false)
        val navController = Navigation.findNavController(requireActivity(),R.id.nav_host_fragment_activity_main2)
        binding.imageView3.setOnClickListener {
            navController.popBackStack()
        }
        val ind = requireContext().getSharedPreferences("prefs",Context.MODE_PRIVATE).getInt("currency",0)
        val cur = if(ind==0) "€" else "$"
        binding.currency = cur
        binding.button.setOnClickListener {
           try {
               var count = binding.amount.text.toString().toInt()
               var rate = binding.rate.text.toString().toInt()
               var years = binding.months.text.toString().toInt()
               var over = ((count*rate/100f)*years).toInt()

               binding.textView16.text = "${((count+over)/(years*12f)).toInt()} $cur"
               binding.textView15.text = "${count+over} $cur"
               binding.textView17.text = "$over $cur"

               binding.button1.visibility = View.VISIBLE
               binding.textView8.visibility = View.VISIBLE
               binding.textView9.visibility = View.VISIBLE
               binding.textView10.visibility = View.VISIBLE
               binding.textView15.visibility = View.VISIBLE
               binding.textView16.visibility = View.VISIBLE
               binding.textView17.visibility = View.VISIBLE
           } catch (e: Exception) {
               e.printStackTrace()
           }
        }
        binding.button1.setOnClickListener {
            navController.popBackStack()
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
         * @return A new instance of fragment CalculatorFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            CalculatorFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}