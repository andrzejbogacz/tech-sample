package com.example.lionheartassignment.adapters

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.lionheartassignment.R
import com.example.lionheartassignment.remote.models.Memes
import kotlinx.android.synthetic.main.top_meme_card.view.*

class BlankMemesAdapter(private val memesList: List<Memes>, private val context: Context) :
    RecyclerView.Adapter<BlankMemesAdapter.MyViewHolder>() {

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {

        holder.bindItems(memesList[position])
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val v = LayoutInflater.from(context).inflate(
            R.layout.top_meme_card,
            parent, false
        )
        return MyViewHolder(
            v
        )
    }

    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        @SuppressLint("SetTextI18n")
        fun bindItems(currentMeme: Memes) {
            Glide.with(itemView.context).load(currentMeme.url).into(itemView.thumbnail)

            //todo export
            itemView.title.text =
                itemView.context.getString(R.string.template_title) + currentMeme.name

            itemView.setOnClickListener {

                val memeBundle = bundleOf("memeTemplate" to currentMeme)

                Navigation.findNavController(itemView)
                    .navigate(
                        R.id.action_create_memes_fragment_to_memeGeneratorFragment,
                        memeBundle
                    )
            }
        }
    }

    override fun getItemCount(): Int {
        return memesList.size
    }
}