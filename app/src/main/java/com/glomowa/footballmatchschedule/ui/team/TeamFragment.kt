package com.glomowa.footballmatchschedule.ui.team


import android.app.Activity
import android.app.AlertDialog
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.AdapterView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.glomowa.footballmatchschedule.R
import com.glomowa.footballmatchschedule.di.component.DaggerFragmentComponent
import com.glomowa.footballmatchschedule.di.module.FragmentModule
import com.glomowa.footballmatchschedule.model.Team
import com.glomowa.footballmatchschedule.ui.detailteam.DetailTeamActivity
import kotlinx.android.synthetic.main.fragment_team.*
import org.jetbrains.anko.support.v4.startActivity
import org.jetbrains.anko.support.v4.toast
import javax.inject.Inject

class TeamFragment : Fragment(), TeamContract.View {

    @Inject
    lateinit var presenter: TeamContract.Presenter
    private lateinit var builder: AlertDialog.Builder
    private lateinit var dialog: AlertDialog
    private var league: String = "English Premier League"
    private lateinit var rootView: View

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        rootView = inflater.inflate(R.layout.fragment_team, container, false)
        builder = AlertDialog.Builder(activity)
        val dialogView = layoutInflater.inflate(R.layout.progress_dialog, null)
        dialog = builder.setView(dialogView).setCancelable(false).create()
        return rootView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        presenter.attach(this)
        presenter.subscribe()
        sp_leagueteam.onItemSelectedListener = object: AdapterView.OnItemSelectedListener{
            override fun onNothingSelected(parent: AdapterView<*>?) {
                presenter.loadData(league, "")
            }

            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                league = sp_leagueteam.selectedItem.toString()
                presenter.loadData(league,"")
            }
        }
        etQueryTeam.addTextChangedListener(object : TextWatcher{
            override fun afterTextChanged(s: Editable?) {
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                presenter.loadData(league, s!!.toString())
            }

        })
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        injectDependency()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        presenter.unsubscribe()
    }

    override fun showProgress(show: Boolean) {
        if (show){
            dialog.show()
        }else{
            dialog.hide()
        }
    }

    override fun showErrorMessage(error: String) {
        Log.d("asd",error)
    }

    override fun loadDataSuccess(list: List<Team>?) {
        val adapter = TeamAdapter(list!!.toMutableList()){
            intentDetail(it)
        }
        rv_listteam.layoutManager = LinearLayoutManager(context)
        rv_listteam.addItemDecoration(DividerItemDecoration(context, DividerItemDecoration.VERTICAL))
        rv_listteam.adapter = adapter
    }

    override fun intentDetail(team: Team) {
        startActivity<DetailTeamActivity>("team" to team)
    }

    private fun injectDependency(){
        val teamComponent = DaggerFragmentComponent.builder()
                .fragmentModule(FragmentModule())
                .build()
        teamComponent.inject(this)
    }

}
