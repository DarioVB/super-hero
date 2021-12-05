package com.coppel.superhero.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.coppel.superhero.databinding.ItemHeroAdapterBinding
import com.coppel.superhero.models.Hero


class FavoritesAdapter(private val onClickListener: OnClickListener) : ListAdapter<Hero, FavoritesAdapter.FavouriteHeroViewHolder>(HeroDiffCallBack()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavouriteHeroViewHolder {
        return FavouriteHeroViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: FavouriteHeroViewHolder, position: Int) {
        val hero = getItem(position)
        holder.itemView.setOnClickListener {
            onClickListener.onClick(hero)
        }
        holder.bind(hero)
    }

    class FavouriteHeroViewHolder private constructor(val binding: ItemHeroAdapterBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(favouriteHero: Hero) {
            binding.hero = favouriteHero
            binding.executePendingBindings()
        }

        companion object {
            fun from(parent: ViewGroup): FavouriteHeroViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = ItemHeroAdapterBinding.inflate(layoutInflater, parent, false)
                return FavouriteHeroViewHolder(binding)
            }
        }
    }

    class OnClickListener(val clickListener : (hero: Hero) -> Unit){
        fun onClick(favouriteHero: Hero) = clickListener(favouriteHero)
    }

    class HeroDiffCallBack : DiffUtil.ItemCallback<Hero>(){
        override fun areItemsTheSame(oldItem: Hero, newItem: Hero): Boolean {
            return oldItem.name == newItem.name
        }

        override fun areContentsTheSame(oldItem: Hero, newItem: Hero): Boolean {
            return oldItem == newItem
        }
    }
}