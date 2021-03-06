package com.example.dotaheroes.view.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import androidx.recyclerview.widget.RecyclerView
import com.example.dotaheroes.api.RetrofitHelper
import com.example.dotaheroes.api.models.HeroApiResponse
import com.example.dotaheroes.api.models.HeroApiResponse.Companion.transformAttribute
import com.example.dotaheroes.core.Extensions.ifNull
import com.example.dotaheroes.databinding.ItemHeroListBinding
import com.squareup.picasso.Picasso

class HeroListAdapter(
    val onClickHero: (HeroApiResponse) -> Unit
) : RecyclerView.Adapter<HeroListAdapter.ViewHolder>(), Filterable {

    private var items: MutableList<HeroApiResponse> = mutableListOf()
    private var itemsFiltered: MutableList<HeroApiResponse> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = ItemHeroListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.setIsRecyclable(false)
        holder.bind(items[position])
    }

    override fun getItemCount(): Int = items.size

    fun setData(data: List<HeroApiResponse>) {
        items.clear()
        itemsFiltered.clear()
        items.addAll(data)
        itemsFiltered.addAll(data)
        notifyDataSetChanged()
        //notifyItemRangeChanged(0, items.size)
    }

    inner class ViewHolder(private val view: ItemHeroListBinding) : RecyclerView.ViewHolder(view.root) {
        fun bind(item: HeroApiResponse) {
            view.nameHeroTextView.text = item.localizedName
            view.attributeHeroTextView.text = item.primaryAttr.transformAttribute()

            Picasso.get()
                .load(RetrofitHelper.BASE_URL + item.image)
                .into(view.imageViewHero)

            view.root.setOnClickListener { onClickHero(item) }
        }
    }

    override fun getFilter(): Filter {
        return object : Filter() {
            override fun performFiltering(constraint: CharSequence?): FilterResults {
                val charString = constraint.toString().ifNull { String() }
                items = if (charString.isEmpty()) {
                    itemsFiltered
                } else {
                    val filteredList = ArrayList<HeroApiResponse>()
                    filteredList.addAll(
                        itemsFiltered.filter { it.name.contains(charString) }
                    )
                    filteredList
                }
                return FilterResults().apply { values = items }
            }

            override fun publishResults(text: CharSequence?, results: FilterResults?) {
                items = results?.values.ifNull { mutableListOf<HeroApiResponse>() } as MutableList<HeroApiResponse>
                notifyDataSetChanged()
            }
        }
    }

}