package com.jingtian.springrain.ui.home

import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.ServiceConnection
import android.os.Bundle
import android.os.IBinder
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.jingtian.springrain.IMyAidlInterface
import com.jingtian.springrain.R
import com.jingtian.springrain.databinding.FragmentHomeBinding
import com.jingtian.springrain.helper.provideHomeViewModelFactory
import com.jingtian.springrain.helper.setupRefreshLayout

class HomeFragment : Fragment() {
    private val viewModel: HomeViewModel by viewModels {
        provideHomeViewModelFactory(this)
    }
    private lateinit var viewDataBinding: FragmentHomeBinding
    private lateinit var listAdapter: OperationAdapter

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

    private var mIntent: Intent? = null
    private var myAidlInterface: IMyAidlInterface? = null

    private val connection = object : ServiceConnection {
        override fun onServiceDisconnected(p0: ComponentName?) {
        }

        override fun onServiceConnected(p0: ComponentName?, iBinder: IBinder?) {
            myAidlInterface = IMyAidlInterface.Stub.asInterface(iBinder)
        }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewDataBinding.lifecycleOwner = this.viewLifecycleOwner
        setupListAdapter()
        setupRefreshLayout(viewDataBinding.refreshLayout, viewDataBinding.operations)
        setupFab()

        mIntent = Intent()
        mIntent?.component =
            ComponentName("com.jingtian.springrain", "com.jingtian.springrain.MyService")
        activity?.bindService(mIntent, connection, Context.BIND_AUTO_CREATE)
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
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        activity?.unbindService(connection)
    }
}
