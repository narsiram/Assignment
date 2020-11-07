package com.sumcorp.shaadiassignment.ui.list

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.sumcorp.shaadiassignment.R
import com.sumcorp.shaadiassignment.data.local.entity.model.ResultData
import kotlinx.android.synthetic.main.item_layout_shaadi_match.view.*
import java.util.ArrayList

class RequestListAdapter : RecyclerView.Adapter<RequestListAdapter.Holder> {

    var onClickEvent: OnClickEvent? = null
    var requestList: ArrayList<ResultData>? = null

    constructor(onClickEvent: OnClickEvent) {
        this.onClickEvent = onClickEvent
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        return Holder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_layout_shaadi_match, parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        if (requestList != null) {
            holder.bind(requestList!!.get(position))

            holder.itemView.btnAccept.setOnClickListener {
                onClickEvent!!.onItemClick(holder.adapterPosition, holder.itemView.btnAccept)
            }

            holder.itemView.btnReject.setOnClickListener {
                onClickEvent!!.onItemClick(holder.adapterPosition, holder.itemView.btnReject)
            }
        }
    }

    override fun getItemCount(): Int =
        if (requestList != null) {
            requestList!!.size
        } else
            0


    fun setData(requestList: ArrayList<ResultData>) {
        this.requestList = requestList
        notifyDataSetChanged()
    }

    class Holder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        @SuppressLint("SetTextI18n")
        fun bind(data: ResultData) {
            itemView.tvName.text = "${data.name.firstName} ${data.name.lastName}"
            itemView.tvAddress.text = "${data.dob.age} yrs, ${data.gender}"

            if (data.status.equals("accept", true)
                || data.status.equals("reject", true)
            ) {
                itemView.lytButton.visibility = View.GONE
                itemView.tvStatus.visibility = View.VISIBLE
                itemView.tvStatus.text = "Request ${data.status}ed"
            } else {
                itemView.lytButton.visibility = View.VISIBLE
                itemView.tvStatus.visibility = View.GONE
            }

            Glide.with(itemView.context)
                .load(data.imageData.profileUrl)
                .circleCrop()
                .placeholder(R.drawable.default_image)
                .into(itemView.imageView)


        }
    }


    interface OnClickEvent {
        fun onItemClick(pos: Int, view: View)
    }
}