package com.example.thelynx.ui.githubuserlist

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.thelynx.databinding.ItemGithubUserBinding
import com.example.thelynx.room.table.UserListEntity
import com.example.thelynx.ui.base.BaseRecyclerView
import com.example.thelynx.utils.SharedPreference

class GitHubUserAdapter(
    private val githubUserListEntity: MutableList<UserListEntity>,
    private val sharedPref: SharedPreference,
    private val itemClickLike: ((String, Boolean) -> Unit)
) :
    BaseRecyclerView<GitHubUserViewHolder>() {


    override fun getViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view = ItemGithubUserBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return GitHubUserViewHolder(view, parent.context, sharedPref) { nodeId, clickLike ->
            itemClickLike.invoke(nodeId, clickLike)
        }
    }

    override fun itemList(): MutableList<*> = githubUserListEntity

}