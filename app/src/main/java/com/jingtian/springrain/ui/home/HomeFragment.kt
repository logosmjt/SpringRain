package com.jingtian.springrain.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.jingtian.springrain.R
import com.jingtian.springrain.data.Operation
import com.jingtian.springrain.databinding.FragmentHomeBinding
import com.jingtian.springrain.helper.provideHomeViewModelFactory
import com.jingtian.springrain.helper.setupRefreshLayout
import com.jingtian.springrain.ui.view.ScrollChildSwipeRefreshLayout

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
                    "https://dss2.baidu.com/6ONYsjip0QIZ8tyhnq/it/u=3793085435,4226680853&fm=85&app=63&f=JPEG?w=121&h=75&s=569227677FDB46D04D19B9EA0300B03D"))
                operationId++
            }
        }
    }
}
