package com.salonip.moviereviewsapp

import androidx.annotation.Nullable
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import com.salonip.moviereviewsapp.data.database.ReviewsFavoritesDatabase
import com.salonip.moviereviewsapp.data.domain.Review
import com.salonip.moviereviewsapp.data.repository.LocalDataSourceRepository
import com.salonip.moviereviewsapp.view.home.HomeViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import java.util.concurrent.CountDownLatch


@RunWith(AndroidJUnit4::class)
class HomeViewModelTest {
    private lateinit var homeViewModel: HomeViewModel

    @Before
    fun setUp() {
        val dao =
            ReviewsFavoritesDatabase.getDatabase(InstrumentationRegistry.getInstrumentation().targetContext).reviewsFavoritesDao
        val repository = LocalDataSourceRepository(dao)
        homeViewModel = HomeViewModel(repository)
    }

    @Test
    fun testGetAllReviews() {
        var list = listOf<Review>()
        val latch = CountDownLatch(1)

        val observer: Observer<ArrayList<Review>> = object : Observer<ArrayList<Review>> {
            override fun onChanged(@Nullable observeList: ArrayList<Review>) {
                list = observeList
                latch.countDown()
                homeViewModel.movieCriticsReviewData.removeObserver(this)
            }
        }
        GlobalScope.launch(Dispatchers.Main) {
            homeViewModel.movieCriticsReviewData.observeForever(observer)
            homeViewModel.getReviews()
            latch.await()
            assert(list.isNotEmpty())
        }
    }


    @Throws(InterruptedException::class)
    fun <T> getOrAwaitValue(liveData: LiveData<T>): T? {
        val data = arrayOfNulls<Any>(1)
        val latch = CountDownLatch(1)
        val observer: Observer<T> = object : Observer<T> {
            override fun onChanged(@Nullable o: T) {
                data[0] = o
                latch.countDown()
                liveData.removeObserver(this)
            }
        }
        liveData.observeForever(observer)
        latch.await()
        return data[0] as T?
    }

}