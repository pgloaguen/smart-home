package me.pgloaguen.smarthome.scene.devicesteering

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.navArgs
import kotlinx.coroutines.launch
import me.pgloaguen.domain.model.Device
import me.pgloaguen.smarthome.R
import me.pgloaguen.smarthome.databinding.DeviceSteeringFragmentBinding
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf


class DeviceSteeringFragment : Fragment() {

    private var _binding: DeviceSteeringFragmentBinding? = null

    private val binding get() = _binding!!

    private val args by navArgs<DeviceSteeringFragmentArgs>()

    private val viewModel by viewModel<DeviceSteeringViewModel> { parametersOf(args.deviceId) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.uiState.collect(::render)
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = DeviceSteeringFragmentBinding.inflate(inflater, container, false)
        binding.retryButton.setOnClickListener { viewModel.retry() }
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun render(state: DeviceSteeringState) {
        binding.loadingView.visibility = if (state.isLoading) View.VISIBLE else View.GONE
        binding.errorView.visibility = if (state.error != null) View.VISIBLE else View.GONE
        binding.contentView.visibility = if (state.device != null) View.VISIBLE else View.GONE
        binding.errorMessage.text = state.error?.message

        when (state.device) {
            is Device.Heater -> renderHeater(state.device)
            is Device.Light -> renderLight(state.device)
            is Device.RollerShutter -> renderRollerShutter(state.device)
            null -> Unit
        }
    }

    private fun renderHeater(device: Device.Heater) {
        binding.sliderLabel.text = getString(R.string.device_steering_heater_value)
        binding.slider.value = device.temperature
        binding.slider.valueFrom = device.minTemperature
        binding.slider.valueTo = device.maxTemperature
        binding.slider.stepSize = device.stepTemperature
        binding.modeSwitch.visibility = View.VISIBLE
        binding.modeSwitch.isChecked = device.mode == Device.Mode.ON
    }

    private fun renderLight(device: Device.Light) {
        binding.sliderLabel.text = getString(R.string.device_steering_heater_value)
        binding.slider.value = device.intensity.toFloat()
        binding.slider.valueFrom = device.minIntensity.toFloat()
        binding.slider.valueTo = device.maxIntensity.toFloat()
        binding.slider.stepSize = 1f
        binding.modeSwitch.visibility = View.VISIBLE
        binding.modeSwitch.isChecked = device.mode == Device.Mode.ON
    }

    private fun renderRollerShutter(device: Device.RollerShutter) {
        binding.sliderLabel.text = getString(R.string.device_steering_heater_value)
        binding.slider.value = device.position.toFloat()
        binding.slider.valueFrom = device.minPosition.toFloat()
        binding.slider.valueTo = device.maxPosition.toFloat()
        binding.slider.stepSize = 1f
        binding.modeSwitch.visibility = View.GONE
    }
}