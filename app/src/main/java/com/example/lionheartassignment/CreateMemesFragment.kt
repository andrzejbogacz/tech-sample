package com.example.lionheartassignment

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
import com.android.volley.toolbox.Volley
import com.example.lionheartassignment.dto.JsonBase
import com.example.lionheartassignment.dto.Memes
import com.example.lionheartassignment.network.HttpConstants
import com.google.gson.Gson
import kotlinx.android.synthetic.main.fragment_create_memes.*
import kotlinx.android.synthetic.main.fragment_top_memes.*

class CreateMemesFragment : Fragment() {

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
        val request = StringRequest(Request.Method.GET, HttpConstants.BASE_URL,  Response.Listener { response ->

            var memesModel = Gson().fromJson(response, JsonBase::class.java)

            var movieList = memesModel.data.memes

            for (item: Memes in movieList) {
                Log.d("TAG", item.name)
            }

            recycler_view_create_meme.layoutManager= LinearLayoutManager(context,
                LinearLayout.VERTICAL,false)

            recycler_view_create_meme.adapter = BlankMemesAdapter(movieList,context!!)

        }, Response.ErrorListener { error ->
            loadToast(error.message)
            Log.d("TAG", error.message!!)

        })
        //
        Volley.newRequestQueue(context).add(request)
    }

    private fun loadToast(content: String?) {
        Toast.makeText(context, content, Toast.LENGTH_SHORT).show()
    }

}