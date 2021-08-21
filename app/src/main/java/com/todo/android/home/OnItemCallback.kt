package com.todo.android.home

import com.todo.android.database.Item

interface OnItemCallback {
    fun onClickedCheckBox(item: Item.ContentEntity)
    fun onClickedItem(item: Item.ContentEntity)
}