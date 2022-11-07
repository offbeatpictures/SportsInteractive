package com.sports.cricket.view.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.google.android.material.tabs.TabLayoutMediator
import com.sports.cricket.R
import com.sports.cricket.databinding.ActivityPlayersInfoBinding
import com.sports.cricket.service.model.Players
import com.sports.cricket.service.model.TeamInfoModel
import com.sports.cricket.view.fragment.adapaters.FragmentAdapater
import com.sports.cricket.view.fragment.PlayersInfoFragment

class PlayersInfoActivity : AppCompatActivity() {


    lateinit var activityPlayersInfoBinding: ActivityPlayersInfoBinding;
    var team_info: ArrayList<TeamInfoModel>? = null;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityPlayersInfoBinding =
            DataBindingUtil.setContentView(this, R.layout.activity_players_info)
        activityPlayersInfoBinding.lifecycleOwner = this

        getDataFromIntent()

        initFragmetAdpater()


    }

    private fun getDataFromIntent() {
        team_info = intent.getSerializableExtra("teamInfo") as ArrayList<TeamInfoModel>
    }

    private fun initFragmetAdpater() {
        team_info?.let {

            val arrayofFrg = ArrayList<Fragment>()
            for (index in 0 until it.size) {
                val teamInfoModel = it.get(index)
                arrayofFrg.add(PlayersInfoFragment.getInstance(teamInfoModel.players))
            }

            activityPlayersInfoBinding.viewPager.adapter = FragmentAdapater(this@PlayersInfoActivity, arrayofFrg)
            TabLayoutMediator(
                activityPlayersInfoBinding.tabLayout,
                activityPlayersInfoBinding.viewPager
            ) { tab, position ->
                tab.text = it.get(position).nameFull
            }.attach()


        }

    }


}