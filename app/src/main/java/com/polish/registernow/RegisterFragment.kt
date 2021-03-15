package com.polish.registernow

import android.app.Dialog
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.SetOptions
import com.polish.registernow.constants.Constants
import com.polish.registernow.databinding.FragmentRegisterBinding
import com.polish.registernow.firestore.FirestoreClass
import com.polish.registernow.model.User
import com.polish.registernow.utils.*


class RegisterFragment : Fragment() {
    val TAG = "REGISTER_FRAGMENT"
    // declare views
    lateinit var registerButton: Button
    // firestore componenet
    lateinit var nFirestore: FirebaseFirestore

    // declare dialog
    lateinit var progressBar:Dialog
    lateinit var testProgressBar:Dialog

    // initialize the view binding
    private var _binding:FragmentRegisterBinding? = null
    private val binding
    get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        _binding = FragmentRegisterBinding.inflate(inflater, container, false)
        val view = binding.root
        // initialize views
        registerButton = binding.fragmentRegisterRegisteBtn
        // initialize the firestore here
        nFirestore = FirebaseFirestore.getInstance()

        // add setOnClicklistener to register button
        registerButton.setOnClickListener {
            Log.d(TAG, "register_button")
            registerUser()
        }

        // initialize the dialog
        progressBar = Dialog(requireContext())
        testProgressBar = Dialog(requireContext())

        return view
    }

    /**
     * A function to validate the entries of a new user
     */
    private fun validateRegisterDetails():Boolean{
        return when {
            TextUtils.isEmpty(binding.fragmentRegisterFirstnameEt.text.toString().trim {it <= ' '}) -> {
                showToast("Please enter first name")
                false
            }
            TextUtils.isEmpty(binding.fragmentRegisterLastnameEt.text.toString().trim {it <= ' '}) -> {
                showToast("Please enter last name")
                false
            }
            TextUtils.isEmpty(binding.fragmentRegisterEmailEt.text.toString().trim {it <= ' '}) -> {
                showToast("Please enter email")
                false
            }
            TextUtils.isEmpty(binding.fragmentRegisterPhonenumberEt.text.trim{it <= ' '})-> {
                showToast("Please enter a phone number")
                false
            }
            TextUtils.isEmpty(binding.fragmentRegisterPasswordEt.text.toString().trim{it <= ' '}) -> {
                showToast("Please enter password")
                false
            } else -> {
                showToast("Your details are valid")
                true
            }


        }

    }

    /**
     * registration
     */
    private fun registerUser(){
        // validate the fields
        if(validateRegisterDetails()){
            // show the progress dialog
            showProgressBar(progressBar)

            // receive the entry
            val email = binding.fragmentRegisterEmailEt.text.toString().trim()
            val password = binding.fragmentRegisterPasswordEt.text.toString().trim()
            Log.d(TAG, "this is email:$email")
            // create an instance of firebaseAuth and register a user with email and password
            FirebaseAuth.getInstance().createUserWithEmailAndPassword(email, password)
                    .addOnCompleteListener { task ->
                        // if registration is successfully done
                        if (task.isSuccessful){
                            // firebase registered user
                            val firebaseUser:FirebaseUser = task.result!!.user!!
                            // to capture the user data
                            val gender = if(binding.fragmentRegisterRadioButnMaleBtn.isChecked){
                                Constants.MALE
                            } else {
                                Constants.FEMALE
                            }
                            val user = User(
                                firebaseUser.uid,
                                binding.fragmentRegisterFirstnameEt.text.toString().trim{it <= ' '},
                                binding.fragmentRegisterLastnameEt.text.toString().trim{it <= ' '},
                                binding.fragmentRegisterEmailEt.text.toString().trim{it <= ' '},
                                binding.fragmentRegisterPhonenumberEt.text.toString().trim{it <= ' '},
                                gender
                            )
                            // save user info to database
//                            FirestoreClass().registerUser(requireContext(),user, testProgressBar)
                            nFirestore.collection(Constants.USERS)
                                    .document(user.id)
                                    .set(user, SetOptions.merge())
                                    .addOnSuccessListener {
                                        hideProgressBar(progressBar)
                                        showToast("You have registered successfully")
                                        navigate(R.id.loginFragment)
                                    }
                                    .addOnFailureListener { e ->
                                        hideProgressBar(progressBar)
                                        showToast("${e.message}")
                                    }

                        } else {
                            showToast("${task.exception!!.message.toString()}")
                            Log.d(TAG, "${task.exception!!.message.toString()}")
                            hideProgressBar(progressBar)
//                            removeProgressBar(testProgressBar)
                        }
                    }
        }
        /**
         * A function to notify the success result of Firestore entry when the user is registered successfully.
         */
        fun userRegistrationSuccess(){
            // hide the progress bar
            hideProgressBar(progressBar)
//                removeProgressBar(testProgressBar)
            showToast("You are registered successfully")
            // so what do you want to do with this screen after successful registration
            // should you just remain on the register
        }


    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }


}