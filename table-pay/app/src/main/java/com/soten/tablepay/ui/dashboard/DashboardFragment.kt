package com.soten.tablepay.ui.dashboard

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.soten.tablepay.R
import com.soten.tablepay.databinding.FragmentDashboardBinding
import com.soten.tablepay.ui.base.BaseFragment

class DashboardFragment : BaseFragment<FragmentDashboardBinding>(R.layout.fragment_dashboard) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.textDashboard.setOnClickListener {
            Toast.makeText(context, "hi", Toast.LENGTH_SHORT).show()
        }
    }

}