package com.example.thelynx.ui.favourite

import android.annotation.SuppressLint
import android.view.Menu
import android.view.MenuInflater
import androidx.lifecycle.lifecycleScope
import com.example.thelynx.R
import com.example.thelynx.databinding.FragmentFavouriteBinding
import com.example.thelynx.room.table.UserListEntity
import com.example.thelynx.ui.base.BaseFragment
import com.example.thelynx.ui.dialog.FilterDialog
import com.example.thelynx.ui.extension.filterOptionMenu
import com.example.thelynx.ui.githubuserlist.GitHubUserAdapter
import com.example.thelynx.ui.githubuserlist.GitHubUserListViewModel
import com.example.thelynx.utils.SharedPreference
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.sharedViewModel
import timber.log.Timber

class FavouriteFragment : BaseFragment<FragmentFavouriteBinding>() {

    private val viewModel by sharedViewModel<GitHubUserListViewModel>()
    private val sharedPref: SharedPreference by inject()
    private var userListFavourite = mutableListOf<UserListEntity>()

    override fun getViewBinding(): FragmentFavouriteBinding =
        FragmentFavouriteBinding.inflate(layoutInflater)

    override fun observeData() {
        viewModel.filterLike()
    }

    override fun setView() {
        viewModel.userListFavourite.observe(this, {
            userListFavourite = it.toMutableList()
            showFavouriteUserList(userListFavourite)
        })
        viewModel.filterASC.observe(this, {
            showFavouriteUserList(it.toMutableList())
        })
        viewModel.filterDESC.observe(this, {
            showFavouriteUserList(it.toMutableList())
        })
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun showFavouriteUserList(favouriteUserList: MutableList<UserListEntity>) {
        Timber.i("DataResponse: $favouriteUserList")
        binding.rvFavouriteUserList.apply {
            adapter = GitHubUserAdapter(favouriteUserList, sharedPref) { nodeId, clickLike ->
                viewModel.updateClickLikeUserListEntity(nodeId, clickLike)
                lifecycleScope.launch {
                    delay(300)
                    viewModel.filterLike()
                }
            }
            this.adapter?.notifyDataSetChanged()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        filterOptionMenu(inflater, R.menu.main_menu, menu, R.id.filter) {
            if (userListFavourite.isNotEmpty() && userListFavourite.size != 1) {
                FilterDialog().also {
                    it.clickFilterASC.observe(it, {
                        viewModel.getAlphabetizedByASC("FavouriteFragment")
                    })
                    it.clickFilterDESC.observe(it, {
                        viewModel.getAlphabetizedByDESC("FavouriteFragment")
                    })
                }.show(childFragmentManager, "FilterDialog")
            }
        }
    }

}