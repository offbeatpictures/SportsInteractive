package com.sports.cricket.view.fragment

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.sports.cricket.R
import com.sports.cricket.service.model.Players
import com.sports.cricket.view.fragment.adapaters.PlayersInfoAdpater

class PlayersInfoFragment : Fragment() {

    var frg_view: View? = null;
    lateinit var rc_view: RecyclerView
    lateinit var appCompatActivity: AppCompatActivity

    var playerList = ArrayList<Players>()


    companion object{
        fun getInstance(playersList: ArrayList<Players>): PlayersInfoFragment {
            val playersInfoFragment = PlayersInfoFragment()
            val bundle = Bundle()
            bundle.putSerializable("playerList", playersList)
            playersInfoFragment.arguments = bundle
            return playersInfoFragment
        }
    }



    override fun onAttach(context: Context) {
        super.onAttach(context)
        appCompatActivity = context as AppCompatActivity
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        playerList = arguments?.getSerializable("playerList") as ArrayList<Players>
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        if (frg_view == null) {
            frg_view = inflater.inflate(R.layout.players_info_frag_layout, container, false)
        }
        initViews()
        attachAdpater()
        return frg_view

    }

    private fun attachAdpater() {
        val playersInfoAdpater = PlayersInfoAdpater(appCompatActivity, playerList)
        rc_view.apply {
            this.adapter = playersInfoAdpater
            this.layoutManager = LinearLayoutManager(appCompatActivity)
        }
    }

    private fun initViews() {
        frg_view?.let {
            rc_view = it.findViewById(R.id.rc_view_players)
        }
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }


}