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

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide
import com.example.lionheartassignment.R
import com.example.lionheartassignment.remote.models.Memes
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

        return view
    }

    private fun loadMeme(
        meme: Memes,
        view: View
    ) {
        Glide.with(context!!).load(meme.url).into(view.iv_generator_thumbnail)
        view.title.text = meme.name
    }


    private fun loadMemeTemplate(url: String?, text1: String? = null, text2: String? = null) {
        //todo load generated template
    }


}
