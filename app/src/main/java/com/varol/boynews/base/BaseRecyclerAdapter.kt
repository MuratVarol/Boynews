package com.varol.boynews.base

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.RecyclerView
import com.varol.boynews.BR
import com.varol.boynews.R
import com.varol.boynews.data.models.SourceModel
import com.varol.boynews.data.view_entity.NewsViewEntity
import com.varol.boynews.util.listener.ItemClickListener
import kotlinx.android.synthetic.main.item_news_list.view.*

class BaseRecyclerAdapter<ModelType>(
    var modelList: List<ModelType>,
    val itemLayoutId: Int,
    val itemClickListener: ItemClickListener<ModelType>?,
    val addToBookmarkListener: ItemClickListener<ModelType>?
) : RecyclerView.Adapter<BaseRecyclerAdapter<ModelType>.BaseViewHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): BaseViewHolder {
        val layoutInflater: LayoutInflater = LayoutInflater.from(parent.context)
        val binding: ViewDataBinding =
            DataBindingUtil.inflate(layoutInflater, itemLayoutId, parent, false)
        return BaseViewHolder(binding)

    }

    inner class BaseViewHolder(val itemBinding: ViewDataBinding) :
        RecyclerView.ViewHolder(itemBinding.root)


    override fun getItemCount(): Int = modelList.size


    override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {
        val model = modelList[position]
        /**
         * Adding to bookmark ability killed the generic structure of Adapter
         * Below code is workaround for add to bookmark ability
         * TODO: turn back here and move below job to independent class
         */
        if (model is NewsViewEntity) {
            holder.itemBinding.root.tv_add_to_list?.setOnClickListener { view ->
                model.isAddedToReadList = model.isAddedToReadList.not()
                addToBookmarkListener?.onItemClick(view, model, position)
                notifyItemChanged(position)
            }
        }
        holder.itemBinding.setVariable(BR.model, model)
        holder.itemBinding.root.setOnClickListener { view ->
            itemClickListener?.onItemClick(view, model, position)
        }
    }

    fun updateData(list: List<ModelType>) {
        modelList = list
        notifyDataSetChanged()
    }

    override fun getItemViewType(position: Int): Int {
        val model = modelList[position]
        return getViewType(model)

    }

    private fun getViewType(model: ModelType): Int {
        return when (model) {
            is NewsViewEntity -> R.layout.item_news_list
            is SourceModel -> R.layout.item_sources_list
            else -> R.layout.item_sources_list
        }
    }

}