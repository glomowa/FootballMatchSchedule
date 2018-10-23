package com.glomowa.footballmatchschedule.ui.prev


import android.app.AlertDialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import com.glomowa.footballmatchschedule.ui.detailevent.DetailEventActivity
import com.glomowa.footballmatchschedule.R
import com.glomowa.footballmatchschedule.di.component.DaggerFragmentComponent
import com.glomowa.footballmatchschedule.di.module.FragmentModule
import com.glomowa.footballmatchschedule.model.Event
import kotlinx.android.synthetic.main.fragment_prev.*
import org.jetbrains.anko.support.v4.startActivity
import org.jetbrains.anko.support.v4.toast
import javax.inject.Inject

class PrevFragment : Fragment(), PrevContract.View {

    @Inject
    lateinit var presenter: PrevContract.Presenter
    private lateinit var rootView: View
    private lateinit var builder: AlertDialog.Builder
    private lateinit var dialog: AlertDialog
    private var ids: String = "4328"

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        rootView = inflater.inflate(R.layout.fragment_prev, container, false)
        builder = AlertDialog.Builder(activity)
        val dialogView = layoutInflater.inflate(R.layout.progress_dialog, null)
        dialog = builder.setView(dialogView).setCancelable(false).create()
        return rootView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        presenter.attach(this)
        presenter.subscribe()
        sp_leagueprev.onItemSelectedListener = object: AdapterView.OnItemSelectedListener{
            override fun onNothingSelected(parent: AdapterView<*>?) {
                presenter.loadData(ids, "")
            }

            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                var league: String = sp_leagueprev.selectedItem.toString()
                when (league) {
                    "English Premier League" -> {
                        ids = "4328"
                    }
                    "Italian Serie A" -> {
                        ids = "4332"
                    }
                    "Spanish La Liga" -> {
                        ids = "4335"
                    }
                }
                presenter.loadData(ids,"")
            }
        }
        etQueryPrev.addTextChangedListener(object : TextWatcher{
            override fun afterTextChanged(s: Editable?) {
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                presenter.loadData(ids, s!!.toString())
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
        if (show) {
            dialog.show()
        } else {
            dialog.hide()
        }
    }

    override fun showErrorMessage(error: String) {
        toast(error)
    }

    override fun loadDataSuccess(list: List<Event>?) {
        val adapter = PrevAdapter(list!!.toMutableList()) {
            intentDetail(it)
        }
        rc_prev.layoutManager = LinearLayoutManager(activity)
        rc_prev.adapter = adapter
    }

    override fun intentDetail(event: Event) {
        startActivity<DetailEventActivity>("idHome" to event.idHomeTeam, "idAway" to event.idAwayTeam, "idEvent" to event.idEvent)
    }

    private fun injectDependency() {
        val prevComponent = DaggerFragmentComponent.builder()
                .fragmentModule(FragmentModule())
                .build()
        prevComponent.inject(this)
    }

}
