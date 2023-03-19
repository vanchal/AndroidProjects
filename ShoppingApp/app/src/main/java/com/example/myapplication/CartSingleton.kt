package com.example.myapplication

object CartSingleton {
    var cartList = arrayListOf<CartItem>()

    fun addItem(product: Product) {
        cartList.add(CartItem(product, 1))
    }

    fun removeItem(product: Product) {
        cartList.remove(CartItem(product, 1))
    }

    fun modifyQuantity(product: Product, newQuantity : Int) {
        val prod = cartList.get(product.id)



    }
}
data class CartItem(var product : Product, var quantity : Int) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as CartItem

        if (product.id != other.product.id) return false

        return true
    }

    override fun hashCode(): Int {
        return product.hashCode()
    }
}



//import android.content.Context
//import android.widget.Toast
//import io.paperdb.Paper
//
//class ShoppingCart {
//
//    companion object {
//        fun addItem(cartItem: CartItem) {
//            val cart = ShoppingCart.getCart()
//
//            val targetItem = cart.singleOrNull { it.product.id == cartItem.product.id }
//            if (targetItem == null) {
//                cartItem.quantity++
//                cart.add(cartItem)
//            } else {
//                targetItem.quantity++
//            }
//            ShoppingCart.saveCart(cart)
//        }
//
//        fun removeItem(cartItem: CartItem, context: Context) {
//            val cart = ShoppingCart.getCart()
//
//            val targetItem = cart.singleOrNull { it.product.id == cartItem.product.id }
//            if (targetItem != null) {
//                if (targetItem.quantity > 0) {
//                    targetItem.quantity--
//                } else {
//                    cart.remove(targetItem)
//                }
//            }
//
//            ShoppingCart.saveCart(cart)
//        }
//
//        fun saveCart(cart: MutableList<CartItem>) {
//            Paper.book().write("cart", cart)
//        }
//
//        fun getCart(): MutableList<CartItem> {
//            return Paper.book().read("cart", mutableListOf())
//        }
//
//        fun getShoppingCartSize(): Int {
//            var cartSize = 0
//            ShoppingCart.getCart().forEach {
//                cartSize += it.quantity;
//            }
//
//            return cartSize
//        }
//    }
//
//}

//object Helper {
//    var db : Database? = null
//    fun getInstance(): DataBase{
//        if(db==null){
//            db = DataBase.newInstance()
//        }
//        return db
//    }
//}