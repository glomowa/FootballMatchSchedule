package com.glomowa.footballmatchschedule.ui.detailteam

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import com.glomowa.footballmatchschedule.ui.description.DescriptionFragment
import com.glomowa.footballmatchschedule.ui.listplayer.ListPlayerFragment
import com.glomowa.footballmatchschedule.model.Team

class TabPagerAdapter(fm: FragmentManager, private var team: Team):
        FragmentStatePagerAdapter(fm) {

    override fun getItem(position: Int): Fragment? {
        var fragment: Fragment
        var bundle = Bundle()
        bundle.putParcelable("team", team)
        when(position){
            0 -> {
                fragment = DescriptionFragment()
                fragment.arguments = bundle
                return fragment
            }
            1 -> {
                fragment = ListPlayerFragment()
                fragment.arguments = bundle
                return fragment
            }
            else -> return null
        }
    }

    override fun getCount(): Int = 2

    override fun getPageTitle(position: Int): CharSequence? {
        return when (position){
            0->"Description"
            else -> "List Player"
        }
    }
}