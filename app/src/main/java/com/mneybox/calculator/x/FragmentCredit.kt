package com.mneybox.calculator.x

import android.content.Context
import android.graphics.PorterDuff
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import com.mneybox.calculator.x.databinding.FragmentCreditBinding

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [FragmentCredit.newInstance] factory method to
 * create an instance of this fragment.
 */
class FragmentCredit : Fragment() {
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

    private lateinit var binding: FragmentCreditBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentCreditBinding.inflate(inflater,container,false)
        val s = requireArguments().getString("tmp")!!.split("|")
        binding.icon.setImageResource(s[1].toInt())
        binding.icon.setColorFilter(requireActivity().getColor(s[2].toInt()), PorterDuff.Mode.SRC_IN)
        binding.textView10.text = s[3]
        binding.button.setOnClickListener {
            val tmp = requireArguments().getString("tmp")!!+"${binding.amount.text.toString()}|${binding.months.text.toString()}"
            val set = requireContext().getSharedPreferences("prefs", Context.MODE_PRIVATE).getStringSet("moneybox",HashSet<String>())!!
            val set1 = HashSet<String>()
            set1.addAll(set)
            set1.add(tmp)
            requireContext().getSharedPreferences("prefs", Context.MODE_PRIVATE).edit().putStringSet("moneybox",set1).apply()
            val navController = Navigation.findNavController(requireActivity(), R.id.nav_host_fragment_activity_main2)
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
         * @return A new instance of fragment FragmentCredit.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            FragmentCredit().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}