package com.soten.tablepay.ui.customer.home

import android.content.pm.PackageManager
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.os.bundleOf
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.soten.tablepay.R
import com.soten.tablepay.databinding.FragmentCustomerHomeBinding
import com.soten.tablepay.ui.base.BaseFragment
import com.soten.tablepay.utils.CameraXManager
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CustomerHomeFragment :
    BaseFragment<FragmentCustomerHomeBinding>(R.layout.fragment_customer_home), ScanListener {

    private lateinit var cameraManager: CameraXManager

    private var isScan = false

    private val viewModel by viewModels<HomeViewModel>()

    override fun findRestaurant(restaurantInformation: String) {
        if (restaurantInformation == "gd") {
            if (isScan) {
                return
            }
            isScan = true
            val bundle = bundleOf(
                "KEY" to restaurantInformation
            )
            findNavController().navigate(R.id.navigation_customer_settings, bundle)
        } else {
            Toast.makeText(context, "조회된 가게 정보 없음", Toast.LENGTH_SHORT).show()
        }

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        createCameraManager()

        if (allPermissionsGranted()) {
            cameraManager.startCamera()
        } else {
            ActivityCompat.requestPermissions(
                requireActivity(),
                REQUIRED_PERMISSIONS,
                REQUEST_CODE_PERMISSIONS
            )
        }

    }

    private fun createCameraManager() {
        cameraManager = CameraXManager(
            requireActivity(),
            binding.viewFinder,
            this,
            binding.barcodeOverlay,
            this
        )
    }

    private fun allPermissionsGranted() = REQUIRED_PERMISSIONS.all {
        ContextCompat.checkSelfPermission(requireContext(), it) == PackageManager.PERMISSION_GRANTED
    }

    override fun onPause() {
        super.onPause()
        isScan = false
    }

    override fun onDestroyView() {
        super.onDestroyView()
        cameraManager.cameraShutDown()
    }

    companion object {
        private const val REQUEST_CODE_PERMISSIONS = 10
        private val REQUIRED_PERMISSIONS = arrayOf(
            android.Manifest.permission.CAMERA,
        )
    }
}