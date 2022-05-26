package com.example.dotaheroes.view.adapters

import android.view.LayoutInflater
import android.view.RoundedCorner
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.dotaheroes.api.RetrofitHelper
import com.example.dotaheroes.api.models.Hero
import com.example.dotaheroes.api.models.Hero.Companion.transformAttribute
import com.example.dotaheroes.databinding.ItemHeroListBinding
import com.squareup.picasso.Picasso

class HeroListAdapter(
    val onClickHero: (Hero) -> Unit
) : RecyclerView.Adapter<HeroListAdapter.ViewHolder>() {

    private val items: MutableList<Hero> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = ItemHeroListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.setIsRecyclable(false)
        holder.bind(items[position])
    }

    override fun getItemCount(): Int = items.size

    fun setData(data: List<Hero>){
        items.clear()
        items.addAll(data)
        notifyDataSetChanged()
        //notifyItemRangeChanged(0, items.size)
    }

    inner class ViewHolder(private val view: ItemHeroListBinding) : RecyclerView.ViewHolder(view.root) {
        fun bind(item: Hero) {
            view.nameHeroTextView.text = item.localizedName
            view.attributeHeroTextView.text = item.primaryAttr.transformAttribute()

            Picasso.with(view.root.context)
                .load(RetrofitHelper.BASE_URL+item.image)
                .into(view.imageViewHero)

            view.root.setOnClickListener { onClickHero(item) }
        }
    }

}