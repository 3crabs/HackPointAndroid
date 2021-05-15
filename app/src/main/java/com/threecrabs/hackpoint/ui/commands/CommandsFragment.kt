package com.threecrabs.hackpoint.ui.commands

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.threecrabs.hackpoint.databinding.CommandFragmentBinding
import com.threecrabs.hackpoint.ui.commands.recycler.RecyclerItemCommand
import eu.davidea.flexibleadapter.FlexibleAdapter
import eu.davidea.flexibleadapter.common.FlexibleItemDecoration

class CommandsFragment: Fragment() {

    private lateinit var binding: CommandFragmentBinding
    private val viewModel: CommandViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = CommandFragmentBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getTeams()
        viewModel.teams.observe(viewLifecycleOwner) { teams ->
            binding.recycler.adapter = FlexibleAdapter(teams.map{ RecyclerItemCommand(it) })
//            (binding.recycler.adapter as? FlexibleAdapter<*>)?.let {
//                it.updateDataSet(teams.map{ RecyclerItemCommand(it) },true)
//            }
        }
        binding.recycler.layoutManager = LinearLayoutManager(requireContext())
        binding.recycler.adapter = FlexibleAdapter(listOf())
        binding.recycler.addItemDecoration(FlexibleItemDecoration(requireContext())
            .withOffset(8)
            .withBottomEdge(true)
        )
        binding.back.setOnClickListener {
            findNavController().popBackStack()
        }
    }
}