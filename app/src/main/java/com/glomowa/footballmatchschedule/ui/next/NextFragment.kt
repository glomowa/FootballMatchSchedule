package com.glomowa.footballmatchschedule.ui.next


import android.app.AlertDialog
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log.d
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.glomowa.footballmatchschedule.R
import com.glomowa.footballmatchschedule.di.component.DaggerFragmentComponent
import com.glomowa.footballmatchschedule.di.module.FragmentModule
import com.glomowa.footballmatchschedule.model.Event
import com.glomowa.footballmatchschedule.ui.detailevent.DetailEventActivity
import kotlinx.android.synthetic.main.fragment_next.*
import org.jetbrains.anko.support.v4.startActivity
import org.jetbrains.anko.support.v4.toast
import javax.inject.Inject


class NextFragment : Fragment(), NextContract.View {

    @Inject
    lateinit var presenter: NextContract.Presenter
    private lateinit var rootView: View
    private lateinit var builder: AlertDialog.Builder
    private lateinit var dialog: AlertDialog
    private var ids: String = "4328"

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        rootView = inflater.inflate(R.layout.fragment_next, container, false)
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
        sp_leaguenext.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?) {
                presenter.loadData(ids, "")
            }

            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                var league: String = sp_leaguenext.selectedItem.toString()
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
                presenter.loadData(ids, "")
            }
        }
        etQueryNext.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                presenter.loadData(ids, s!!.toString())
            }

        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        presenter.unsubscribe()
    }

    override fun showProgress(show: Boolean) {
        if (show) {
            dialog.show()
        } else {
            dialog.dismiss()
        }
    }

    override fun showErrorMessage(error: String) {
        toast(error)
        d("asd", error)
    }

    override fun intentDetail(event: Event) {
        startActivity<DetailEventActivity>("idHome" to event.idHomeTeam, "idAway" to event.idAwayTeam, "idEvent" to event.idEvent)
    }

    override fun loadDataSuccess(list: List<Event>?) {
        var adapter = NextAdapter(list!!.toMutableList()) {
            intentDetail(it)
        }
        rc_next.layoutManager = LinearLayoutManager(activity)
        rc_next.adapter = adapter
    }

    private fun injectDependency() {
        val nextComponent = DaggerFragmentComponent.builder()
                .fragmentModule(FragmentModule())
                .build()
        nextComponent.inject(this)
    }

}
