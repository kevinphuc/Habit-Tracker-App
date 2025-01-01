package com.example.habit_tracker_app.views

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.FrameLayout
import androidx.recyclerview.widget.GridLayoutManager
import com.example.habit_tracker_app.adapters.IconSelectionAdapter
import com.example.habit_tracker_app.databinding.LayoutIconPickerBinding
import com.example.habit_tracker_app.managers.IconManager
import com.example.habit_tracker_app.models.IconModel

class IconPickerView(context: Context, attributeSet: AttributeSet) : FrameLayout(context, attributeSet) {

    private val binding : LayoutIconPickerBinding = LayoutIconPickerBinding.inflate(LayoutInflater.from(context), this, true)

    private val iconSelectionAdapter : IconSelectionAdapter

    var iconManager : IconManager? = null
        set(value) {
            field = value

            value ?: return

            iconSelectionAdapter.data = value.iconModels
            iconSelectionAdapter.notifyDataSetChanged()
        }

    init {

        binding.recyclerView.layoutManager = GridLayoutManager(context, 5)

        iconSelectionAdapter = IconSelectionAdapter(context, ArrayList())
        binding.recyclerView.adapter = iconSelectionAdapter
    }

    fun setSelectedIcon(iconModel: IconModel?) {
        iconSelectionAdapter.selectedIconModel = iconModel
        iconSelectionAdapter.notifyDataSetChanged()
    }

    fun setIconSelectedListener(iconSelectedListener: IconSelectionAdapter.IconSelectedListener) {
        iconSelectionAdapter.iconSelectedListener = iconSelectedListener
    }
}