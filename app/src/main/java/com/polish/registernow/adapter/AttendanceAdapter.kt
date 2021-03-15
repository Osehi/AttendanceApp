package com.polish.registernow.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.polish.registernow.databinding.ItemAttendanceBinding
import com.polish.registernow.model.User

class AttendanceAdapter(val users:ArrayList<User>):RecyclerView.Adapter<AttendanceAdapter.AttendanceViewHolder>() {

    /**
     * viewholder class
     */
    class AttendanceViewHolder(private val binding: ItemAttendanceBinding):RecyclerView.ViewHolder(binding.root){
        fun bind(user: User){
            // initialize views
            val firstName = binding.itemAttendanceFirstnameTv
            val lastName = binding.itemAttendanceLastnameTv
            val email = binding.itemAttendanceEmail
            val phoneNumber = binding.itemAttendancePhonenumber
            // set the data to the views
            firstName.text = user.firstName.toString()
            lastName.text = user.lastName.toString()
            email.text = user.email.toString()
            phoneNumber.text = user.phoneNumber.toString()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AttendanceViewHolder {
        // inflate the viewholder
        val layoutInflater = LayoutInflater.from(parent.context)
        val attendanceListBinding = ItemAttendanceBinding.inflate(layoutInflater, parent, false)
        return AttendanceViewHolder(attendanceListBinding)
    }

    override fun onBindViewHolder(holder: AttendanceViewHolder, position: Int) {
        val item = users[position]
        holder.bind(item)
    }

    override fun getItemCount(): Int {
        return users.size
    }

}