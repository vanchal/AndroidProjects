package com.example.myapplication

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
private  var activityResultLauncher : ActivityResultLauncher<Intent>? = null
class LandingPage : AppCompatActivity() {
//  private lateinit var adapter: Shoppingadapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_landing_page)


        var productList = arrayListOf<Product>()

        productList.add(Product(0,"Green Kurti",100,"clothing", R.drawable.s1,"100","Green Floral Kurti",false,1))
        productList.add(Product(1,"Dress",50,"clothing", R.drawable.s2,"30","Dangree",false,1))
        productList.add(Product(2,"Black Dress",110,"clothing", R.drawable.s3,"50","Black Floral Dress",false,1))
        productList.add(Product(3,"Kurta",80,"clothing", R.drawable.s4,"20","Blue printed Kurti",false,1))
        productList.add(Product(4,"Black Shirt",90,"clothing", R.drawable.s5,"40","Black Fit Shirt",false,1))
        productList.add(Product(5,"Blue Denim",20,"clothing", R.drawable.s8,"50","Blue Denim Shirt",false,1))
        productList.add(Product(6,"Check Shirt",50,"clothing" ,R.drawable.s7,"60","Blue Check Shirt",false,1))

        var shoppinglistrview =findViewById<RecyclerView>(R.id.shopping_list)
        shoppinglistrview.layoutManager =
            GridLayoutManager(this, 2)
        shoppinglistrview.adapter = Shoppingadapter(this,productList)

//        val adapter = Shoppingadapter(this,productList)
           activityResultLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {result ->
            Log.e("activityResultLauncher", "registerForActivityResult called")
            if(result.resultCode == RESULT_OK){
                var list= result.data?.getIntegerArrayListExtra("deletedList") as ArrayList<Int>?

                val set = list?.let { HashSet(it) }
                productList.forEachIndexed{ index, item ->
                    if (set != null) {
                        if(set.contains(item.id)){
                            Log.d("___", result.resultCode.toString())
                            item.state=false
                            (shoppinglistrview.adapter as Shoppingadapter).notifyItemChanged(index)
                            Log.d("___-->",list.toString());
                        }
                    }
                }
//                runOnUiThread {
//                    Log.d("___", "notify cLLWS")
//                    adapter.notifyDataSetChanged()
//                }
            }
        }



        var cartIcon = findViewById<ImageView>(R.id.cart)
        cartIcon.setOnClickListener {
            val intent = Intent(this,Cart::class.java)
            activityResultLauncher?.launch(intent)
        }
    }
}

class Shoppingadapter(var context: Context, var productList:ArrayList<Product>): RecyclerView.Adapter<Shoppingadapter.shoppingviewholder>() {
    class shoppingviewholder(itemview: View) : RecyclerView.ViewHolder(itemview) {
        var shoppingtextview = itemview.findViewById<TextView>(R.id.text1)
        var shoppingImageView = itemview.findViewById<ImageView>(R.id.prod1)
        var addButton = itemview.findViewById<Button>(R.id.addBtn)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewtype: Int):  shoppingviewholder{
        val view = LayoutInflater.from(context).inflate(R.layout.activity_card,parent,false)
        return shoppingviewholder(view)
    }

    override fun onBindViewHolder(holder: shoppingviewholder, position: Int) {
        Log.e("model", "onBindViewHolder Cll")
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

        Log.e("model", ""+shoppingmodel.state + " "+shoppingmodel.name)

        if(shoppingmodel.state)
        {
            holder.addButton.text="Go to cart"
        }
        else
        {
            holder.addButton.text="ADD"
        }


        holder.addButton.setOnClickListener {
            if(shoppingmodel.state == true){
                    val intent = Intent(context,Cart::class.java)
                    activityResultLauncher?.launch(intent)
//                    context.startActivity(intent)
                    return@setOnClickListener
            }
            shoppingmodel.state = true;
            holder.addButton.text = "Go To Cart"
            Cart.cartItems.add(shoppingmodel)
        }

    }
    override fun getItemCount(): Int {
        return productList.size
    }

//    fun updateState(pos:Int, state: Boolean){
//        productList.get(pos).state = state
//        notifyItemChanged(pos)
//    }

}

data class Product(var id:Int, var name:String, val item : Int, val des : String,val img: Int, val price : String, val about : String, var state : Boolean, var itemCount : Int )

