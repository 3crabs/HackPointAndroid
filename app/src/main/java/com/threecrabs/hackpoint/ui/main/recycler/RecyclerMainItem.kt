package com.threecrabs.hackpoint.ui.main.recycler

import android.view.View
import androidx.core.content.ContextCompat
import androidx.core.content.res.ResourcesCompat
import androidx.recyclerview.widget.RecyclerView
import com.threecrabs.hackpoint.R
import com.threecrabs.hackpoint.databinding.MainItemBinding
import eu.davidea.flexibleadapter.FlexibleAdapter
import eu.davidea.flexibleadapter.items.AbstractFlexibleItem
import eu.davidea.flexibleadapter.items.IFlexible

class RecyclerMainItem(val item: MainItem) :
    AbstractFlexibleItem<RecyclerMainItem.MainItemViewHolder>() {

    override fun equals(other: Any?): Boolean {
        return false
    }

    override fun getLayoutRes(): Int {
        return R.layout.main_item
    }


    override fun createViewHolder(
        view: View,
        adapter: FlexibleAdapter<IFlexible<RecyclerView.ViewHolder>>?
    ): MainItemViewHolder {
        return MainItemViewHolder(MainItemBinding.bind(view))
    }

    override fun bindViewHolder(
        adapter: FlexibleAdapter<IFlexible<RecyclerView.ViewHolder>>,
        holder: MainItemViewHolder,
        position: Int,
        payloads: MutableList<Any>?
    ) {
        holder.binding.apply {
            statusName.setText(
                if (item.status) {
                    R.string.done
                } else {
                    R.string.in_process
                }
            )
            statusArea.background = root.context.getDrawable(
                if (item.status) {
                    R.drawable.background_status_done
                } else {
                    R.drawable.background_status_in_process
                }
            )
            statusName.setTextColor(
                ContextCompat.getColor(
                    root.context,
                    if (item.status) {
                        R.color.done_dark
                    } else {
                        R.color.in_progress_dark
                    }
                )
            )
            statusIcon.setColorFilter(
                ContextCompat.getColor(
                    root.context,
                    if (item.status) {
                        R.color.done_dark
                    } else {
                        R.color.in_progress_dark
                    }
                ),
                android.graphics.PorterDuff.Mode.SRC_IN
            )
            title.text = item.name
            date.text = item.date
            button.isEnabled = !item.status
            button.setText(
                if (item.status) {
                    R.string.result
                } else {
                    R.string.grade
                }
            )
            button.background = root.context.getDrawable(
                if (item.status) {
                    R.drawable.background_button_grey
                } else {
                    R.drawable.background_button
                }
            )
            button.setTextColor(
                ContextCompat.getColor(
                    root.context,
                    if (item.status) {
                        R.color.black
                    } else {
                        R.color.white
                    }
                )
            )
            if (!item.status) {
                button.setOnClickListener {
                    adapter.mItemClickListener.onItemClick(it, position)
                }
            }
        }
    }

    override fun hashCode(): Int {
        return item.hashCode()
    }

    class MainItemViewHolder(val binding: MainItemBinding) : RecyclerView.ViewHolder(binding.root)
}