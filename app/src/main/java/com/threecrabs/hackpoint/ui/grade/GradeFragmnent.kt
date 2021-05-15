package com.threecrabs.hackpoint.ui.grade

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.PagerSnapHelper
import com.threecrabs.hackpoint.R
import com.threecrabs.hackpoint.databinding.GradeFragmentBinding
import com.threecrabs.hackpoint.ui.commands.CommandsFragmentDirections
import com.threecrabs.hackpoint.ui.grade.recycler.RecyclerItemPoint
import com.threecrabs.hackpoint.ui.grade.recycler.RecyclerItemPointEnd
import eu.davidea.flexibleadapter.FlexibleAdapter
import eu.davidea.flexibleadapter.items.AbstractFlexibleItem


class GradeFragmnent: Fragment() {

    private lateinit var binding: GradeFragmentBinding
    private val viewModel: GradeViewModel by viewModels()
    private val args: GradeFragmnentArgs by navArgs()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.team = args.team
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = GradeFragmentBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getPoints()
        viewModel.points.observe(viewLifecycleOwner) { points ->
            val list = mutableListOf<AbstractFlexibleItem<*>>()
            list.addAll(points.map { RecyclerItemPoint(it) })
            list.add(RecyclerItemPointEnd())
            binding.recycler.adapter = FlexibleAdapter(
                list
            ).apply {
                addListener(object: FlexibleAdapter.OnItemClickListener {
                    override fun onItemClick(view: View, position: Int): Boolean {
                        when (view.id) {
                            R.id.left -> {
                                if (position > 0) {
                                    binding.recycler.post {
                                        binding.recycler.smoothScrollToPosition(position - 1)
                                    }
                                }
                            }
                            R.id.right -> {
                                if (points.size >= position - 2) {
                                    binding.recycler.post {
                                        binding.recycler.smoothScrollToPosition(position + 1)
                                    }
                                }
                            }
                            R.id.button -> {

                            }
                            R.id.gradeArea -> {
                                getItem(position)?.let {
                                    (it as? RecyclerItemPoint)?.let {
                                        viewModel.degrePoint(it.item.id, it.item.point)
                                    }
                                }
                            }
                        }
                        return false
                    }
                })
            }
        }
        binding.back.setOnClickListener {
            findNavController().popBackStack()
        }
        binding.recycler.layoutManager = LinearLayoutManager(
            requireContext(),
            LinearLayoutManager.HORIZONTAL,
            false
        )
        binding.commandName.text = viewModel.team.name
        val snapHelper = PagerSnapHelper()
        snapHelper.attachToRecyclerView(binding.recycler)
    }
}