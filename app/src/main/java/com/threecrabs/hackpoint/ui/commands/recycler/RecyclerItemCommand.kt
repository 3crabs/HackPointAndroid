package com.threecrabs.hackpoint.ui.commands.recycler

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.threecrabs.hackpoint.R
import com.threecrabs.hackpoint.api.dto.DTOTeam
import com.threecrabs.hackpoint.databinding.ItemCommandBinding
import eu.davidea.flexibleadapter.FlexibleAdapter
import eu.davidea.flexibleadapter.items.AbstractFlexibleItem
import eu.davidea.flexibleadapter.items.IFlexible

class RecyclerItemCommand(val item: DTOTeam)
    : AbstractFlexibleItem<RecyclerItemCommand.CommandViewHolder>() {

    override fun equals(other: Any?): Boolean {
        return false
    }

    override fun getLayoutRes(): Int {
        return R.layout.item_command
    }

    override fun createViewHolder(
        view: View,
        adapter: FlexibleAdapter<IFlexible<RecyclerView.ViewHolder>>?
    ): CommandViewHolder {
        return CommandViewHolder(ItemCommandBinding.bind(view))
    }

    override fun bindViewHolder(
        adapter: FlexibleAdapter<IFlexible<RecyclerView.ViewHolder>>,
        holder: CommandViewHolder,
        position: Int,
        payloads: MutableList<Any>?
    ) {
        holder.binding.apply {
            name.text = item.name
            root.setOnClickListener {
                adapter.mItemClickListener.onItemClick(it, position)
            }
            if (item.statusFinalPitch == "present") {
                check.visibility = View.VISIBLE
            } else {
                check.visibility = View.GONE
            }
        }
    }

    override fun hashCode(): Int {
        return item.hashCode()
    }

    class CommandViewHolder(val binding: ItemCommandBinding) : RecyclerView.ViewHolder(binding.root)
}