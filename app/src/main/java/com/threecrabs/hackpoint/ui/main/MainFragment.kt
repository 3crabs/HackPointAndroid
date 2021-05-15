package com.threecrabs.hackpoint.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.threecrabs.hackpoint.BaseViewModel
import com.threecrabs.hackpoint.R
import com.threecrabs.hackpoint.changeAuth
import com.threecrabs.hackpoint.databinding.MainFragmentBinding
import com.threecrabs.hackpoint.ui.main.recycler.MainItem
import com.threecrabs.hackpoint.ui.main.recycler.RecyclerMainItem
import eu.davidea.flexibleadapter.FlexibleAdapter
import eu.davidea.flexibleadapter.common.FlexibleItemDecoration

class MainFragment: Fragment() {

    private lateinit var binding: MainFragmentBinding
    private val viewModel: BaseViewModel by viewModels()

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
        )).apply {
            addListener(object: FlexibleAdapter.OnItemClickListener {
                override fun onItemClick(view: View?, position: Int): Boolean {
                    findNavController().navigate(MainFragmentDirections.actionMainToCommands())
                    return false
                }
            })
        }
        binding.recycler.addItemDecoration(
            FlexibleItemDecoration(requireContext())
                .withOffset(24)
                .withBottomEdge(true)
        )
        binding.logout.setOnClickListener {
            viewModel.sharedPrefs.saveToken(null)
            requireActivity().changeAuth()
        }
        viewModel.sharedPrefs.getToken()?.let {
            binding.name.text = getString(R.string.hello, it.referee.name)
        }
    }

}