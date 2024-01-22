package com.pythonanywhere.swee.diceapp

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    private fun toast(message: CharSequence) =
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    @SuppressLint("UseSwitchCompatOrMaterialCode")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val nope = R.drawable.nope
        val sides: EditText = findViewById(R.id.editTextNumber2)
        val dices: EditText = findViewById(R.id.editTextNumber)
        val rollButton: Button = findViewById(R.id.button)
        val image: ImageView = findViewById(R.id.imageView)
        val image2: ImageView = findViewById(R.id.imageView2)
        val image3: ImageView = findViewById(R.id.imageView3)
        val images: Switch = findViewById(R.id.switch1)
        val ones = listOf(R.drawable.one,R.drawable.two,R.drawable.three,R.drawable.four,R.drawable.five,R.drawable.six,R.drawable.seven,R.drawable.eight,R.drawable.nine,R.drawable.ten) //maps all the Dice images
        rollButton.setOnClickListener {
            try {
                image.visibility = View.INVISIBLE
                image2.visibility = View.INVISIBLE
                image3.visibility = View.INVISIBLE
                val temp1 = try {sides.text.toString().toInt()}catch (e:Exception){0}
                val temp2 = try {dices.text.toString().toInt()}catch (e:Exception){0}
                val resultTextView: TextView = findViewById(R.id.vales)
                var values = listOf((1..temp1).random())
                if (temp2 > 30) {
                    toast("The dice value is abnormally big! You will get visual issues and/or lag!")
                }
                if (temp1 > 500) {
                    toast("The sides value is abnormally big! You will get visual issues and/or lag!")
                }
                for (dice in 2..dices.text.toString().toInt()) {
                    val num = (1..temp1).random()
                    values = values.plus(num)
                }
                var lists = ""
                if (values.count() == 1) {
                    if (images.isChecked and (dices.text.toString() == "1") and (sides.text.toString().toInt() <= 10)) { //makes image 1 visible
                        image.visibility = View.VISIBLE
                        image.setImageResource(ones[values[0] - 1])
                    }else {
                        if (images.isChecked) {
                            image.visibility = View.VISIBLE
                            image.setImageResource(nope)
                        }
                    }


                    lists = values[0].toString()
                }else {
                    if (images.isChecked and (dices.text.toString() == "2") and (sides.text.toString().toInt() <= 10)) {
                        if (sides.text.toString().toInt() < 11) {
                            image.visibility = View.VISIBLE
                            image.setImageResource(ones[values[1] - 1])
                            image2.visibility = View.VISIBLE
                            image2.setImageResource(ones[values[0] - 1])
                        } else {

                            image.visibility = View.VISIBLE
                            image.setImageResource(nope)
                        }

                    }else if (images.isChecked and (dices.text.toString() == "3") and (sides.text.toString().toInt() < 11)) {
                        if (sides.text.toString().toInt() < 11) {
                            image.visibility = View.VISIBLE
                            image.setImageResource(ones[values[1] - 1])
                            image2.visibility = View.VISIBLE
                            image2.setImageResource(ones[values[0] - 1])
                            image3.visibility = View.VISIBLE
                            image3.setImageResource(ones[values[2] - 1])
                        } else {
                            image.visibility = View.VISIBLE
                            image.setImageResource(nope)
                        }
                    }else if (images.isChecked) {
                        image.visibility = View.VISIBLE
                        image.setImageResource(nope)
                    }
                    for (i in values) {
                        lists += "$i  "
                    }
                }
                resultTextView.text = lists
            }catch(e: NoSuchElementException){
                toast(getString(R.string.sideserror))
            }catch(e: java.lang.NumberFormatException){
                toast(getString(R.string.diceerror))
            }
        }
    }
}