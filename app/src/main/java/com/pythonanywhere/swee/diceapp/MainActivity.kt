package com.pythonanywhere.swee.diceapp

import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    fun toast(message: CharSequence) =
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        var random = 0
        val sides: EditText = findViewById(R.id.editTextNumber2) //Define the Sides TextBox
        val dices: EditText = findViewById(R.id.editTextNumber) //Define the Sides TextBox
        val rollButton: Button = findViewById(R.id.button) //Defines the Roll Button
        val image: ImageView = findViewById(R.id.imageView) //Defines the First Image
        val image2: ImageView = findViewById(R.id.imageView2) //Defines the Second image
        val image3: ImageView = findViewById(R.id.imageView3) //Defines the Third image
        val images: CheckBox = findViewById(R.id.checkBox) //Defines the "Images" checkbox
        val ones = listOf(R.drawable.one,R.drawable.two,R.drawable.three,R.drawable.four,R.drawable.five,R.drawable.six,R.drawable.seven,R.drawable.eight,R.drawable.nine,R.drawable.ten) //maps all the Dice images
        rollButton.setOnClickListener {// When the Roll button is clicked
            try {
                image.visibility = View.INVISIBLE // Hides the images first:
                image2.visibility = View.INVISIBLE // :
                image3.visibility = View.INVISIBLE // :End
                val abc = try {sides.text.toString().toInt()}catch (e:Exception){0} //Turns the value of the sides TextBox to int
                val abcd = try {dices.text.toString().toInt()}catch (e:Exception){0} //Turns the value of the dice TextBox to int
                val resultTextView: TextView = findViewById(R.id.vales) //Defines the Result Text
                var vals = listOf((1..abc).random()) //List of values
                if (abcd > 30) {
                    toast("The dice value is abnormally big! You will get visual issues!")
                }
                if (abc > 500) {
                    toast("The sides value is abnormally big! You will get visual issues and/or lag!")
                }
                for (dice in 2..dices.text.toString().toInt()) { //for loop to make random numbers
                    random = (1..abc).random()
                    vals = vals.plus(random)
                }
                var lists = "" //lists is the string to compile the list to a string
                if (vals.count() == 1) { //Validates the requirements to use images
                    if (images.isChecked and (dices.text.toString() == "1") and (sides.text.toString().toInt() <= 10)) { //makes image 1 visible
                        image.visibility = View.VISIBLE
                        image.setImageResource(ones.get(vals.get(0) - 1))
                    }else {
                        if (images.isChecked) {
                            image.visibility = View.VISIBLE
                            image.setImageResource(R.drawable.nope)
                        }
                    }


                    lists = vals.get(0).toString()
                }else {
                    if (images.isChecked and (dices.text.toString() == "2") and (sides.text.toString().toInt() <= 10)) { //makes image 1 and 2 visible and sets it to their respective images
                        if (sides.text.toString().toInt() < 11) {
                            image.visibility = View.VISIBLE
                            image.setImageResource(ones.get(vals.get(1) - 1))
                            image2.visibility = View.VISIBLE
                            image2.setImageResource(ones.get(vals.get(0) - 1))
                        } else {

                            image.visibility = View.VISIBLE
                            image.setImageResource(R.drawable.nope)
                        }

                    }else if (images.isChecked and (dices.text.toString() == "3") and (sides.text.toString().toInt() < 11)) { //makes all images visible and sets it to their respective images
                        if (sides.text.toString().toInt() < 11) {
                            image.visibility = View.VISIBLE
                            image.setImageResource(ones.get(vals.get(1) - 1))
                            image2.visibility = View.VISIBLE
                            image2.setImageResource(ones.get(vals.get(0) - 1))
                            image3.visibility = View.VISIBLE
                            image3.setImageResource(ones.get(vals.get(2) - 1))
                        } else {
                            image.visibility = View.VISIBLE
                            image.setImageResource(R.drawable.nope)
                        }
                    }else if (images.isChecked) {
                        image.visibility = View.VISIBLE
                        image.setImageResource(R.drawable.nope)
                    }
                    for (i in vals) { // compiles the list to lists
                        lists += i.toString() + "  "
                    }
                }
                resultTextView.text = lists // sets the textView to the compiled list
            }catch(e: NoSuchElementException){
                val resultTextView: TextView = findViewById(R.id.vales)
                resultTextView.text = getString(R.string.sideserror)
            }catch(e: java.lang.NumberFormatException){
                val resultTextView: TextView = findViewById(R.id.vales)
                resultTextView.text = getString(R.string.diceerror)
            }
        }
    }
}