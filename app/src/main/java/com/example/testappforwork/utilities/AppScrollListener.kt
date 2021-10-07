package com.example.testappforwork.utilities

import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class AppScrollListener(
    private val layoutManager: LinearLayoutManager,
    private val onLoadMore: () -> Unit
) : RecyclerView.OnScrollListener() {
    var isLoading = true

    override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
        super.onScrolled(recyclerView, dx, dy)
        val visibleItemCount = layoutManager.childCount //смотрим сколько элементов на экране
        val totalItemCount = layoutManager.itemCount //сколько всего элементов
        val firstVisibleItems =
            layoutManager.findFirstVisibleItemPosition() //какая позиция первого элемента
        val threshold = 0
        if (!isLoading) { //проверяем, грузим мы что-то или нет, эта переменная должна быть вне класса  OnScrollListener
            if (visibleItemCount + firstVisibleItems + threshold >= totalItemCount) {
                isLoading = true //ставим флаг что мы попросили еще элемены
                onLoadMore()
            }
        }
    }
}