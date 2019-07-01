package com.varol.boynews.base

import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView

abstract class BaseViewHolder2(val itemBinding: ViewDataBinding) :
    RecyclerView.ViewHolder(itemBinding.root) {

    abstract fun bindData(position: Int)

}