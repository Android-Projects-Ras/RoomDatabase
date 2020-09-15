package com.rogok.roomdatabase.fragments.update

import android.os.Bundle
import android.text.TextUtils
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.rogok.roomdatabase.R
import com.rogok.roomdatabase.model.User
import com.rogok.roomdatabase.viewmodel.UserViewModel
import kotlinx.android.synthetic.main.fragment_update.*
import kotlinx.android.synthetic.main.fragment_update.view.*


class UpdateFragment : Fragment() {
   private val args by navArgs<UpdateFragmentArgs>()
    private lateinit var mUserViewModel: UserViewModel


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_update, container, false)

        mUserViewModel = ViewModelProvider(this).get(UserViewModel::class.java)
        view.update_first_name_et.setText(args.currentUser.firstName)
        view.update_last_name_et.setText(args.currentUser.lastName)
        view.update_age_et.setText(args.currentUser.age.toString())

        view.update_button.setOnClickListener {
            updateItem()
        }



        return view
    }

    private fun updateItem() {
        val firstName = update_first_name_et.text.toString()
        val lastName = update_last_name_et.text.toString()
        val age = Integer.parseInt(update_age_et.text.toString())

        if(inputCheck(firstName, lastName, update_age_et.text.toString())) {
            //Create user object
            val updatedUser = User(args.currentUser.id, firstName, lastName, age)
            //Update current user
            mUserViewModel.updateUser(updatedUser)
            Toast.makeText(requireContext(), "Updated successfully", Toast.LENGTH_SHORT).show()
            //Navigate back
            findNavController().navigate(R.id.action_updateFragment_to_listFragment)
        } else {
            Toast.makeText(requireContext(), "Please fill out all fields", Toast.LENGTH_SHORT).show()
        }

    }

    private fun inputCheck(firstName: String, lastName: String, age: String): Boolean {
        return !(TextUtils.isEmpty(firstName) && TextUtils.isEmpty(lastName) && TextUtils.isEmpty(age))
    }

}