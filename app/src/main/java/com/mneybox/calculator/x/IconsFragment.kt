package com.mneybox.calculator.x

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import com.mneybox.calculator.x.databinding.FragmentIconsBinding

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [IconsFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class IconsFragment : Fragment() {
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

    private lateinit var binding: FragmentIconsBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentIconsBinding.inflate(inflater,container,false)
        val navController = Navigation.findNavController(requireActivity(),R.id.nav_host_fragment_activity_main2)
        val list = mutableListOf(binding.i1,binding.i2,binding.i3,binding.i4,binding.i5,binding.i6,binding.i7,binding.i8,binding.i9,binding.i10,binding.i11,binding.i12,
            binding.i13,binding.i14,binding.i15,binding.i16,binding.i17,binding.i18,)
        val id = mutableListOf(R.drawable.i1,R.drawable.i2,R.drawable.i3,R.drawable.i4,R.drawable.i5,R.drawable.i6,R.drawable.i7,R.drawable.i8,R.drawable.i9,
            R.drawable.i10,R.drawable.i12,R.drawable.i13,R.drawable.i14,R.drawable.i15,R.drawable.i16,R.drawable.i17,R.drawable.i18,)
        for(i in list.indices) {
            list[i].setOnClickListener {
                requireContext().getSharedPreferences("prefs",Context.MODE_PRIVATE)
                    .edit().putInt("tmp",id[i]).apply()
                navController.popBackStack()
            }
        }
        binding.imageView3.setOnClickListener {
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
         * @return A new instance of fragment IconsFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            IconsFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}