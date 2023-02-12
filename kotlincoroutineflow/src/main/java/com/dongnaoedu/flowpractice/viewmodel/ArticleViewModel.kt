package com.dongnaoedu.flowpractice.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.dongnaoedu.flowpractice.model.Article
import com.dongnaoedu.flowpractice.net.RetrofitClient
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.launch

/**
 *
 * @author ningchuanqi
 * @version V1.0
 */
class ArticleViewModel(app: Application) : AndroidViewModel(app) {

    val articles = MutableLiveData<List<Article>>()

    fun searchArticles(key: String) {
        viewModelScope.launch {
            flow {
                val list = RetrofitClient.articleApi.searchArticles(key)
                emit(list)
            }.flowOn(Dispatchers.IO)
                .catch { e -> e.printStackTrace() }
                .collect {
                    articles.setValue(it)
                }
        }
    }

}