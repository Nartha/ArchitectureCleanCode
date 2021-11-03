package com.example.thelynx.ui.favourite

import android.annotation.SuppressLint
import com.example.thelynx.databinding.FragmentFavouriteBinding
import com.example.thelynx.room.table.UserListEntity
import com.example.thelynx.ui.base.BaseFragment
import com.example.thelynx.ui.githubuserlist.GitHubUserAdapter
import com.example.thelynx.ui.githubuserlist.GitHubUserListViewModel
import com.example.thelynx.utils.SharedPreference
import okhttp3.internal.notifyAll
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.sharedViewModel
import timber.log.Timber

class FavouriteFragment : BaseFragment<FragmentFavouriteBinding>() {

    private val viewModel by sharedViewModel<GitHubUserListViewModel>()
    private val sharedPref: SharedPreference by inject()

    override fun getViewBinding(): FragmentFavouriteBinding = FragmentFavouriteBinding.inflate(layoutInflater)

    override fun setView() {
        viewModel.filterFavourite.observe(this, {
            showFavouriteUserList(it.toMutableList())
        })
    }

    override fun observeData() {
        viewModel.filterLike()
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun showFavouriteUserList(favouriteUserList: MutableList<UserListEntity>) {
        Timber.i("DataResponse: $favouriteUserList")
        binding.rvFavouriteUserList.apply {
            adapter = GitHubUserAdapter(favouriteUserList, sharedPref) { nodeId, clickLike ->
                viewModel.updateClickLikeUserListEntity(nodeId, clickLike)
            }
            this.adapter?.notifyDataSetChanged()
        }
    }

}