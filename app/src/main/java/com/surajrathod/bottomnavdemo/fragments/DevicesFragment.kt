package com.surajrathod.bottomnavdemo.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.surajrathod.bottomnavdemo.R
import com.surajrathod.bottomnavdemo.databinding.FragmentDevicesBinding

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [DevicesFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class DevicesFragment : Fragment() {

    lateinit var binding : FragmentDevicesBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_devices, container, false)
        binding = FragmentDevicesBinding.bind(view)


        setupClicks()

        return binding.root
    }

    private fun setupClicks() {
        binding.btnDeviceDetails.setOnClickListener {
            findNavController().navigate(R.id.action_btnDevice_to_deviceDetailsFragment2)
        }
    }


}