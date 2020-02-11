package com.example.lionheartassignment.ui

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.example.lionheartassignment.R
import com.example.lionheartassignment.adapters.BlankMemesAdapter
import com.example.lionheartassignment.remote.HttpConstants
import com.example.lionheartassignment.remote.VolleySingleton
import com.example.lionheartassignment.remote.models.GetAllMemesBase
import com.example.lionheartassignment.remote.models.Memes
import com.google.gson.Gson
import kotlinx.android.synthetic.main.fragment_create_memes.*

class MemeTemplatesFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.fragment_create_memes, container, false)

        getMemeTemplates()

        return view
    }

    private fun getMemeTemplates() {
        val request = StringRequest(
            Request.Method.GET,
            HttpConstants.GET_MEMES_BASE_URL,
            Response.Listener { response ->

                val memesModel = Gson().fromJson(response, GetAllMemesBase::class.java)

                val memeList = memesModel.data.memes
                val sortedMemeList = sortDoubleBoxes(memeList)

                for (item: Memes in memeList) {
                    Log.d("TAG", "${item.name} ${item.box_count}")
                }

                recycler_view_create_meme.layoutManager = LinearLayoutManager(
                    context,
                    LinearLayout.VERTICAL, false
                )

                recycler_view_create_meme.adapter =
                    BlankMemesAdapter(
                        sortedMemeList,
                        context!!
                    )

            }, Response.ErrorListener { error ->
                loadToast(error.message)
                Log.d("TAG", error.message!!)

            })

        VolleySingleton.requestQueue.add(request)
    }

    // sorting for double boxed memes only
    private fun sortDoubleBoxes(memeList: List<Memes>): List<Memes> {
        val mListOfMemes: MutableList<Memes> = mutableListOf()
        for (item: Memes in memeList) {
            if (item.box_count == 2)
                mListOfMemes.add(item)
        }
        return mListOfMemes
    }

    private fun loadToast(content: String?) {
        Toast.makeText(context, content, Toast.LENGTH_SHORT).show()
    }
}