package com.example.lionheartassignment

import android.content.Context
import android.view.*
import androidx.recyclerview.widget.RecyclerView
import android.widget.PopupMenu
import android.widget.Toast
import com.bumptech.glide.Glide
import com.example.lionheartassignment.dto.Memes
import kotlinx.android.synthetic.main.top_meme_card.view.*

class BlankMemesAdapter(private val memesList: List<Memes>, private val context: Context) : RecyclerView.Adapter<BlankMemesAdapter.MyViewHolder>() {

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {

        holder.bindItems(memesList[position])
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val v = LayoutInflater.from(context).inflate(R.layout.top_meme_card,
                parent, false)
        return MyViewHolder(v)
    }

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bindItems(currentMeme: Memes) {
            Glide.with(itemView.context).load(currentMeme.url).into(itemView.thumbnail)

            //todo export
            itemView.title.text = "Template Title: ${currentMeme.name}"
        }
    }

    override fun getItemCount(): Int {
        return memesList.size
    }
}