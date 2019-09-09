package com.suonk.socialmusician.controller

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.suonk.socialmusician.R
import com.suonk.socialmusician.model.Post

class PostsContentRecyclerViewAdapter(private val context: Context, private var notification_alarm_ListOfNotification: ArrayList<Post>, private var nbOfSMS: Int, private var nbOfWhatsappMsg: Int) : RecyclerView.Adapter<PostsContentRecyclerViewAdapter.PostsContentRecyclerViewHolder>() {
    private var view: View? = null

    fun getItem(position: Int): Post {
        return notification_alarm_ListOfNotification[position]
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostsContentRecyclerViewHolder {
        view = LayoutInflater.from(parent.context).inflate(R.layout.item_recycler_post_content_layout, parent, false)

        return PostsContentRecyclerViewHolder(view!!)
    }

    override fun onBindViewHolder(holder: PostsContentRecyclerViewHolder, position: Int) {
        val post = getItem(position)

    }

    override fun getItemId(position: Int): Long {
        return notification_alarm_ListOfNotification.size.toLong()
    }

    /**
     * Returns the total number of items in the data set held by the adapter.
     *
     * @return The total number of items in this adapter.
     */
    override fun getItemCount(): Int {
        return notification_alarm_ListOfNotification.size
    }

    inner class PostsContentRecyclerViewHolder(view: View) : RecyclerView.ViewHolder(view) {
//        var item_recycler_notification_alarm_Layout: RelativeLayout = view.findViewById(R.id.item_recycler_notification_alarm_layout)
//        var item_recycler_notification_alarm_ImageView: AppCompatImageView = view.findViewById(R.id.item_recycler_notification_alarm_image)
//        var item_recycler_notification_alarm_Text: TextView = view.findViewById(R.id.item_recycler_notification_alarm_text)
    }
}