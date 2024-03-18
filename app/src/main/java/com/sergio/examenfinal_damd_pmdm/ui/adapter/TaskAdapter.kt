package com.sergio.examenfinal_damd_pmdm.ui.adapter

import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.sergio.examenfinal_damd_pmdm.data.network.model.TaskResponse
import com.sergio.examenfinal_damd_pmdm.databinding.ItemBaseBinding

class TaskAdapter : ListAdapter<TaskResponse, TaskAdapter.BaseViewHolder>(BaseItemCallBack) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemBaseBinding.inflate(inflater, parent, false)
        return BaseViewHolder(binding)

    }

    override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {
        val item = getItem(position)
        val context = holder.itemView.context
        holder.binding.tvTaskTitle.text = item.taskTitle
        holder.binding.tvEmployee.text = item.employee
        holder.binding.tvDeadline.text = "Fecha de entrega: ${item.deadLine}"
        holder.binding.tvProjectName.text = "Información del proyecto: ${item.project.title}"
        holder.binding.tvDeadlineProject.text = "Fecha de entrega del proyecto: ${item.project.projectDeadLine}"
        holder.binding.tvDepartment.text = item.project.department
        holder.binding.tvDescription.text = item.description

        if(item.project.department == "Mobile"){
            holder.binding.cvItemBase.setCardBackgroundColor(Color.YELLOW)
        }
    }



    inner class BaseViewHolder(val binding: ItemBaseBinding) : RecyclerView.ViewHolder(binding.root)

}

object BaseItemCallBack : DiffUtil.ItemCallback<TaskResponse>() {
    override fun areItemsTheSame(oldItem: TaskResponse, newItem: TaskResponse): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: TaskResponse, newItem: TaskResponse): Boolean {
        return oldItem == newItem
    }
}