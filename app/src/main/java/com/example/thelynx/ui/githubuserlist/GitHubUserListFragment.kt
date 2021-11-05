package com.example.thelynx.ui.githubuserlist

import android.view.Menu
import android.view.MenuInflater
import android.widget.Toast
import androidx.appcompat.widget.AppCompatImageButton
import androidx.appcompat.widget.AppCompatImageView
import androidx.constraintlayout.utils.widget.ImageFilterView
import com.example.thelynx.R
import com.example.thelynx.databinding.FragmentGithubUserListBinding
import com.example.thelynx.room.table.UserListEntity
import com.example.thelynx.ui.base.BaseFragment
import com.example.thelynx.ui.dialog.FilterDialog
import com.example.thelynx.ui.extension.filterOptionMenu
import com.example.thelynx.utils.SharedPreference
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.sharedViewModel
import timber.log.Timber

class GitHubUserListFragment : BaseFragment<FragmentGithubUserListBinding>() {

    private val viewModel by sharedViewModel<GitHubUserListViewModel>()
    private val sharedPref: SharedPreference by inject()

    override fun getViewBinding(): FragmentGithubUserListBinding =
        FragmentGithubUserListBinding.inflate(layoutInflater)

    override fun observeData() {
        viewModel.getAllUser()
    }

    override fun setView() {
        viewModel.userListEntity.observe(this, {
            showGitHubUserList(it.toMutableList())
        })
        viewModel.filterASC.observe(this, {
            showGitHubUserList(it.toMutableList())
        })
        viewModel.filterDESC.observe(this, {
            showGitHubUserList(it.toMutableList())
        })
    }

    private fun showGitHubUserList(userListEntity: MutableList<UserListEntity>) {
        Timber.i("DataResponse: $userListEntity")
        binding.rvGithubUserList.apply {
            adapter = GitHubUserAdapter(userListEntity, sharedPref) { nodeId, clickLike ->
                viewModel.updateClickLikeUserListEntity(nodeId, clickLike)
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        filterOptionMenu(inflater, R.menu.main_menu, menu, R.id.filter) {
            FilterDialog().also {
                it.clickFilterASC.observe(this, {
                    viewModel.getAlphabetizedByASC("GithubUserListFragment")
                })
                it.clickFilterDESC.observe(this, {
                    viewModel.getAlphabetizedByDESC("GithubUserListFragment")
                })
            }.show(childFragmentManager, "FilterDialog")
        }
    }
}