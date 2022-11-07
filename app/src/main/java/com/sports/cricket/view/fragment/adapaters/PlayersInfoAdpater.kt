package com.sports.cricket.view.fragment.adapaters

import android.app.AlertDialog
import android.graphics.Color
import android.graphics.Typeface
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.google.gson.Gson
import com.sports.cricket.R
import com.sports.cricket.service.model.Players
import org.json.JSONObject


class PlayersInfoAdpater(
    val appCompatActivity: AppCompatActivity,
    val listOfPlayers: ArrayList<Players>
) : RecyclerView.Adapter<PlayersInfoAdpater.PlayersInfoViewHolder>() {


    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): PlayersInfoAdpater.PlayersInfoViewHolder {
        val view = LayoutInflater.from(appCompatActivity)
            .inflate(R.layout.players_info_view, parent, false)
        return PlayersInfoViewHolder(view)
    }

    override fun onBindViewHolder(holder: PlayersInfoAdpater.PlayersInfoViewHolder, position: Int) {
        val players = listOfPlayers.get(position)
        holder.player_name_tv.text = players.nameFull
        holder.player_pos_tv.text = "Batting Position: ${players.position}"
        if (players.isCaptain) {
            holder.player_tag_tv.visibility = View.VISIBLE
            holder.player_tag_tv.text = "Captian"

        } else if (players.iskeeper) {
            holder.player_tag_tv.visibility = View.VISIBLE
            holder.player_tag_tv.text = "Wicket Keeper"
        }

    }

    override fun getItemCount() = listOfPlayers.size


    inner class PlayersInfoViewHolder(viewHoler: View) : RecyclerView.ViewHolder(viewHoler) {

        val player_name_tv: TextView = viewHoler.findViewById(R.id.player_name_tv);
        val player_pos_tv: TextView = viewHoler.findViewById(R.id.player_pos_tv);
        val player_tag_tv: TextView = viewHoler.findViewById(R.id.player_tag_tv);

        init {
            viewHoler.setOnClickListener(View.OnClickListener {
                val player_info = listOfPlayers.get(layoutPosition)
                showPlayerDetailsAlertDialog(player_info)

            })
        }


    }

    fun showPlayerDetailsAlertDialog(players: Players) {


        val layoutInflater = LayoutInflater.from(appCompatActivity).inflate(R.layout.dialog_custom_layout, null)

        layoutInflater?.let {
            val dialog = AlertDialog.Builder(appCompatActivity).setView(it)
            val title = TextView(appCompatActivity)
            title.text = players.nameFull
            title.setBackgroundColor(Color.DKGRAY)
            title.setPadding(10, 10, 10, 10)
            title.gravity = Gravity.CENTER
            title.typeface= Typeface.DEFAULT_BOLD
            title.setTextColor(Color.WHITE)
            title.textSize = 20f
            dialog.setCustomTitle(title)
            val li_container = it.findViewById<LinearLayout>(R.id.li_container)
            addTextViewToContainer("BATTING", true,li_container)
            addPlayerInfoToView(
                li_container,
                JSONObject(Gson().toJson(players.batting))
            ) //batting details
            addTextViewToContainer("BOWLING", true,li_container)
            addPlayerInfoToView(
                li_container,
                JSONObject(Gson().toJson(players.bowling))
            ) //bowling details

            dialog.show()
        }

    }

    fun addTextViewToContainer(text: String,bold:Boolean, li_container: LinearLayout) {
        val tv = TextView(appCompatActivity)
        tv.text = text
        tv.setTextColor(Color.BLACK)
       if (bold) {
           tv.typeface = Typeface.DEFAULT_BOLD
           tv.setPadding(0, 20, 0, 10)
       }
        tv.layoutParams = ViewGroup.LayoutParams(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.WRAP_CONTENT
        )
        li_container.addView(tv)
    }

    fun addPlayerInfoToView(li_container: LinearLayout, jsonObject: JSONObject) {
        try {
            val keys: Iterator<*> = jsonObject.keys()
            while (keys.hasNext()) {
                val key = keys.next().toString()
                val obj = jsonObject.get(key).toString()
                addTextViewToContainer("$key : $obj", false,li_container)
            }

        } catch (expection: Exception) {

        }
    }

}