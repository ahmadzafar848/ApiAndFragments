package com.example.apiandfragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.gson.Gson
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create

class UserFragment : Fragment() {
    private lateinit var userRv: RecyclerView
//    private lateinit var navController: NavController

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
//        navController=Navigation.findNavController(view)

        findNavController()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.fragment_user, container, false)
        userRv = view.findViewById<RecyclerView>(R.id.userRV)
        userRv.layoutManager = LinearLayoutManager(requireContext())
        getListOfUsers()
        return view

    }

    fun getListOfUsers() {
        val retrofit =
            Retrofit.Builder().baseUrl("https://jsonplaceholder.typicode.com").addConverterFactory(GsonConverterFactory.create())
                .build().create(UserInterface::class.java)

        val retroData=retrofit.getUsers()
        retroData.enqueue(object :Callback<ArrayList<UserModelItem>>{
            override fun onResponse(
                call: Call<ArrayList<UserModelItem>>,
                response: Response<ArrayList<UserModelItem>>
            ) {
               if (response.isSuccessful){
                   val responseBody=response.body()
                   if (responseBody!=null){
                       userRv.adapter=UserAdapter(responseBody, object :UserAdapter.OnItemClickListener{
                           override fun onItemClicked(userModelItem: UserModelItem) {
                              Toast.makeText(requireContext(),userModelItem.id.toString(),Toast.LENGTH_SHORT).show()
                              val bundle=Bundle()
                               bundle.putString("userModel", Gson().toJson(userModelItem))
                               findNavController().navigate(R.id.action_userFragment_to_userDetailFragment,bundle)
                           }

                       })
                   }else{
                       Toast.makeText(requireContext(),"Body Null",Toast.LENGTH_SHORT).show()
                   }

               }else{
                   Toast.makeText(requireContext(),response.code().toString(),Toast.LENGTH_SHORT).show()
               }
            }

            override fun onFailure(call: Call<ArrayList<UserModelItem>>, t: Throwable) {
            Toast.makeText(requireContext(),t.toString(),Toast.LENGTH_LONG).show()
            }

        }
        )
    }


}