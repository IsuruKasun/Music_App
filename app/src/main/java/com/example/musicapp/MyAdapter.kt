package com.example.musicapp

import android.app.Activity
import android.media.MediaPlayer
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.core.net.toUri
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso

class MyAdapter (val context: Activity, val dataList: List<Data>):
        RecyclerView.Adapter<MyAdapter.MyViewHolder>() {



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        // create the view if layout manager fails to create the view for the data
        val itemView= LayoutInflater.from(context).inflate(R.layout.each_song, parent, false)
        return MyViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return dataList.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        // populate the data in to view
        val currentData = dataList[position]// now i have access to the current position in the API

        val mediaPlayer = MediaPlayer.create(context, currentData.preview.toUri())
        holder.title.text = currentData.title

        Picasso.get().load(currentData.album.cover).into(holder.image); // import the picasso to convert the image to image view

        // when i click on start button music should play
        holder.play.setOnClickListener {
            mediaPlayer.start()
        }
        // when i click on pause button music should stop
        holder.pause.setOnClickListener {
            mediaPlayer.stop()
        }



    }

    class MyViewHolder(itemView: View): RecyclerView.ViewHolder(itemView)  { // MyViewHolder class is to hold the data
        // create the view if layout manager fails to create the view for the data
        val image: ImageView
        val title: TextView
        val play: ImageButton
        val pause: ImageButton

        init {
            image = itemView.findViewById(R.id.musicImage)
            title = itemView.findViewById(R.id.musicTittle)
            play = itemView.findViewById(R.id.buttonPlay)
            pause = itemView.findViewById(R.id.buttonPause)

        }

    }
}


