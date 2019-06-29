package com.varol.boynews.base

import com.varol.boynews.util.listener.ItemClickListener

class AdapterBuilder<ModelType>(
    val itemList: List<ModelType>,
    val layoutId: Int,
    val itemClickListener: ItemClickListener<ModelType>?
) {

    fun build(): BaseRecyclerAdapter<ModelType> {
        val baseAdapter = BaseRecyclerAdapter(itemList, layoutId, itemClickListener)
        baseAdapter.updateData(itemList)
        return baseAdapter
    }


}