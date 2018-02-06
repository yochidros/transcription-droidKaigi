package com.example.yochio.copy_conference_app_2018.presentation.about.item

import android.databinding.DataBindingUtil
import android.view.View
import com.example.yochio.copy_conference_app_2018.R
import com.example.yochio.copy_conference_app_2018.databinding.ItemAboutAppBinding
import com.example.yochio.copy_conference_app_2018.model.AboutApp
import com.xwray.groupie.databinding.BindableItem
import com.xwray.groupie.databinding.ViewHolder

/**
 * Created by yotio on 2018/02/06.
 */
data class AboutAppItem(
        val aboutApp: AboutApp
) : BindableItem<ItemAboutAppBinding>() {
    override fun createViewHolder(itemView: View): ViewHolder<ItemAboutAppBinding> {
        return ViewHolder(DataBindingUtil.bind(itemView))
    }

    override fun getLayout(): Int  = R.layout.item_about_app

    override fun bind(viewBinding: ItemAboutAppBinding, position: Int) {
        viewBinding.aboutApp = aboutApp
    }

}