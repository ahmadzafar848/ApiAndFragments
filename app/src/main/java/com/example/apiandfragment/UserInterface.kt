package com.example.apiandfragment

import retrofit2.Call
import retrofit2.http.GET

interface UserInterface {
    @GET("/users")
    fun getUsers():Call<ArrayList<UserModelItem>>
}