package com.example.retrofit

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.retrofit.R
import com.example.retrofit.api.ApiInterface
import com.example.retrofit.api.RetrofitClient
import com.example.retrofit.helper.AppHelper
import com.example.retrofit.models.DataItem
import com.example.retrofit.models.SinglePageResponse
import com.google.gson.Gson
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import kotlin.math.log


class MainActivity : AppCompatActivity() {
    lateinit var txtData: TextView
    lateinit  var retrofit: Retrofit
    lateinit var apiInterface: ApiInterface
    var progressBar: ProgressBar? = null
    lateinit var userListView: RecyclerView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initData()

        getSinglePage()
    }
    private fun initData() {
        /*Creating the instance of retrofit */
        retrofit = RetrofitClient.getInstance()

        /*Get the reference of Api interface*/
        apiInterface = retrofit.create(ApiInterface::class.java)
    }

    private fun getSinglePage() {
        /*Internet check*/
        if(AppHelper.isConnected(this))
        {
            progressBar?.visibility = View.VISIBLE
            apiInterface.getSinglePage(2)?.enqueue(object : Callback<SinglePageResponse?> {
                override fun onResponse(
                    call: Call<SinglePageResponse?>,
                    response: Response<SinglePageResponse?>
                ) {
                    /*Set you data to adapter here*/
                    progressBar?.visibility = View.GONE
                    if (response?.isSuccessful!!) {
                    userListView =findViewById<RecyclerView>(R.id.user_recycler)
                    userListView.layoutManager =
                        LinearLayoutManager(this@MainActivity)
                   userListView.adapter = UserAdapter(this@MainActivity,
                       response.body()?.data as List<DataItem>
                   )

                    } else {
                        Toast.makeText(this@MainActivity, "Error", Toast.LENGTH_LONG).show()
                    }
                }

                override fun onFailure(call: Call<SinglePageResponse?>, t: Throwable) {
                    progressBar?.visibility = View.GONE
                    t.printStackTrace()

                    Toast.makeText(this@MainActivity, "Something went wrong", Toast.LENGTH_LONG).show()
                }

            })
        }
        else
        {
            Toast.makeText(this@MainActivity, "Please check you internet connection", Toast.LENGTH_LONG).show()
        }
    }
}

class UserAdapter (var context:Context, val userList :List<DataItem>): RecyclerView.Adapter<UserAdapter.UserViewHolder>() {
    class UserViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val name = itemView.findViewById<TextView>(R.id.name)
        val email = itemView.findViewById<TextView>(R.id.email)
        val img = itemView.findViewById<ImageView>(R.id.userimg)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.activity_single_user,parent,false)
        return UserViewHolder(view)
    }

    override fun getItemCount(): Int {
        return userList.size!!
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        var usermodel = userList.get(position)
        val fullName = usermodel?.firstName + usermodel?.lastName
        holder.name.text = fullName
        holder.email.text = usermodel?.email
        val imageUrl = usermodel.avatar
        Log.e("avatar", "$imageUrl")
        Glide.with(context)
            .load(imageUrl)
            .into(holder.img)


    }
}


data class User(var name : String, var email : String)