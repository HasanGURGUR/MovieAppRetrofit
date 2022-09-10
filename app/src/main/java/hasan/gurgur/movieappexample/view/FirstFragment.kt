package hasan.gurgur.movieappexample.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import hasan.gurgur.movieappexample.databinding.FragmentFirstBinding
import hasan.gurgur.movieappexample.viewmodel.CharacterListViewModel


class FirstFragment : Fragment() {
    private var _binding: FragmentFirstBinding? = null
    private val binding get() = _binding!!

    private lateinit var viewModel: CharacterListViewModel
    lateinit var characterListAdapter: CharacterListAdapter

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
        viewModel.fetchDataFromRemoteApi("1")

        viewModel.upcomingMoviesModel.observe(requireActivity()) {
            characterListAdapter.submitList(it.results)
        }

        return binding.root
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
    private fun initAdapter() {
        characterListAdapter = CharacterListAdapter {
            Toast.makeText(requireContext(), it?.original_title, Toast.LENGTH_SHORT).show()
        }

        binding.characterListRec.layoutManager =
            LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        binding.characterListRec.adapter = characterListAdapter
    }
}