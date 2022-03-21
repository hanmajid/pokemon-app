package com.hanmajid.android.pokemonapp.ui.list

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.LoadState
import androidx.paging.LoadStateAdapter
import androidx.recyclerview.widget.RecyclerView
import com.hanmajid.android.pokemonapp.databinding.ItemPagingFooterBinding

/**
 * Adapter for Paging footer.
 */
class PagingFooterAdapter(
    private val retry: () -> Unit
) : LoadStateAdapter<PagingFooterAdapter.PagingFooterViewHolder>() {
    override fun onBindViewHolder(holder: PagingFooterViewHolder, loadState: LoadState) {
        holder.bind(loadState)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        loadState: LoadState
    ): PagingFooterViewHolder {
        return PagingFooterViewHolder.create(parent, retry)
    }

    class PagingFooterViewHolder(
        private val binding: ItemPagingFooterBinding,
        retry: () -> Unit
    ) : RecyclerView.ViewHolder(binding.root) {

        init {
            binding.retryButton.setOnClickListener { retry.invoke() }
        }

        fun bind(loadState: LoadState) {
            if (loadState is LoadState.Error) {
                binding.errorMessage = loadState.error.localizedMessage
            } else {
                binding.errorMessage = null
            }
            binding.isLoading = loadState is LoadState.Loading
        }

        companion object {
            fun create(parent: ViewGroup, retry: () -> Unit): PagingFooterViewHolder {
                val binding = ItemPagingFooterBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
                return PagingFooterViewHolder(binding, retry)
            }
        }
    }

}