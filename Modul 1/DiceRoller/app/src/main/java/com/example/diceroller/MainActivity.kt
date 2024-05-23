package com.example.diceroller
import android.os.Bundle
import android.widget.Toast
import android.widget.Button
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val firstDiceImage: ImageView = findViewById(R.id.imageView)
        val secondDiceImage: ImageView = findViewById(R.id.imageView2)
        firstDiceImage.setImageResource(R.drawable.empty_dice)
        secondDiceImage.setImageResource(R.drawable.empty_dice)
        val rollButton: Button = findViewById(R.id.button)
        rollButton.setOnClickListener { rollDices() }
    }
    private fun rollDices() {
        val dice = Dice(6)
        val firstRoll = dice.roll()
        val secondRoll = dice.roll()
        val firstImageView: ImageView = findViewById(R.id.imageView)
        val secondImageView: ImageView = findViewById(R.id.imageView2)
        val firstDrawable = when (firstRoll) {
            1 -> R.drawable.dice_1
            2 -> R.drawable.dice_2
            3 -> R.drawable.dice_3
            4 -> R.drawable.dice_4
            5 -> R.drawable.dice_5
            else -> R.drawable.dice_6
        }
        val secondDrawable = when (secondRoll) {
            1 -> R.drawable.dice_1
            2 -> R.drawable.dice_2
            3 -> R.drawable.dice_3
            4 -> R.drawable.dice_4
            5 -> R.drawable.dice_5
            else -> R.drawable.dice_6
        }
        firstImageView.setImageResource(firstDrawable)
        secondImageView.setImageResource(secondDrawable)
        firstImageView.contentDescription = firstRoll.toString()
        secondImageView.contentDescription = secondRoll.toString()
        if (firstRoll != secondRoll) {
            Toast.makeText(this, "Anda belum beruntung!", Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(this, "Selamat anda dapat dadu double!", Toast.LENGTH_SHORT).show()
        }
    }
}
class Dice(private val sides: Int) {
    fun roll(): Int {
        return (1..sides).random()
    }
}
