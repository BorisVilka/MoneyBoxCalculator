package com.mneybox.calculator.x.ui.notifications

import android.content.Context
import android.graphics.PorterDuff
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import com.mneybox.calculator.x.ColorDialog
import com.mneybox.calculator.x.R
import com.mneybox.calculator.x.databinding.FragmentNotificationsBinding

class NotificationsFragment : Fragment() {

    private var _binding: FragmentNotificationsBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val notificationsViewModel =
            ViewModelProvider(this).get(NotificationsViewModel::class.java)

        _binding = FragmentNotificationsBinding.inflate(inflater, container, false)
        val root: View = binding.root

        binding.c3.setOnClickListener {
            val navController = Navigation.findNavController(requireActivity(), R.id.nav_host_fragment_activity_main2)
            navController.navigate(R.id.iconsFragment)
        }
        var color = R.color.red
        binding.c2.setOnClickListener {
            val dialog = ColorDialog() {
                color = it
                binding.icon.setColorFilter(requireActivity().getColor(color),PorterDuff.Mode.SRC_IN)
            }
            dialog.show(requireActivity().supportFragmentManager,"TAG")
        }
        binding.button.setOnClickListener {
            if(binding.checkBox.isChecked) {
                val navController = Navigation.findNavController(requireActivity(), R.id.nav_host_fragment_activity_main2)
                navController.navigate(R.id.fragmentCredit,Bundle().apply {
                    putString("tmp","1|${requireContext().getSharedPreferences("prefs",Context.MODE_PRIVATE).getInt("tmp",R.drawable.i1)}|$color|${binding.amount.text.toString()}|${binding.months.text.toString()}|")
                })
                binding.amount.setText("")
                binding.months.setText("")
            } else {
                var s = "0|${requireContext().getSharedPreferences("prefs",Context.MODE_PRIVATE).getInt("tmp",R.drawable.i1)}|$color|${binding.amount.text.toString()}|${binding.months.text.toString()}"
                val set = requireContext().getSharedPreferences("prefs",Context.MODE_PRIVATE).getStringSet("moneybox",HashSet<String>())!!
                val set1 = HashSet<String>()
                set1.addAll(set)
                set1.add(s)
                requireContext().getSharedPreferences("prefs",Context.MODE_PRIVATE).edit().putStringSet("moneybox",set1).apply()
                binding.amount.setText("")
                binding.months.setText("")
            }
        }
        return root
    }

    override fun onResume() {
        val tmp = requireContext().getSharedPreferences("prefs",Context.MODE_PRIVATE).getInt("tmp",R.drawable.i1)
        binding.icon.setImageResource(tmp)
        super.onResume()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}