package com.dongnaoedu.flowpractice.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.dongnaoedu.flowpractice.databinding.ItemUserBinding
import com.dongnaoedu.flowpractice.db.User
import com.dongnaoedu.flowpractice.model.Article

/**
 *
 * @author ningchuanqi
 * @version V1.0
 */
class ArticleAdapter(private val context: Context) : RecyclerView.Adapter<BindingViewHolder>() {

    private val data = ArrayList<Article>()

    fun setData(data: List<Article>) {
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
        binding.text.text = item.text
    }

    override fun getItemCount() = data.size
}