package com.example.myapplication

import android.app.Activity
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.Cart.Companion.cartItems

class Cart : AppCompatActivity() {

    var deletedItems = arrayListOf<Int>()

    override fun onBackPressed() {
        Log.e("onBackPressed", "onbckpress called")
        val intent = Intent(this, LandingPage::class.java)
        intent.putIntegerArrayListExtra("deletedList", deletedItems)

        setResult(Activity.RESULT_OK, intent)

        super.onBackPressed()
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cart)
        var cartlistrview = findViewById<RecyclerView>(R.id.recyclecartitems)
        cartlistrview.layoutManager =
            LinearLayoutManager(this)
        cartlistrview.adapter = Cartadapter(this, cartItems)

        var backbtn = findViewById<ImageView>(R.id.backbtn)
        backbtn.setOnClickListener {
            onBackPressed()
        }
        updateCartPrice()
    }


    companion object {
        var cartItems = mutableListOf<Product>()
    }


    fun updateCartPrice() {
        var totalPrice = 0
        cartItems.forEachIndexed { index, product ->
            totalPrice +=  (product.price.toInt()) * product.itemCount
        }
        val ordervalue = findViewById<TextView>(R.id.text3)
        ordervalue.text = "$" + totalPrice.toString()
        val total = findViewById<TextView>(R.id.text5)
        total.text = "$" + totalPrice.toString()
    }
}

class Cartadapter(var context: Context, var cartList: MutableList<Product>): RecyclerView.Adapter<Cartadapter.cartviewholder>() {
    class cartviewholder(itemview: View) : RecyclerView.ViewHolder(itemview) {
        var productCartTextView1 = itemview.findViewById<TextView>(R.id.t1)
        var productCartTextView2 = itemview.findViewById<TextView>(R.id.t2)
        var productCartTextView3 = itemview.findViewById<TextView>(R.id.t3)
        var productCartImageView = itemview.findViewById<ImageView>(R.id.img1)
        var deleteBtn = itemview.findViewById<TextView>(R.id.delBtn)
        var spinnerItem = itemview.findViewById<Spinner>(R.id.spin)
        var placeOrderBtn = itemview.findViewById<Button>(R.id.placeorderbtn)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewtype: Int):  cartviewholder{
        val view = LayoutInflater.from(context).inflate(R.layout.activity_single_cart_prod,parent,false)
        return cartviewholder(view)
    }

    override fun onBindViewHolder(holder: cartviewholder, position: Int) {
        var cartmodel = cartList.get(position)
        holder.productCartTextView1.text = cartmodel.name
        holder.productCartTextView2.text = cartmodel.des
        holder.productCartTextView3.text = cartmodel.item.toString()
        holder.productCartImageView.setImageResource(cartmodel.img)

        ArrayAdapter.createFromResource(
            context,
            R.array.sizes,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            holder.spinnerItem.adapter = adapter
        }

        holder.spinnerItem.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
//                Log.i("count",cartlist.toString())
                for (product in cartItems) {
                    if (cartmodel.id == product.id) {
                        val totalOrderValue = holder.spinnerItem.getSelectedItem().toString()
                        product.itemCount = totalOrderValue.toInt()
                    }
                }
                (context as Cart).updateCartPrice()
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {

            }
        }


        holder.deleteBtn.setOnClickListener {
           cartList.removeAt(position)
            notifyItemRemoved(position)
            (context as Cart).deletedItems.add(cartmodel.id)
            (context as Cart).updateCartPrice()
        }

//        holder.placeOrderBtn.setOnClickListener {
//            intent = Intent (this,)
//        }


    }
    override fun getItemCount(): Int {
        return cartList.size
    }

}

data class CartItems(var id:Int, var name:String, val item : Int, val des : String,val img: Int, val price : String, val about : String, var itemCount : Int )
