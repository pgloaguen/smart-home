package me.pgloaguen.smarthome.scene.homepage

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import me.pgloaguen.domain.model.Device
import me.pgloaguen.smarthome.databinding.ItemDeviceBinding

class DeviceListAdapter : RecyclerView.Adapter<DeviceListAdapter.DeviceViewHolder>() {
    var devices: List<Device> = emptyList()
        set(value) {
            DiffUtil.calculateDiff(object : DiffUtil.Callback() {
                override fun getOldListSize(): Int = field.size

                override fun getNewListSize(): Int = value.size

                override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean =
                    field[oldItemPosition].id == value[newItemPosition].id

                override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean =
                    field[oldItemPosition] == value[newItemPosition]
            }).dispatchUpdatesTo(this)
            field = value
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DeviceViewHolder {
        val binding = ItemDeviceBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return DeviceViewHolder(binding)
    }

    override fun getItemCount(): Int = devices.size

    override fun onBindViewHolder(holder: DeviceViewHolder, position: Int) {
        val device = devices[position]
        holder.bind(device)
    }

    inner class DeviceViewHolder(private val binding: ItemDeviceBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(device: Device) {
            binding.deviceName.text = device.name
            when(device) {
                is Device.Heater -> {
                    binding.deviceValue.text = device.temperature.toString()
                    binding.deviceMode.text = device.mode.toString()
                }
                is Device.Light -> {
                    binding.deviceValue.text = device.intensity.toString()
                    binding.deviceMode.text = device.mode.toString()
                }
                is Device.RollerShutter -> {
                    binding.deviceValue.text = device.position.toString()
                    binding.deviceMode.text = null
                }
            }
        }
    }
}