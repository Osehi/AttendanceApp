package com.polish.registernow.firestore

import android.app.Dialog
import android.content.Context
import android.util.Log
import android.widget.Toast
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.SetOptions
import com.polish.registernow.RegisterFragment
import com.polish.registernow.constants.Constants
import com.polish.registernow.model.User
import com.polish.registernow.utils.removeProgressBar

class FirestoreClass {

    val TAG = "Firestore_Class"

    // Access a cloud Firestore instance
    private val mFirestore = FirebaseFirestore.getInstance()
    /**
     * save a registered user to firestore
     */
    fun registerUser(context:Context, userInfo:User, progressBarTake:Dialog){
        // the collection name is "users
        mFirestore.collection(Constants.USERS)
            .document(userInfo.id)
        // Here the userInfo are Field and the SetOption is set to merge. It is for if we wants to merge later on instead of replacing the fields.
            .set(userInfo, SetOptions.merge())
            .addOnSuccessListener {
            // call the function that will remove the progress bar and show a success message
                // hide the progress bar
                removeProgressBar(progressBarTake)
                // and show toast successful

            }
            .addOnFailureListener {e ->
                // hide progessbar
                removeProgressBar(progressBarTake)
                Toast.makeText(context, "error", Toast.LENGTH_SHORT).show()
            }
    }

}