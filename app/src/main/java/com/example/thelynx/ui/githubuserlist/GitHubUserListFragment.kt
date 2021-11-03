package com.example.thelynx.ui.githubuserlist

import com.example.thelynx.databinding.FragmentGithubUserListBinding
import com.example.thelynx.room.table.UserListEntity
import com.example.thelynx.ui.base.BaseFragment
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
    }

    private fun showGitHubUserList(userListEntity: MutableList<UserListEntity>) {
        Timber.i("DataResponse: $userListEntity")
        binding.rvGithubUserList.apply {
            adapter = GitHubUserAdapter(userListEntity, sharedPref) { nodeId, clickLike ->
                viewModel.updateClickLikeUserListEntity(nodeId, clickLike)
            }
        }
    }
}