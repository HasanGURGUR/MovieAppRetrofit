package hasan.gurgur.movieappexample.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import dagger.hilt.android.AndroidEntryPoint
import hasan.gurgur.movieappexample.R
import hasan.gurgur.movieappexample.databinding.FragmentDetailBinding

@AndroidEntryPoint
class DetailFragment : Fragment() {

    val args: DetailFragmentArgs by navArgs()


    private var _binding: FragmentDetailBinding? = null
    private val binding get() = _binding!!
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentDetailBinding.inflate(inflater, container, false)
        binding.movie = args.movieDetail

        return binding.root
    }


}