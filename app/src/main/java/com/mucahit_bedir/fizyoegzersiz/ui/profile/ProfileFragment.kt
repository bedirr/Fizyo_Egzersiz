package com.mucahit_bedir.fizyoegzersiz.ui.profile

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import com.mucahit_bedir.fizyoegzersiz.R
import com.mucahit_bedir.fizyoegzersiz.data.User
import com.mucahit_bedir.fizyoegzersiz.databinding.FragmentForgetPasswordBinding
import com.mucahit_bedir.fizyoegzersiz.databinding.FragmentHomeBinding
import com.mucahit_bedir.fizyoegzersiz.databinding.FragmentProfileBinding
import com.mucahit_bedir.fizyoegzersiz.ui.SharedViewModel
import com.mucahit_bedir.fizyoegzersiz.ui.authentication.LoginFragmentDirections

class ProfileFragment : Fragment(), View.OnClickListener {
    private lateinit var binding: FragmentProfileBinding
    private val sharedViewModel: SharedViewModel by activityViewModels()

    private lateinit var auth: FirebaseAuth
    private lateinit var databaseReference: DatabaseReference
    private lateinit var user: User
    private lateinit var uid: String

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentProfileBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        sharedViewModel.setBottomNavVisibility(true)

        auth = FirebaseAuth.getInstance()
        uid = auth.currentUser?.uid.toString()

        databaseReference = FirebaseDatabase.getInstance().getReference("Users")
        if (uid.isNotEmpty()){
            getUserData()
        }

        listOf(
            binding.cikisButton
        ).forEach {
            it.setOnClickListener(this)
        }

    }

    private fun getUserData() {
        databaseReference.child(uid).addValueEventListener(object : ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                user = snapshot.getValue(User::class.java)!!
                binding.userFullnameTextView.setText(user.isim + " " + user.soyisim)
                binding.isimTextView.setText(user.isim)
                binding.soyisimTextView.setText(user.soyisim)
                binding.dogumTextView.setText(user.dogum)
                binding.boyTextView.setText(user.boy)
                binding.kiloTextView.setText(user.kilo)
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }

        })
    }

    override fun onClick(view: View?) {

    }


}