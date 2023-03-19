package com.example.androidbep

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import java.security.AccessControlContext

class MainActivity4 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main4)


        var restaurantlist = arrayListOf<restaurant>()

        restaurantlist.add(restaurant(1,"UNDER THE SKY", R.drawable.img1))
        restaurantlist.add(restaurant(2,"HOMELY", R.drawable.img2))
        restaurantlist.add(restaurant(3,"DOMINOS", R.drawable.domino))
        restaurantlist.add(restaurant(4,"KEVENTERS", R.drawable.images))
        restaurantlist.add(restaurant(5,"restaurant e", R.drawable.images))
        restaurantlist.add(restaurant(6,"restaurant f", R.drawable.images))
        restaurantlist.add(restaurant(7,"restaurant g", R.drawable.images))

        var restaurantlistrview =findViewById<RecyclerView>(R.id.restaurant_list)
        restaurantlistrview.layoutManager = LinearLayoutManager(this)
        restaurantlistrview.adapter = Restaurantadapter(this,restaurantlist)

    }
}

class Restaurantadapter(var context: Context,var restaurantlist:ArrayList<restaurant>): RecyclerView.Adapter<Restaurantadapter.restaurantviewholder>() {
    class restaurantviewholder(itemview: View) : RecyclerView.ViewHolder(itemview) {
        var restauranttextview = itemview.findViewById<TextView>(R.id.text1)
        var restaurantImageView = itemview.findViewById<ImageView>(R.id.image1)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewtype: Int):  restaurantviewholder{
        val view =LayoutInflater.from(context).inflate(R.layout.activity_main3,parent,false)
        return restaurantviewholder(view)
    }

    override fun onBindViewHolder(holder: restaurantviewholder, position: Int) {
        var restaurantmodel = restaurantlist.get(position)
        holder.restauranttextview.text = restaurantmodel.name
        holder.restaurantImageView.setImageResource(restaurantmodel.img)

        val eachCard = holder.itemView
        eachCard.setOnClickListener {
            val intent = Intent(context, ParticularRestaurant::class.java)
            intent.putExtra("name", restaurantmodel.name)
            intent.putExtra("img", restaurantmodel.img)
            context.startActivity(intent)
        }
    }
    override fun getItemCount(): Int {
        return restaurantlist.size
    }

}

data class restaurant(var id:Int, var name:String, val img: Int)









