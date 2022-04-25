package com.valentine.challengechapter5.UI_ListingMovie

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.valentine.challengechapter5.R
import com.valentine.challengechapter5.databinding.FragmentDetailBinding
import com.valentine.challengechapter5.model.Result


class DetailFragment : Fragment() {
    private var _binding: FragmentDetailBinding? = null
    private val binding get() = _binding!!
    private val arguments: DetailFragmentArgs by navArgs()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentDetailBinding.inflate(layoutInflater)
        return binding.root
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val data = arguments?.detailMovie

        binding.apply {

            txtTitle.text = data?.title
            txtMediaType.text = data?.media_type
            txtDate.text = data?.date
            txtOverview.text = data?.overview
            Glide.with(view.context).load("https://image.tmdb.org/t/p/w780/"+arguments.detailMovie.image).into(imgMovie)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}


