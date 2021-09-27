package com.salonip.moviereviewsapp.view.home.favorites

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.salonip.moviereviewsapp.R
import com.salonip.moviereviewsapp.data.domain.Review
import com.salonip.moviereviewsapp.extensions.navigateActionDirectionWithAnimation
import com.salonip.moviereviewsapp.utils.visibilities
import com.salonip.moviereviewsapp.view.home.HomeFragmentDirections
import com.salonip.moviereviewsapp.view.home.HomeViewModel
import com.salonip.moviereviewsapp.view.home.common.OnReviewClickListener
import com.salonip.moviereviewsapp.view.home.favorites.adapter.FavoritesAdapter
import kotlinx.android.synthetic.main.fragment_favorites.*

class FavoritesFragment : Fragment(), OnReviewClickListener {

    private val homeViewModel: HomeViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_favorites, container, false)
    }

    override fun onViewCreated(
        view: View,
        savedInstanceState: Bundle?
    ) {
        super.onViewCreated(view, savedInstanceState)

        homeViewModel.getMoviesCriticsReviewFavoritesData.observe(viewLifecycleOwner, Observer {
            it?.let { favorites ->
                if (favorites.isEmpty()) {
                    visibilities(invisible = rcy_favorites, visible = iv_list_favorites_empty)
                } else {
                    visibilities(invisible = iv_list_favorites_empty, visible = rcy_favorites)
                    val favoritesAdapter = FavoritesAdapter(favorites)

                    homeViewModel.searchReviewData.observe(viewLifecycleOwner, Observer { titleSearch ->
                        favoritesAdapter.filter.filter(titleSearch)
                    })

                    favoritesAdapter.setOnItemClickListener(this)
                    rcy_favorites.apply {
                        adapter = favoritesAdapter
                        layoutManager = LinearLayoutManager(context)
                    }
                }
            }
        })

        homeViewModel.loadReviewsFavorites()
    }

    override fun onItemClick(review: Review) {
        val directions = HomeFragmentDirections.actionHomeFragmentToDetailsFragment(review)
        findNavController().navigateActionDirectionWithAnimation(directions)
    }

}