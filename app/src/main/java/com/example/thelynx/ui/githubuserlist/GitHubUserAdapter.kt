package com.example.thelynx.ui.githubuserlist

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.thelynx.utils.SharedPreference
import com.example.thelynx.databinding.ItemGithubUserBinding
import com.example.thelynx.service.core.api.UserList
import com.example.thelynx.ui.base.BaseRecyclerView

class GitHubUserAdapter(
    private val githubUserList: ArrayList<UserList>,
    private val sharedPref: SharedPreference,
    private val itemClickLike: (() -> Unit)
) :
    BaseRecyclerView<GitHubUserViewHolder>() {

    override fun getViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view = ItemGithubUserBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return GitHubUserViewHolder(view, parent.context, sharedPref) { itemClickLike.invoke() }
    }

    override fun itemList(): MutableList<*> = githubUserList

}