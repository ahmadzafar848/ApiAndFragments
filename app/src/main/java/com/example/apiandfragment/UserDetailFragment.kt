package com.example.apiandfragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.gson.Gson


class UserDetailFragment : Fragment() {
    // TODO: Rename and change types of parameters

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val userModelJson=requireArguments().getString("userModel")
        if (userModelJson!=null){
            val userModel=Gson().fromJson(userModelJson,UserModelItem::class.java)
            Log.d("Ahmad", "onViewCreated: ${userModel.id.toString()}")
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view=inflater.inflate(R.layout.fragment_user_detail, container, false)

        return view
    }

}