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

package com.example.lionheartassignment.ui

import MemeGeneratorResponse
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.android.volley.AuthFailureError
import com.android.volley.Response
import com.android.volley.VolleyLog
import com.android.volley.toolbox.StringRequest
import com.bumptech.glide.Glide
import com.example.lionheartassignment.R
import com.example.lionheartassignment.remote.HttpConstants
import com.example.lionheartassignment.remote.VolleySingleton
import com.example.lionheartassignment.remote.models.Memes
import com.google.gson.Gson
import kotlinx.android.synthetic.main.fragment_meme_generator.*
import kotlinx.android.synthetic.main.fragment_meme_generator.view.*

class MemeGeneratorFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.fragment_meme_generator, container, false)

        val meme: Memes = arguments?.getParcelable("memeTemplate")!!

        loadMeme(meme, view)

        view.btn_generate_meme.setOnClickListener {
            val text1: String = et_text1.text.toString().trim()
            val text2: String = et_text2.text.toString().trim()

            sendGenerateMemePostRequest(view, text1, text2, meme.id.toString())
        }

        return view
    }

    private fun loadMeme(
        meme: Memes,
        view: View
    ) {
        Glide.with(context!!).load(meme.url).into(view.iv_generator_thumbnail)
        view.title.text = meme.name
    }


    private fun sendGenerateMemePostRequest(
        view: View,
        text1: String = " ",
        text2: String = " ",
        templateID: String
    ) {

        val sr: StringRequest = object : StringRequest(
            Method.POST,
            HttpConstants.POST_CAPTION_IMAGE_URL,
            Response.Listener { response ->

                val myData = Gson().fromJson<MemeGeneratorResponse>(
                    response,
                    MemeGeneratorResponse::class.java
                )

                // if text boxes are not null -> update the image
                myData?.data?.url?.run {
                    Glide.with(context!!).load(myData.data.url).into(view.iv_generator_thumbnail)
                }

            },
            Response.ErrorListener { error ->
                VolleyLog.d("TAG", "Error: " + error.message)
                Log.d("TAG", "" + error.message + "," + error.toString())

            }) {

            // input parameters for post request
            override fun getParams(): Map<String, String> {
                val params: MutableMap<String, String> = HashMap()
                params["template_id"] = templateID
                params["username"] = "andi1000"
                params["password"] = "andi1000"
                params["text0"] = text1
                params["text1"] = text2
                return params
            }

            @Throws(AuthFailureError::class)
            override fun getHeaders(): Map<String, String> {
                val headers: MutableMap<String, String> =
                    HashMap()
                headers["Content-Type"] = "application/x-www-form-urlencoded"
                headers["abc"] = "value"
                return headers
            }
        }

        VolleySingleton.requestQueue.add(sr)

    }

}
