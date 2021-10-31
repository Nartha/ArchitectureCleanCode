package com.example.thelynx.service.koinmodule

import com.example.thelynx.ui.githubuserlist.GitHubUserListViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { GitHubUserListViewModel(get(), get()) }
}