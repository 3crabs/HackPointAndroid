package com.threecrabs.hackpoint.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.threecrabs.hackpoint.databinding.MainFragmentBinding
import com.threecrabs.hackpoint.ui.main.recycler.MainItem
import com.threecrabs.hackpoint.ui.main.recycler.RecyclerMainItem
import eu.davidea.flexibleadapter.FlexibleAdapter
import eu.davidea.flexibleadapter.common.FlexibleItemDecoration

class MainFragment: Fragment() {

    private lateinit var binding: MainFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = MainFragmentBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.recycler.layoutManager = LinearLayoutManager(requireContext())
        binding.recycler.adapter = FlexibleAdapter(listOf(
            RecyclerMainItem(MainItem(
                "Питч идей",
                "пятница, 14 мая",
                true
            )),
            RecyclerMainItem(MainItem(
                "Чекпоинт",
                "суббота, 15 мая",
                true
            )),
            RecyclerMainItem(MainItem(
                "Демофест",
                "воскресенье, 16 мая",
                false
            ))
        ))
        binding.recycler.addItemDecoration(
            FlexibleItemDecoration(requireContext())
                .withOffset(24)
                .withBottomEdge(true)
        )
    }

}