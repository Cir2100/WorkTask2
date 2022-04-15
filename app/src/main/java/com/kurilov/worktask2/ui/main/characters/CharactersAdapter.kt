package com.kurilov.worktask2.ui.main.characters

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.kurilov.worktask2.R
import com.kurilov.worktask2.data.classes.Characther
import com.squareup.picasso.Picasso
import androidx.recyclerview.widget.ListUpdateCallback




class CharactersAdapter(
    private val listener : ActionListener) :
    RecyclerView.Adapter<CharactersAdapter.MyViewHolder>() {

    private var items : ArrayList<Characther> = arrayListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val itemView =
            LayoutInflater.from(parent.context)
                .inflate(R.layout.fragment_characters_list_item, parent, false)
        return MyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val item = items[position]

        holder.characterNameTextView.text = item.name
        holder.characterNameSpeciesView.text = "Раса: ${item.species}"
        holder.characterNameGenderView.text = "Пол: ${item.gender}"

        Picasso.get()
            .load(item.image)
            .into(holder.characterImageView)

        holder.itemView.setOnClickListener{
            val pos = holder.bindingAdapterPosition
            if(pos != DiffUtil.DiffResult.NO_POSITION) {
                listener.onClickItem(item.id)
            }
        }

        if(holder.bindingAdapterPosition == itemCount - 1) {
            //isLoading = true;
            listener.onLoadMore(items.last().id)
        }

    }

    fun addItems(newItems: List<Characther>) {
        val oldPos = items.lastIndex
        items.addAll(newItems)
        notifyItemRangeInserted(oldPos, newItems.size)
    }

    inner class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val characterImageView: ImageView = itemView.findViewById(R.id.character_item_image_view)
        val characterNameTextView: TextView = itemView.findViewById(R.id.character_item_name)
        val characterNameSpeciesView: TextView = itemView.findViewById(R.id.character_item_species)
        val characterNameGenderView: TextView = itemView.findViewById(R.id.character_item_gender)

    }

    override fun getItemCount() = items.size

    interface ActionListener {
        fun onClickItem(id: Int)

        fun onLoadMore(id: Int)
    }
}