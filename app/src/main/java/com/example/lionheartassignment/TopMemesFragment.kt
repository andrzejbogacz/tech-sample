/*
 * Copyright 2018 Google LLC
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.example.lionheartassignment

import JsonBase
import Memes
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.lionheartassignment.network.HttpConstants
import com.google.gson.Gson


class TopMemesFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.fragment_top_memes, container, false)

        getTopMemes()

        return view
    }


    private fun getTopMemes() {
        val request = StringRequest(Request.Method.GET, HttpConstants.BASE_URL,  Response.Listener { response ->

            var memesModel = Gson().fromJson(response, JsonBase::class.java)

            var movieList = memesModel.data.memes

            for (item: Memes in movieList) {
               Log.d("TAG", item.name)
            }

            //todo add items to recyclerView

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
