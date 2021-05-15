package com.threecrabs.hackpoint.ui.grade.recycler

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.threecrabs.hackpoint.R
import com.threecrabs.hackpoint.api.dto.DTOPoint
import com.threecrabs.hackpoint.databinding.ItemGradeBinding
import com.threecrabs.hackpoint.databinding.ItemGradeFinalBinding
import eu.davidea.flexibleadapter.FlexibleAdapter
import eu.davidea.flexibleadapter.items.AbstractFlexibleItem
import eu.davidea.flexibleadapter.items.IFlexible

class RecyclerItemPointEnd
    : AbstractFlexibleItem<RecyclerItemPointEnd.PointViewHolder>() {

    override fun equals(other: Any?): Boolean {
        return false
    }

    override fun getLayoutRes(): Int {
        return R.layout.item_grade_final
    }

    override fun createViewHolder(
        view: View,
        adapter: FlexibleAdapter<IFlexible<RecyclerView.ViewHolder>>?
    ): PointViewHolder {
        return PointViewHolder(ItemGradeFinalBinding.bind(view))
    }

    override fun bindViewHolder(
        adapter: FlexibleAdapter<IFlexible<RecyclerView.ViewHolder>>,
        holder: PointViewHolder,
        position: Int,
        payloads: MutableList<Any>?
    ) {
        holder.binding.apply {
            button.setOnClickListener {
                adapter.mItemClickListener.onItemClick(it, position)
            }
        }
    }

    override fun hashCode(): Int {
        return 22.hashCode()
    }

    class PointViewHolder(val binding: ItemGradeFinalBinding) : RecyclerView.ViewHolder(binding.root)
}