package com.feliz.apps.presentation.ui.splash

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.feliz.apps.R
import com.feliz.apps.databinding.FragmentSplashBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class SplashFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding: FragmentSplashBinding = FragmentSplashBinding.inflate(inflater)

//        Handler(Looper.getMainLooper()).postDelayed({
//            findNavController().navigate(R.id.action_splashFragment_to_homeFragment)
//        }, 1000)

        lifecycleScope.launch(Dispatchers.IO) {
            delay(1000)
            lifecycleScope.launch(Dispatchers.Main) {
                findNavController().navigate(R.id.action_splashFragment_to_homeFragment)
//                findNavController().navigate(R.id.action_splashFragment_to_vendorFragment)
//                findNavController().navigate(R.id.action_splashFragment_to_companyProfileFragment)
            }
        }

        return binding.root
    }

}