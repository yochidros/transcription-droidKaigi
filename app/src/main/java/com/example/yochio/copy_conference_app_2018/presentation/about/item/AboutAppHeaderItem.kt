package com.example.yochio.copy_conference_app_2018.presentation.about.item

import android.databinding.DataBindingUtil
import android.view.View
import com.example.yochio.copy_conference_app_2018.R
import com.example.yochio.copy_conference_app_2018.databinding.ItemAboutAppHeaderBinding
import com.example.yochio.copy_conference_app_2018.model.AboutApp
import com.xwray.groupie.Item
import com.xwray.groupie.databinding.BindableItem
import com.xwray.groupie.databinding.ViewHolder

/**
 * Created by yotio on 2018/02/06.
 */

class AboutAppHeaderItem(
        private val headItem: AboutApp.HeadItem,
        private val onAboutHeaderIconClickListener: (String) -> Unit?
)  : BindableItem<ItemAboutAppHeaderBinding>() {
    override fun createViewHolder(itemView: View): ViewHolder<ItemAboutAppHeaderBinding> {
        return ViewHolder(DataBindingUtil.bind(itemView))
    }

    override fun getLayout(): Int = R.layout.item_about_app_header

    override fun bind(viewBinding: ItemAboutAppHeaderBinding, position: Int) {
        viewBinding.headItem = headItem
        viewBinding.aboutAppFacebook.setOnClickListener {
            onAboutHeaderIconClickListener(headItem.faceBookUrl)
        }
        viewBinding.aboutAppTwitter.setOnClickListener {
            onAboutHeaderIconClickListener(headItem.twitterUrl)
        }
        viewBinding.aboutAppGithub.setOnClickListener {
            onAboutHeaderIconClickListener(headItem.githubUrl)
        }
        viewBinding.aboutAppYoutube.setOnClickListener {
            onAboutHeaderIconClickListener(headItem.youtubeUrl)
        }
        viewBinding.aboutAppMedium.setOnClickListener {
            onAboutHeaderIconClickListener(headItem.mediumUrl)
        }
    }

    override fun isSameAs(other: Item<*>?): Boolean =
            other is AboutAppHeaderItem

    override fun equals(other: Any?): Boolean =
            headItem == (other as? AboutAppHeaderItem?)?.headItem

    override fun hashCode(): Int = headItem.hashCode()
}