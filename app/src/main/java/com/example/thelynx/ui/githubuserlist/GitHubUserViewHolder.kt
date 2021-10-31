package com.example.thelynx.ui.githubuserlist

import android.content.Context
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.thelynx.R
import com.example.thelynx.utils.SharedPreference
import com.example.thelynx.utils.loadImage
import com.example.thelynx.databinding.ItemGithubUserBinding
import com.example.thelynx.service.core.api.UserList
import com.example.thelynx.ui.base.BaseRecyclerView
import timber.log.Timber
import java.lang.RuntimeException

class GitHubUserViewHolder(
    private val binding: ItemGithubUserBinding,
    private val context: Context,
    private val shared: SharedPreference,
    private val itemClickLike: (() -> Unit)
) :
    RecyclerView.ViewHolder(binding.root), BaseRecyclerView.Binder<String> {

    override fun bind(dataItem: Any?, position: Int) {
        val item = (dataItem as ArrayList<*>)[position]
        (item as UserList).apply {
            Timber.i("item: ${this.login} -> ${this.clickLike}")
            binding.tvLoginName.text = this.login
            binding.tvUrl.text = this.url
            context.loadImage(this.avatar_url, binding.imgAvatarUrl)
            setViewClickLike(item.clickLike)
        }

        binding.imgFavourite.setOnClickListener {
            item.clickLike = !shared.clickLike
            Timber.i("item: ${item.login} -> ${item.clickLike}")
            itemClickLike.invoke()
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

