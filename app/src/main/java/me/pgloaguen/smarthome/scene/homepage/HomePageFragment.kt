package me.pgloaguen.smarthome.scene.homepage

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import kotlinx.coroutines.launch
import me.pgloaguen.smarthome.databinding.HomePageFragmentBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

class HomePageFragment : Fragment() {

    private var _binding: HomePageFragmentBinding? = null

    private val binding get() = _binding!!

    private val viewModel by viewModel<HomePageViewModel>()

    private val deviceListAdapter = DeviceListAdapter()

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
        _binding = HomePageFragmentBinding.inflate(inflater, container, false)
        binding.devicesList.adapter = deviceListAdapter
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun render(state: HomePageState) {
        binding.loadingView.visibility = if (state.isLoading) View.VISIBLE else View.GONE
        binding.errorView.visibility = if (state.error != null) View.VISIBLE else View.GONE
        binding.errorMessage.text = state.error?.message
        binding.retryButton.setOnClickListener { viewModel.retry() }
        deviceListAdapter.devices = state.devices
    }
}