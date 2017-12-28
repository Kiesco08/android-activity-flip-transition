package com.example.franckmamboue.myfliptransition

import android.app.Fragment
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.support.v4.content.ContextCompat
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.activity_profile.*

class ProfileActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        overridePendingTransition(0, 0)
        setContentView(R.layout.activity_profile)
    }

    override fun onBackPressed() {

        // Add blank layer
        val blankHandler = Handler()
        blankHandler.postDelayed({
            container2.foreground = ContextCompat.getDrawable(this, R.color.white)
        }, 150)

        // Flip card
        flipCard()

        // Open profile activity
        val handler = Handler()
        handler.postDelayed({
            super.onBackPressed()
        }, 200)
    }

    override fun onPause() {
        super.onPause()
        overridePendingTransition(0, 0)
    }

    class CardFrontFragment : Fragment() {
        override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View {
            return inflater?.inflate(R.layout.activity_main, container, false)!!
        }
    }

    private fun flipCard() {

        // Create and commit a new fragment transaction that adds the fragment for
        // the back of the card, uses custom animations, and is part of the fragment
        // manager's back stack.

        fragmentManager
                .beginTransaction()

                // Replace the default fragment animations with animator resources
                // representing rotations when switching to the back of the card, as
                // well as animator resources representing rotations when flipping
                // back to the front (e.g. when the system Back button is pressed).
                .setCustomAnimations(
                        R.animator.flip_left_in,
                        R.animator.flip_left_out,
                        R.animator.flip_right_in,
                        R.animator.flip_right_out)

                // Replace any fragments currently in the container view with a
                // fragment representing the next page (indicated by the
                // just-incremented currentPage variable).
                .replace(R.id.container, CardFrontFragment())

                // Commit the transaction.
                .commit()
    }
}
