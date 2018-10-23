package com.glomowa.footballmatchschedule.ui.listplayer


import android.app.AlertDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.glomowa.footballmatchschedule.R
import com.glomowa.footballmatchschedule.di.component.DaggerFragmentComponent
import com.glomowa.footballmatchschedule.di.module.FragmentModule
import com.glomowa.footballmatchschedule.model.Player
import com.glomowa.footballmatchschedule.model.Team
import com.glomowa.footballmatchschedule.ui.detailplayer.DetailPlayerActivity
import kotlinx.android.synthetic.main.fragment_list_player.*
import org.jetbrains.anko.support.v4.startActivity
import org.jetbrains.anko.support.v4.toast
import javax.inject.Inject


class ListPlayerFragment : Fragment(), ListPlayerContract.View {

    @Inject
    lateinit var presenter: ListPlayerContract.Presenter
    private lateinit var rootView: View
    private lateinit var builder: AlertDialog.Builder
    private lateinit var dialog: AlertDialog
    private var team: Team? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        rootView = inflater.inflate(R.layout.fragment_list_player, container, false)
        builder = AlertDialog.Builder(activity)
        val dialogView = layoutInflater.inflate(R.layout.progress_dialog, null)
        dialog = builder.setView(dialogView).setCancelable(false).create()
        return rootView
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        injectDependency()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        presenter.attach(this)
        presenter.subscribe()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        presenter.unsubscribe()
    }

    override fun getDataIntent() {
        team = arguments?.getParcelable<Team>("team")
        presenter.loadPlayer(team?.strTeam!!)
    }

    private fun injectDependency(){
        val playerComponent = DaggerFragmentComponent.builder()
                .fragmentModule(FragmentModule())
                .build()
        playerComponent.inject(this)
    }

    override fun showProgress(show: Boolean) {
        if (show) {
            dialog.show()
        } else {
            dialog.dismiss()
        }
    }

    override fun loadDataSuccess(list: List<Player>) {
        rv_listplayer.isNestedScrollingEnabled = false
        rv_listplayer.layoutManager = LinearLayoutManager(context)
        rv_listplayer.addItemDecoration(DividerItemDecoration(context, DividerItemDecoration.VERTICAL))
        val adapter = ListPlayerAdapter(list){
            intentDetail(it)
        }
        rv_listplayer.adapter = adapter
    }

    override fun showErrorMessage(message: String) {
        toast(message)
    }

    override fun intentDetail(player: Player) {
        startActivity<DetailPlayerActivity>("player" to player)
    }

}
