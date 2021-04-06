package library.presentation.core

import androidx.recyclerview.widget.RecyclerView

fun RecyclerView.bindScrollListener(bind: Boolean, onScrollListener : RecyclerView.OnScrollListener  ){
    removeOnScrollListener(onScrollListener)
    if(bind){
        addOnScrollListener(onScrollListener)
    }
}
