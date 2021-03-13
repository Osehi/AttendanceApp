package com.polish.registernow

import android.app.Dialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import com.polish.registernow.databinding.FragmentLoginBinding
import com.polish.registernow.utils.navigate
import com.polish.registernow.utils.showProgressBar


class LoginFragment : Fragment() {

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
            showProgressBar(progrzBar)
        }

        return view
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }


}