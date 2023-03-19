package com.example.myapplication

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView

class LandingPage : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_landing_page)



    val productList = arrayListOf<Product>()

        productList.add(Product(1,"Green Kurti",100,"clothing", R.drawable.s1,"$100","Green Floral Kurti"))
        productList.add(Product(2,"Dress",50,"clothing", R.drawable.s2,"$30","Dangree"))
        productList.add(Product(3,"Black Dress",110,"clothing", R.drawable.s3,"$50","Black Floral Dress"))
        productList.add(Product(4,"Kurta",80,"clothing", R.drawable.s4,"$20","Blue printed Kurti"))
        productList.add(Product(5,"Black Shirt",90,"clothing", R.drawable.s5,"$40","Black Fit Shirt"))
        productList.add(Product(6,"Blue Denim",20,"clothing", R.drawable.s8,"$50","Blue Denim Shirt"))
        productList.add(Product(7,"Check Shirt",50,"clothing" ,R.drawable.s7,"$60","Blue Check Shirt"))

        var shoppinglistrview =findViewById<RecyclerView>(R.id.shopping_list)
    shoppinglistrview.layoutManager =
        GridLayoutManager(this, 2)
    shoppinglistrview.adapter = Shoppingadapter(this,productList)

}
}

class Shoppingadapter(var context: Context,var productList:ArrayList<Product>): RecyclerView.Adapter<Shoppingadapter.shoppingviewholder>() {
    class shoppingviewholder(itemview: View) : RecyclerView.ViewHolder(itemview) {
        var shoppingtextview = itemview.findViewById<TextView>(R.id.text1)
        var shoppingImageView = itemview.findViewById<ImageView>(R.id.prod1)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewtype: Int):  shoppingviewholder{
        val view =LayoutInflater.from(context).inflate(R.layout.activity_card,parent,false)
        return shoppingviewholder(view)
    }

    override fun onBindViewHolder(holder: shoppingviewholder, position: Int) {
        var shoppingmodel = productList.get(position)
        holder.shoppingtextview.text = shoppingmodel.name
        holder.shoppingImageView.setImageResource(shoppingmodel.img)

        val eachCard = holder.itemView
        eachCard.setOnClickListener {
            val intent = Intent(context, ProdDetail::class.java)
            intent.putExtra("img1", shoppingmodel.img)
            intent.putExtra("img2",shoppingmodel.img)
            intent.putExtra("text1", shoppingmodel.name)
            intent.putExtra("text2", shoppingmodel.price)
            intent.putExtra("text3", shoppingmodel.about)

            context.startActivity(intent)
        }

    }



    override fun getItemCount(): Int {
        return productList.size
    }

}

data class Product(var id:Int, var name:String, val item : Int, val des : String,val img: Int, val price : String, val about : String )

