package com.example.testappforwork.ui.images

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.testappforwork.R
import com.example.testappforwork.databinding.FragmentImagesBinding
import com.example.testappforwork.models.ErrorResult
import com.example.testappforwork.models.PendingResult
import com.example.testappforwork.models.SuccessResult
import com.example.testappforwork.models.takeSuccess
import com.example.testappforwork.utilities.AppScrollListener
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ImagesFragment : Fragment(R.layout.fragment_images) {

    private var _binding: FragmentImagesBinding? = null
    private val binding get() = _binding!!
    private val viewModel by viewModels<ImagesViewModel>()

    private lateinit var recyclerView: RecyclerView
    private val adapter: ImagesAdapter = ImagesAdapter()
    private lateinit var layoutManager: LinearLayoutManager
    private lateinit var scrollListener: AppScrollListener

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentImagesBinding.inflate(inflater, container, false)

        init()
        return binding.root
    }

    private fun init() {
        recyclerView = binding.imagesRecyclerView
        layoutManager = LinearLayoutManager(context)
        recyclerView.layoutManager = layoutManager
        recyclerView.adapter = adapter

        scrollListener = AppScrollListener(layoutManager) {
            viewModel.getImages()
            adapter.addLoadingHeader()
        }
        recyclerView.addOnScrollListener(scrollListener)
    }

    override fun onStart() {
        super.onStart()

        viewModel.imagesLiveData.observe(viewLifecycleOwner) { result ->
            when (result) {
                is SuccessResult -> {
                    result.takeSuccess()?.let { adapter.updateList(it) }
                    scrollListener.isLoading = false
                    Log.d(TAG, "${result.takeSuccess()?.count()}")
                    binding.apply {
                        imagesRecyclerView.visibility = View.VISIBLE
                        binding.progressBar.visibility = View.GONE
                        errorTryAgain.visibility = View.GONE
                    }
                }
                is PendingResult -> {
                    showPending()
                }
                is ErrorResult -> {
                    binding.apply {
                        imagesRecyclerView.visibility = View.GONE
                        progressBar.visibility = View.GONE
                        errorTryAgain.visibility = View.VISIBLE
                    }
                }
            }
        }

        binding.tryAgainButton.setOnClickListener {
            viewModel.getImages()
            showPending()
        }
    }

    private fun showPending() {
        binding.apply {
            imagesRecyclerView.visibility = View.GONE
            binding.progressBar.visibility = View.VISIBLE
            errorTryAgain.visibility = View.GONE
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        private const val TAG = "ImagesFragment"
    }
}