package com.dongnaoedu.flowpractice.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.dongnaoedu.flowpractice.databinding.ItemUserBinding
import com.dongnaoedu.flowpractice.db.User

/**
 *
 * @author ningchuanqi
 * @version V1.0
 */
class UserAdapter(private val context: Context) : RecyclerView.Adapter<BindingViewHolder>() {

    private val data = ArrayList<User>()

    fun setData(data: List<User>) {
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
        binding.text.text = "${item.bid}, 书名：${item.catename}, 作者：${item.author}, 行动1：${item.action_section?.get(0)?.title}, " +
                "行动2：${item.action_section?.get(1)?.title}," +
                "行动3：${item.action_section?.get(2)?.title}," +
                "标签：${item.tagsary?.get(0)}  ${item.tagsary?.get(1)}  ${item.tagsary?.get(2)}"
    }

    override fun getItemCount() = data.size
}