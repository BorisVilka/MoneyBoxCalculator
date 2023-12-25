package com.mneybox.calculator.x.ui.dashboard

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import com.mneybox.calculator.x.R
import com.mneybox.calculator.x.databinding.FragmentDashboardBinding

class DashboardFragment : Fragment() {

    private var _binding: FragmentDashboardBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val dashboardViewModel =
            ViewModelProvider(this).get(DashboardViewModel::class.java)

        _binding = FragmentDashboardBinding.inflate(inflater, container, false)
        val root: View = binding.root

        val list = requireContext().getSharedPreferences("prefs",Context.MODE_PRIVATE).getStringSet("moneybox",HashSet<String>())!!.toMutableList()
        val adapter = BoxAdapter(list,requireContext()) {
            val navController = Navigation.findNavController(requireActivity(), R.id.nav_host_fragment_activity_main2)
            navController.navigate(R.id.boxFragment,Bundle().apply { putString("box",it) })
        }
        binding.list.adapter = adapter
        ItemTouchHelper(object : ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT) {
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                // this method is called
                // when the item is moved.
                return false
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                // this method is called when we swipe our item to right direction.
                // on below line we are getting the item at a particular position.

                // below line is to get the position
                // of the item at that position.
                val position = viewHolder.adapterPosition

                // this method is called when item is swiped.
                // below line is to remove item from our array list.
                adapter.list.removeAt(viewHolder.adapterPosition)

                // below line is to notify our item is removed from adapter.
                adapter.notifyItemRemoved(viewHolder.adapterPosition)
                requireContext().getSharedPreferences("prefs",Context.MODE_PRIVATE)
                    .edit()
                    .putStringSet("moneybox",adapter.list.toSet())
                    .apply()

                // below line is to display our snackbar with action.
                // below line is to display our snackbar with action.
                // below line is to display our snackbar with action.
            }
            // at last we are adding this
            // to our recycler view.
        }).attachToRecyclerView(binding.list)
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}