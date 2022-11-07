package com.sports.cricket.view.activity

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.google.gson.Gson
import com.sports.cricket.R
import com.sports.cricket.databinding.ActivityMainBinding
import com.sports.cricket.service.model.CricketModel
import com.sports.cricket.service.model.Players
import com.sports.cricket.service.model.TeamInfoModel
import com.sports.cricket.viewmodel.MainActivityViewModel
import org.json.JSONObject

const val TEAM_INFO="teamInfo";
class MainActivity : AppCompatActivity() {

    lateinit var mainActivityViewModel: MainActivityViewModel;
    lateinit var mainActivityMainBinding: ActivityMainBinding;
    var cricketModel: CricketModel? = null;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainActivityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        mainActivityViewModel = ViewModelProvider(this)[MainActivityViewModel::class.java]
        mainActivityMainBinding.mainActivityViewModel = mainActivityViewModel
        mainActivityMainBinding.lifecycleOwner = this;
        attachObserver()
        getMatchDetailsFromApi();
        mainActivityMainBinding.includeCardLayout.cardDetails.setOnClickListener(View.OnClickListener {
            openPlayersInfoActivity()
        })
    }

    fun attachObserver() {
        mainActivityViewModel.cricketModel.observe(this, Observer {
            cricketModel = it
        })
    }

    fun getMatchDetailsFromApi() {
        mainActivityViewModel.getMatchDetails();
    }

    fun addPlayersList(teamInfoModel: TeamInfoModel, jsonObject: JSONObject) {
        try {
            val keys: Iterator<*> = jsonObject.keys()
            while (keys.hasNext()) {
                val key = keys.next()
                val obj = jsonObject.get(key.toString())
                teamInfoModel.players.add(Gson().fromJson(obj.toString(), Players::class.java))
            }

        } catch (expection: Exception) {

        }
    }

    fun getTeamInfoDetails(jsonObject: JSONObject): TeamInfoModel {
        return TeamInfoModel(
            jsonObject.get("Name_Full").toString(),
            jsonObject.get("Name_Short").toString(),
            ArrayList()
        )
    }

    fun openPlayersInfoActivity() {
        cricketModel?.let {
            val listOfTeamInfoModel = ArrayList<TeamInfoModel>()

            try {
                val team1 = getTeamInfoDetails(JSONObject(it.teams.getAsJsonObject("4").toString()))
                val team2 = getTeamInfoDetails (JSONObject(it.teams.getAsJsonObject("5").toString()))
                addPlayersList(team1,JSONObject(it.teams.getAsJsonObject("4").getAsJsonObject("Players").toString()))
                addPlayersList(team2,JSONObject(it.teams.getAsJsonObject("5").getAsJsonObject("Players").toString()))
                listOfTeamInfoModel.add(team1)
                listOfTeamInfoModel.add(team2)

            } catch (ex: Exception) {

            }

            if (listOfTeamInfoModel.size>0) startActivity(Intent(this@MainActivity,PlayersInfoActivity::class.java).putExtra(
                TEAM_INFO,listOfTeamInfoModel))


        }
    }




}