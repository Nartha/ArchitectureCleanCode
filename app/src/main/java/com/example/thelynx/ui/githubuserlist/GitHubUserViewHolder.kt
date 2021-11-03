package com.example.thelynx.ui.githubuserlist

import android.content.Context
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.thelynx.R
import com.example.thelynx.databinding.ItemGithubUserBinding
import com.example.thelynx.room.table.UserListEntity
import com.example.thelynx.ui.base.BaseRecyclerView
import com.example.thelynx.utils.SharedPreference
import com.example.thelynx.utils.loadImage

class GitHubUserViewHolder(
    private val binding: ItemGithubUserBinding,
    private val context: Context,
    private val shared: SharedPreference,
    private val itemClickLike: ((String, Boolean) -> Unit)
) :
    RecyclerView.ViewHolder(binding.root), BaseRecyclerView.Binder<String> {

    private val sharedPref = SharedPreference(context)

    override fun bind(dataItem: Any?, position: Int) {
        val item = (dataItem as ArrayList<*>)[position]
        (item as UserListEntity).apply {
            binding.tvLoginName.text = this.login
            binding.tvUrl.text = this.url
            context.loadImage(this.avatar_url, binding.imgAvatarUrl)
            setViewClickLike(item.clickLike)
        }

        binding.imgFavourite.setOnClickListener {
            sharedPref.clickLike = !sharedPref.clickLike
            item.clickLike = shared.clickLike
            itemClickLike.invoke(item.node_id, item.clickLike)
            setViewClickLike(item.clickLike)
        }
    }

    private fun setViewClickLike(clickLike: Boolean) {
        if (clickLike) {
            binding.imgFavourite.background =
                ContextCompat.getDrawable(context, R.drawable.ic_like)
        } else {
            binding.imgFavourite.background =
                ContextCompat.getDrawable(context, R.drawable.ic_unlike)
        }
    }
}

