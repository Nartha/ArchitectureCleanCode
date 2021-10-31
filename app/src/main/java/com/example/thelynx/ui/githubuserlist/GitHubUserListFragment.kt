package com.example.thelynx.ui.githubuserlist

import android.widget.Toast
import com.example.thelynx.utils.SharedPreference
import com.example.thelynx.databinding.FragmentGithubUserListBinding
import com.example.thelynx.service.core.api.GithubUserList
import com.example.thelynx.ui.base.BaseFragment
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
        viewModel.userList.observe(this, {
            it?.let {
                Timber.i("test: $it")
                if (it.isNotEmpty()) {
                    Timber.i("test1: $it")
                    showGitHubUserList(it as GithubUserList)
                }
                else {
                    Timber.i("test2: $it")
                    viewModel.getDataUserList()
                }
            }
        })
        viewModel.dataGitHubUserList.observe(this, { showGitHubUserList(it) })
    }

    private fun showGitHubUserList(userList: GithubUserList) {

        binding.rvGithubUserList.apply {
            adapter = GitHubUserAdapter(userList, sharedPref) {
                sharedPref.clickLike = !sharedPref.clickLike
                Toast.makeText(
                    requireContext(),
                    sharedPref.clickLike.toString(),
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
        Timber.i("DataResponse: $userList")
    }

}