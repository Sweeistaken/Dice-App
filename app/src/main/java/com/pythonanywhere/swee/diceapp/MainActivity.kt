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
        val invalidDiceImage = R.drawable.nope
        val sidesBox: EditText = findViewById(R.id.editTextNumber2)
        val diceBox: EditText = findViewById(R.id.editTextNumber)
        val rollButton: Button = findViewById(R.id.button)
        val image: ImageView = findViewById(R.id.imageView)
        val image2: ImageView = findViewById(R.id.imageView2)
        val image3: ImageView = findViewById(R.id.imageView3)
        val imgCheckBox: CheckBox = findViewById(R.id.switch1)
        val diceImages = listOf(R.drawable.one,R.drawable.two,R.drawable.three,R.drawable.four,R.drawable.five,R.drawable.six,R.drawable.seven,R.drawable.eight,R.drawable.nine,R.drawable.ten) //maps all the Dice images
        rollButton.setOnClickListener {
            try {
                image.visibility = View.INVISIBLE
                image2.visibility = View.INVISIBLE
                image3.visibility = View.INVISIBLE
                val sidesValue = sidesBox.text.toString().toInt()
                val diceValue = diceBox.text.toString().toInt()
                val resultTextView: TextView = findViewById(R.id.vales)
                var values = listOf((1..sidesValue).random())
                for (dice in 2..diceBox.text.toString().toInt()) {
                    val num = (1..sidesValue).random()
                    values = values.plus(num)
                }
                var result = ""
                if (values.count() == 1) {
                    if (imgCheckBox.isChecked and (diceValue == 1) and (sidesValue <= 10)) { //makes image 1 visible
                        image.visibility = View.VISIBLE
                        image.setImageResource(diceImages[values[0] - 1])
                    }else {
                        if (imgCheckBox.isChecked) {
                            image.visibility = View.VISIBLE
                            image.setImageResource(invalidDiceImage)
                        }
                    }
                    result = values[0].toString()
                }else {
                    if (imgCheckBox.isChecked and (diceValue == 2) and (sidesValue <= 10)) {
                        if (sidesValue < 11) {
                            image.visibility = View.VISIBLE
                            image.setImageResource(diceImages[values[1] - 1])
                            image2.visibility = View.VISIBLE
                            image2.setImageResource(diceImages[values[0] - 1])
                        } else {
                            image.visibility = View.VISIBLE
                            image.setImageResource(invalidDiceImage)
                        }

                    }else if (imgCheckBox.isChecked and (diceValue == 3) and (sidesValue < 11)) {
                        if (sidesValue < 11) {
                            image.visibility = View.VISIBLE
                            image.setImageResource(diceImages[values[1] - 1])
                            image2.visibility = View.VISIBLE
                            image2.setImageResource(diceImages[values[0] - 1])
                            image3.visibility = View.VISIBLE
                            image3.setImageResource(diceImages[values[2] - 1])
                        } else {
                            image.visibility = View.VISIBLE
                            image.setImageResource(invalidDiceImage)
                        }
                    }else if (imgCheckBox.isChecked) {
                        image.visibility = View.VISIBLE
                        image.setImageResource(invalidDiceImage)
                    }
                    for (i in values) {
                        result += "$i  "
                    }
                }
                resultTextView.text = result
            }catch(e: NoSuchElementException){
                toast(getString(R.string.sidesError))
            }catch(e: java.lang.NumberFormatException){
                toast(getString(R.string.diceError))
            }
        }
    }
}