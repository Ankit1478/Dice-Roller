package com.example.diceroler

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.diceroler.databinding.ActivityMainBinding
import kotlin.random.Random

class MainActivity : AppCompatActivity() {

    private val binding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    var score1 = 0
    var score2 = 0
    var isFirstplayer = true
    var gameOver = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        binding.player1score.text = "Player 1 Score: $score1"
        binding.player2score.text = "Player 2 Score: $score2"

        binding.diceimage.setOnClickListener {
            if (!gameOver) {
                val number = Random.nextInt(1, 7)

                val image = when (number) {
                    1 -> R.drawable.dice1
                    2 -> R.drawable.dice2
                    3 -> R.drawable.dice3
                    4 -> R.drawable.dice4
                    5 -> R.drawable.dice5
                    else -> R.drawable.dice6
                }

                if (isFirstplayer) {
                    score1 += number
                    binding.player1score.text = "Player 1 Score: $score1"
                } else {
                    score2 += number
                    binding.player2score.text = "Player 2 Score: $score2"
                }

                if (score1 >= 20 || score2 >= 20) {
                    val winner = if (score1 > score2) "Player 1" else "Player 2"
                    binding.resultView.visibility = View.VISIBLE
                    binding.winner.text = "Winner is $winner and score is ${if (winner == "Player 1") score1 else score2}"
                    gameOver = true
                    binding.diceimage.isClickable = false
                }

                isFirstplayer = !isFirstplayer
                binding.rollDice.text = if (isFirstplayer) "Roll for Player 1" else "Roll for Player 2"
                binding.diceimage.setImageResource(image)
            }
        }
        binding.playAgainButton.setOnClickListener {
            score1 = 0
            score2 = 0
            isFirstplayer = true
            gameOver = false

            binding.player1score.text = "Player 1 Score: $score1"
            binding.player2score.text = "Player 2 Score: $score2"
            binding.resultView.visibility = View.GONE
            binding.diceimage.isClickable = true
            binding.rollDice.text = "Roll for Player 1"
        }

    }
}
