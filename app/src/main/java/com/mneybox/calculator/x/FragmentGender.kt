package com.mneybox.calculator.x

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.navigation.Navigation
import com.mneybox.calculator.x.databinding.FragmentGenderBinding

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [FragmentGender.newInstance] factory method to
 * create an instance of this fragment.
 */
class FragmentGender : Fragment() {
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

    var ind = 0
    private lateinit var binding: FragmentGenderBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentGenderBinding.inflate(inflater,container,false)
        binding.euro.setOnClickListener {
            ind = 0
            inval()
        }
        binding.dollar.setOnClickListener {
            ind = 1
            inval()
        }
        binding.button.setOnClickListener {
            requireActivity().getSharedPreferences("prefs",Context.MODE_PRIVATE)
                .edit()
                .putString("name",requireArguments().getString("name"))
                .putInt("age",requireArguments().getInt("age"))
                .putInt("currency",requireArguments().getInt("currency"))
                .putString("gender",if(ind==0) "Male" else "Female")
                .apply()
                startActivity(Intent(requireContext(),MainActivity2::class.java))
            requireActivity().finish()
        }
        inval()
        return binding.root
    }
    fun inval() {
        binding.euro.setTextColor(if(ind==0) requireActivity().getColor(R.color.red) else requireActivity().getColor(R.color.grey))
        binding.dollar.setTextColor(if(ind==1) requireActivity().getColor(R.color.red) else requireActivity().getColor(R.color.grey))
        binding.eur.setColorFilter(ContextCompat.getColor(requireContext(), if(ind==0) R.color.red else R.color.grey), android.graphics.PorterDuff.Mode.SRC_IN);
        binding.dol.setColorFilter(ContextCompat.getColor(requireContext(), if(ind==1) R.color.red else R.color.grey), android.graphics.PorterDuff.Mode.SRC_IN);
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment FragmentGender.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            FragmentGender().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}