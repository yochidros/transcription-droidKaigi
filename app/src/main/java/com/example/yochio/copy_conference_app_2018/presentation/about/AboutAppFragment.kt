package com.example.yochio.copy_conference_app_2018.presentation.about

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.SimpleItemAnimator
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.yochio.copy_conference_app_2018.data.db.fixeddata.AboutApps
import com.example.yochio.copy_conference_app_2018.databinding.FragmentAboutAppBinding
import com.example.yochio.copy_conference_app_2018.di.Injectable
import com.example.yochio.copy_conference_app_2018.presentation.NavigationController
import com.example.yochio.copy_conference_app_2018.presentation.about.item.AboutAppItem
import com.example.yochio.copy_conference_app_2018.presentation.about.item.AboutAppsSection
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.ViewHolder
import javax.inject.Inject

/**
 * Created by yotio on 2018/02/06.
 */

class AboutAppFragment : Fragment(), Injectable {
    private lateinit var binding: FragmentAboutAppBinding
    @Inject lateinit var navigationController: NavigationController
    private val aboutAppSection = AboutAppsSection()
    private val onAboutHeaderIconClickListener = { navUrl: String ->
        navigationController.navigateToExternalBrowser(navUrl)
    }
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        binding = FragmentAboutAppBinding.inflate(inflater, container!!, false)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        setupRecyclerView()
    }

    private fun setupRecyclerView() {
        val groupAdapter = GroupAdapter<ViewHolder>().apply {
            add(aboutAppSection)
            setOnItemClickListener({ item, _ ->
                val aboutAppItem = item as? AboutAppItem ?: return@setOnItemClickListener
                navigationController.navigateImplicitly(aboutAppItem.aboutApp.navigationUrl)
            })
        }
        binding.aboutAppRecycler.apply {
            adapter = groupAdapter
            (itemAnimator as SimpleItemAnimator).supportsChangeAnimations = false
        }
        aboutAppSection.updateAboutApps(
                 AboutApps.getThisApps(),
              onAboutHeaderIconClickListener
        )
    }

    companion object {
        fun newInstance(): AboutAppFragment = AboutAppFragment()
    }
}