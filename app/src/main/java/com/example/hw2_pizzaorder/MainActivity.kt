package com.example.hw2_pizzaorder

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.CheckBox
import android.widget.Switch
import kotlinx.android.synthetic.main.activity_main.*
import java.math.BigDecimal

class MainActivity : AppCompatActivity() {

    private var total = BigDecimal.valueOf(9.99)
    private var previousSize = "medium"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        totalPrice.text = "Total Price: $$total"

        val pizzaList = listOf("Pepperoni", "Margheritta", "Hawaiian", "BBQ Chicken")
        val myAdapter = ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, pizzaList)
        pizza_list.adapter = myAdapter

        pizza_list.setOnItemClickListener { list, item, position, id ->
            val imageId = when(position)
            {
                0 -> R.drawable.pepperoni
                1 -> R.drawable.margheritta
                2 -> R.drawable.hawaiian
                else -> R.drawable.bbq_chicken
            }
            val typeID = when(position)
            {
                0 -> "Pepperoni"
                1 -> "Margheritta"
                2 -> "Hawaiian"
                else -> "BBQ Chicken"
            }

            pizzaType.text = typeID

            pizzaImage.setImageResource(imageId)
        }

        sizeGroup.setOnCheckedChangeListener { group, checkedId ->
            when (previousSize) {
                "medium" -> {total -= BigDecimal.valueOf(9.99)}
                "large" -> {total -= BigDecimal.valueOf(13.99)}
                "xl" -> {total -= BigDecimal.valueOf(15.99)}
            }

            when (checkedId) {
                radioMedium.id -> {
                    total += BigDecimal.valueOf(9.99)
                    previousSize = "medium"
                }
                radioLarge.id -> {
                    total += BigDecimal.valueOf(13.99)
                    previousSize = "large"
                }
                radioXL.id -> {
                    total += BigDecimal.valueOf(15.99)
                    previousSize = "xl"
                }
            }

            totalPrice.text = "Total Price: $$total"
        }
    }

    fun onToppingsClicked(view: View)
    {
        if(view is CheckBox)
        {
            val checked = view.isChecked
            val toppingsCost = BigDecimal.valueOf(1.69)

            when(view.id)
            {
                R.id.checkCheese -> {if(checked){total += toppingsCost}else{total -= toppingsCost}}
                R.id.checkSpinach -> {if(checked){total += toppingsCost}else{total -= toppingsCost}}
                R.id.checkPeppers -> {if(checked){total += toppingsCost}else{total -= toppingsCost}}
                R.id.checkOnions -> {if(checked){total += toppingsCost}else{total -= toppingsCost}}
                R.id.checkMushrooms -> {if(checked){total += toppingsCost}else{total -= toppingsCost}}
                R.id.checkBroccoli -> {if(checked){total += toppingsCost}else{total -= toppingsCost}}
                R.id.checkOlives -> {if(checked){total += toppingsCost}else{total -= toppingsCost}}
                R.id.checkTomatoes -> {if(checked){total += toppingsCost}else{total -= toppingsCost}}
            }

            totalPrice.text = "Total Price: $$total"
        }
    }

    fun onDeliveryClicked(view: View)
    {
        val deliveryCost = BigDecimal.valueOf(2.00)
        val swDelivery = findViewById<Switch>(R.id.switchDelivery)

        if(swDelivery.isChecked)
        {
            total += deliveryCost
            switchDelivery.text = "Yes, $2.00"
        }
        else
        {
            total -= deliveryCost
            switchDelivery.text = "No, $0.00"
        }

        totalPrice.text = "Total Price: $$total"
    }
}
