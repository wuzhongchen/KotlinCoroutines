package com.dongnaoedu.flowpractice.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.dongnaoedu.flowpractice.databinding.ItemUserBinding
import com.dongnaoedu.flowpractice.db.User
import com.dongnaoedu.flowpractice.model.SearchEntity
import com.dongnaoedu.flowpractice.model.SearchResultEntity

/**
 *
 * @author ningchuanqi
 * @version V1.0
 */
class ArticleAdapter(private val context: Context) : RecyclerView.Adapter<BindingViewHolder>() {

    private val data = ArrayList<SearchResultEntity.SearchItem>()

    fun setData(data: List<SearchResultEntity.SearchItem>) {
        this.data.clear()
        this.data.addAll(data)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BindingViewHolder {
        val binding = ItemUserBinding.inflate(LayoutInflater.from(context), parent, false)
        return BindingViewHolder(binding)
    }

    override fun onBindViewHolder(holder: BindingViewHolder, position: Int) {
        val item = data[position]
        val binding = holder.binding as ItemUserBinding
        binding.text.text = item.catename.plus("作者:  ").plus(item.authorname)
    }

    override fun getItemCount() = data.size
}