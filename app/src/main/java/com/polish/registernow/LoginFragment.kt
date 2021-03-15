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
import android.widget.TextView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.polish.registernow.databinding.FragmentLoginBinding
import com.polish.registernow.utils.hideProgressBar
import com.polish.registernow.utils.navigate
import com.polish.registernow.utils.showProgressBar
import com.polish.registernow.utils.showToast


class LoginFragment : Fragment() {
    val TAG = "LOGIN_FRAGMENT"

    // declare the views
    lateinit var register:TextView
    lateinit var login:Button
    lateinit var progrzBar:Dialog

    // initialize the viewbinding for this fragment
    private var _binding:FragmentLoginBinding? = null
    private val binding
    get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentLoginBinding.inflate(inflater, container, false)
        val view = binding.root

        // initialize the dialog
        progrzBar = Dialog(requireContext())

        // initialize views
        register = binding.fragmentLoginRegisterTv
        login = binding.fragmentLoginLoginBtn

        // navigate to the register fragment
        register.setOnClickListener {
            navigate(R.id.registerFragment)
        }

        // add on click listener
        login.setOnClickListener {
            loginRegisteredUser()
        }

        return view
    }

    private fun validateLoginDetails():Boolean{
        return when {
            TextUtils.isEmpty(binding.activityMainEmailEt.text.toString().trim{it <= ' '}) -> {
                showToast("Please enter email")
                false
            }
            TextUtils.isEmpty(binding.fragmentLoginPasswordEt.text.toString().trim{it <= ' '}) -> {
                showToast("Please enter password")
                false
            }
            else -> {
                showToast("Your details are valid")
                true
            }
        }
    }

    private fun loginRegisteredUser(){
        if (validateLoginDetails()){
            showProgressBar(progrzBar)
            // get the input
            val email = binding.activityMainEmailEt.text.toString().trim()
            val password = binding.fragmentLoginPasswordEt.text.toString().trim()
            Log.d(TAG, "in login email:$email")
            Log.d(TAG, "in login password:$password")
            // login using firebase auth
            Firebase.auth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener { task ->
                    if (task.isSuccessful){
                        hideProgressBar(progrzBar)
                        navigate(R.id.attendanceFragment)
                    } else {
                        hideProgressBar(progrzBar)
                        showToast("${task.exception!!.message.toString()}")
                        Log.d(TAG, "${task.exception!!.message}")
                    }
                }
        }
        binding.activityMainEmailEt.text.clear()
        binding.fragmentLoginPasswordEt.text.clear()
    }



    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }


}