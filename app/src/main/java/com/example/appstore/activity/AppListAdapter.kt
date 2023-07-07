package com.example.appstore.activity

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.appstore.R
import com.example.appstore.data.AppDetailsListing
import com.example.appstore.utils.Utils
import com.example.appstore.utils.checkSelfPermissionCompat
import com.example.appstore.utils.requestPermissionsCompat
import com.example.appstore.utils.shouldShowRequestPermissionRationaleCompat
import com.example.appstore.utils.showSnackbar
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_second.secondLayout

class AppListAdapter(
    private val appList: List<AppDetailsListing>,
    private val context: Context,
    var listener: AppListAdapter.ClickListener
) :
    RecyclerView.Adapter<AppListAdapter.ItemViewHolder>() {
    companion object {
        var mClickListener: ClickListener? = null
    }


    class ItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val imageView: ImageView = itemView.findViewById(R.id.appLogo)
        val appNameTextVIew: TextView = itemView.findViewById(R.id.appName)
        val button: Button = itemView.findViewById(R.id.install)
        val appVersionTextView: TextView = itemView.findViewById(R.id.appVersion)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        mClickListener = listener
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.card_list_item, parent, false)
        return ItemViewHolder(view)
    }

    override fun getItemCount(): Int {
        return appList.size
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val currentItem = appList[position]

        holder.imageView.setImageResource(R.drawable.img)
        holder.appNameTextVIew.text = currentItem.appName
        holder.appVersionTextView.text = currentItem.versionName

        if (Utils.isAppInstalled(currentItem.packageName!!, context)) {
            holder.button.text = "Open"
        } else {
            holder.button.text = "Install"
        }


        holder.button.setOnClickListener {
            if (Utils.isAppInstalled(currentItem.packageName!!, context)) {
                mClickListener!!.onOpen(position)
            } else {
                mClickListener!!.onInstall(position)
            }
        }
    }

    interface ClickListener {
        fun onInstall(position: Int)
        fun onOpen(position: Int)
    }

}