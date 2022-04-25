package com.valentine.challengechapter5.UI_ListingMovie

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.valentine.challengechapter5.ORM.UserDatabase
import com.valentine.challengechapter5.R
import com.valentine.challengechapter5.ViewModel.MainViewModel
import com.valentine.challengechapter5.databinding.FragmentHomeBinding
import com.valentine.challengechapter5.model.MainAdapter

class HomeFragment : Fragment() {
    private var username: String? = null
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private val PREFS_NAME = "sfuser"
    private var mDB: UserDatabase? = null
    private val ViewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setupSecondObserver()

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val view = binding.root
        //initialization database room
        mDB = UserDatabase.getInstance(requireContext())
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //casting data
        val sharedPreferences:SharedPreferences =requireActivity().getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
        username = sharedPreferences.getString("login", "default")

        val editor: SharedPreferences.Editor = sharedPreferences.edit()

        Log.d("cek loginnn", username.toString())
        binding.txtUsername.text = "welcome, $username"
        if (username ==  "default") {
            editor.clear()
            editor.apply()
            findNavController().navigate(R.id.action_homeFragment_to_loginFragment2)

        }
        binding.imgUser.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_profileFragment)
        }

    }

    private fun setupSecondObserver() {
        Log.d("Tag", "Fragment activity : datanya ->")
        ViewModel.getMovies().observe(requireActivity()) {
            Log.d("Tag", "Fragment activity : datanya -> $it")
            val adapter = MainAdapter(it)
            val layoutManager =
                LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
            binding.rvMain.layoutManager = layoutManager
            binding.rvMain.adapter = adapter
        }


    }

}