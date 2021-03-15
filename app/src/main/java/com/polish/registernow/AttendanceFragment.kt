package com.polish.registernow

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.firestore.EventListener
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.QuerySnapshot
import com.google.firebase.firestore.ktx.toObject
import com.polish.registernow.adapter.AttendanceAdapter
import com.polish.registernow.constants.Constants
import com.polish.registernow.databinding.FragmentAttendanceBinding
import com.polish.registernow.model.User


class AttendanceFragment : Fragment() {
    val TAG = "ATTENDANCE_FRAGMENT"
    // get instance of the cloud firestore
    lateinit var db:FirebaseFirestore


    /**
     * declare views
     */
    lateinit var attendanceAdapter: AttendanceAdapter
    lateinit var displayItems:RecyclerView
    lateinit var retrievedList:ArrayList<User>
    // declaring view binding
    private var _binding:FragmentAttendanceBinding? = null
    private val binding
    get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        _binding = FragmentAttendanceBinding.inflate(inflater, container, false)
        val view = binding.root
        // initalize the firebase firestore
        db = FirebaseFirestore.getInstance()
        // initialize the list
        retrievedList = ArrayList()

        // query the database
        getAttendanceList()

        /**
         * initalize views
         */
        displayItems = binding.fragmentAttendanceListRv

        /**
         * add recyclerview
         */
        displayItems.layoutManager = LinearLayoutManager(requireContext())

        return view
    }

    private fun getAttendanceList(){
        val queryCloudFirestore = db.collection(Constants.USERS)
            .addSnapshotListener(EventListener<QuerySnapshot>{ value, error ->  
                if (error != null || value == null){
                    return@EventListener
                }
                for (doc in value){
                    var attendance = doc.toObject(User::class.java)
                    retrievedList.add(attendance)
                }
                attendanceAdapter = AttendanceAdapter(retrievedList)
                attendanceAdapter.notifyDataSetChanged()
                displayItems.adapter = attendanceAdapter
                Log.d(TAG, "my list is $retrievedList")

            })
    }


}