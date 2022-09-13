package hasan.gurgur.movieappexample.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import hasan.gurgur.movieappexample.R
import hasan.gurgur.movieappexample.databinding.FragmentFirstBinding
import hasan.gurgur.movieappexample.model.Result
import hasan.gurgur.movieappexample.viewmodel.CharacterListViewModel


class FirstFragment : Fragment() {
    private var _binding: FragmentFirstBinding? = null
    private val binding get() = _binding!!
    var loading = true
    var pastItemsVisible = 0
    var visibleItemCount: Int = 0
    var totalItemCount: Int = 0
    var page = 1

    private lateinit var viewModel: CharacterListViewModel
    lateinit var characterListAdapter: CharacterListAdapter
    var list = arrayListOf<Result>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentFirstBinding.inflate(inflater, container, false)
        initAdapter()


        viewModel = ViewModelProvider(requireActivity()).get(CharacterListViewModel::class.java)
        viewModel.fetchDataFromRemoteApi(page)

        viewModel.upcomingMoviesModel.observe(requireActivity()) {
            list.addAll(it.results)
            characterListAdapter.submitList(list)
            binding.progressBar.visibility = View.GONE
        }

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun initAdapter() {
        characterListAdapter = CharacterListAdapter {

            findNavController().navigate(R.id.action_firstFragment_to_detailFragment, bundleOf("movie_detail" to it))
        }

        binding.characterListRec.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        binding.characterListRec.adapter = characterListAdapter



        binding.characterListRec.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                if (dy > 0) { //check for scroll down
                    visibleItemCount =
                        (binding.characterListRec.layoutManager as LinearLayoutManager).childCount
                    totalItemCount =
                        (binding.characterListRec.layoutManager as LinearLayoutManager).itemCount
                    pastItemsVisible =
                        (binding.characterListRec.layoutManager as LinearLayoutManager).findFirstVisibleItemPosition()
                    if (loading) {
                        if (visibleItemCount + pastItemsVisible >= totalItemCount) {
                            loading = false

                            binding.progressBar.visibility = View.VISIBLE
                            page++
                            viewModel.fetchDataFromRemoteApi(page)
                            loading = true
                        }
                    }
                }
            }
        })
    }
}