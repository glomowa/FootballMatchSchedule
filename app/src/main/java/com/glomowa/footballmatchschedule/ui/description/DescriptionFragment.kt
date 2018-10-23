package com.glomowa.footballmatchschedule.ui.description


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment

import com.glomowa.footballmatchschedule.R
import com.glomowa.footballmatchschedule.di.component.DaggerFragmentComponent
import com.glomowa.footballmatchschedule.di.module.FragmentModule
import com.glomowa.footballmatchschedule.model.Team
import kotlinx.android.synthetic.main.fragment_description.*
import javax.inject.Inject


class DescriptionFragment : Fragment(), DescriptionContract.View {

    @Inject
    lateinit var presenter: DescriptionContract.Presenter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        injectDependency()
        return inflater.inflate(R.layout.fragment_description, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        presenter.attach(this)
    }

    override fun loadData() {
        val team = arguments?.getParcelable<Team>("team")
        tv_description.text = team?.strDescriptionEN
    }

    private fun injectDependency(){
        var descComponent = DaggerFragmentComponent.builder()
                .fragmentModule(FragmentModule())
                .build()
        descComponent.inject(this)
    }

}
