package com.threecrabs.hackpoint.ui.grade.recycler

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.threecrabs.hackpoint.R
import com.threecrabs.hackpoint.api.dto.DTOPoint
import com.threecrabs.hackpoint.databinding.ItemGradeBinding
import eu.davidea.flexibleadapter.FlexibleAdapter
import eu.davidea.flexibleadapter.items.AbstractFlexibleItem
import eu.davidea.flexibleadapter.items.IFlexible

class RecyclerItemPoint(val item: DTOPoint)
    : AbstractFlexibleItem<RecyclerItemPoint.PointViewHolder>() {

    override fun equals(other: Any?): Boolean {
        return false
    }

    override fun getLayoutRes(): Int {
        return R.layout.item_grade
    }

    override fun createViewHolder(
        view: View,
        adapter: FlexibleAdapter<IFlexible<RecyclerView.ViewHolder>>?
    ): PointViewHolder {
        return PointViewHolder(ItemGradeBinding.bind(view))
    }

    override fun bindViewHolder(
        adapter: FlexibleAdapter<IFlexible<RecyclerView.ViewHolder>>,
        holder: PointViewHolder,
        position: Int,
        payloads: MutableList<Any>?
    ) {
        holder.binding.apply {
            description.text = item.criterion.description
            header.text = item.criterion.name
            if (position == 0) {
                left.visibility = View.GONE
            } else {
                left.visibility = View.VISIBLE
            }
        }
    }

    override fun hashCode(): Int {
        return item.hashCode()
    }

    class PointViewHolder(val binding: ItemGradeBinding) : RecyclerView.ViewHolder(binding.root)
}