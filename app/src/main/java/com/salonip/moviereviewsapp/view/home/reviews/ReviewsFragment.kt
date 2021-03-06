package com.salonip.moviereviewsapp.view.home.reviews

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.salonip.moviereviewsapp.R
import com.salonip.moviereviewsapp.data.database.ReviewsFavoritesDatabase
import com.salonip.moviereviewsapp.data.domain.Review
import com.salonip.moviereviewsapp.data.repository.LocalDataSourceRepository
import com.salonip.moviereviewsapp.extensions.navigateActionDirectionWithAnimation
import com.salonip.moviereviewsapp.utils.visibilities
import com.salonip.moviereviewsapp.view.home.HomeFragmentDirections
import com.salonip.moviereviewsapp.view.home.HomeViewModel
import com.salonip.moviereviewsapp.view.home.HomeViewModelFactory
import com.salonip.moviereviewsapp.view.home.common.OnReviewClickListener
import com.salonip.moviereviewsapp.view.home.reviews.adapter.ReviewsAdapter
import kotlinx.android.synthetic.main.fragment_reviews.*

class ReviewsFragment : Fragment(),
    OnReviewClickListener {

    private val homeViewModel: HomeViewModel by activityViewModels(
        factoryProducer = {
            val database = ReviewsFavoritesDatabase.getDatabase(requireContext())
            HomeViewModelFactory(
                localDataRepository = LocalDataSourceRepository(database.reviewsFavoritesDao)
            )
        }
    )

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_reviews, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        homeViewModel.movieCriticsReviewData.observe(viewLifecycleOwner, Observer { reviews ->
            if (reviews == null) {
                visibilities(invisible = progress_bar, visible = iv_message_error)
            } else {
                visibilities(invisible = progress_bar, visible = rcy_movies_critics_reviews)
                val reviewsAdapter = ReviewsAdapter(reviews)

                homeViewModel.searchReviewData.observe(viewLifecycleOwner, Observer { titleSearch ->
                    reviewsAdapter.filter.filter(titleSearch)
                })

                reviewsAdapter.setOnItemClickListener(this)
                rcy_movies_critics_reviews.apply {
                    adapter = reviewsAdapter
                    layoutManager = LinearLayoutManager(context)
                }
            }
        })
        homeViewModel.getReviews()
    }

    override fun onItemClick(review: Review) {
        val directions = HomeFragmentDirections.actionHomeFragmentToDetailsFragment(review)
        findNavController().navigateActionDirectionWithAnimation(directions)
    }
}