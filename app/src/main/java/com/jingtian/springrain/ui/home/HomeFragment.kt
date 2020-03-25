package com.jingtian.springrain.ui.home

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.jingtian.springrain.R
import com.jingtian.springrain.data.Operation
import com.jingtian.springrain.databinding.FragmentHomeBinding
import com.jingtian.springrain.helper.provideHomeViewModelFactory
import com.jingtian.springrain.helper.setupRefreshLayout

class HomeFragment : Fragment() {
    private val viewModel: HomeViewModel by viewModels {
        provideHomeViewModelFactory(this)
    }
    private lateinit var viewDataBinding: FragmentHomeBinding
    private lateinit var listAdapter: OperationAdapter
    var operationId = 4

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        viewDataBinding = FragmentHomeBinding.inflate(inflater, container, false).apply {
            viewmodel = viewModel
        }
        return viewDataBinding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewDataBinding.lifecycleOwner = this.viewLifecycleOwner
        setupListAdapter()
        setupRefreshLayout(viewDataBinding.refreshLayout, viewDataBinding.operations)
        setupFab()
    }

    private fun setupListAdapter() {
        val viewModel = viewDataBinding.viewmodel
        if (viewModel != null) {
            listAdapter = OperationAdapter(viewModel)
            viewDataBinding.operations.adapter = listAdapter
        }
    }

    private fun setupFab() {
        activity?.findViewById<FloatingActionButton>(R.id.add_task_fab)?.let {
            it.setOnClickListener {
                viewModel.insert(Operation(operationId.toString(), 2,
                    "https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1584448673299&di=3352fde1360d7e722f81bbfdc3d90b4a&imgtype=0&src=http%3A%2F%2Fimg1001.pocoimg.cn%2Fimage%2Fpoco%2Fworks%2F65%2F2013%2F0416%2F09%2F5331815520130416175036096_53318155.jpg"))
                operationId++
            }
        }
    }
}
