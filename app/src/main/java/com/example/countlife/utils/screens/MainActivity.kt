package com.example.countlife.utils.screens

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.text.Editable
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuItem
import com.example.countlife.R
import com.example.countlife.utils.extension.countResult
import com.example.countlife.utils.extension.returnLifeColor
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.alert_change_life.view.*

class MainActivity : AppCompatActivity() {

    enum class Value(val VALUE: Int) {
        MIN(5),
        MAX(10),
        LIFE(10),
        BASELIFE(100)
    }

    enum class Operator(val VALUE: String) {
        PLUS("+"),
        MIN("-"),
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initButton()
        initView()
    }

    // INIT
    private fun initView(){
        playerLife.text = Value.BASELIFE.VALUE.toString()
        opponentLife.text = Value.BASELIFE.VALUE.toString()
    }

    private fun initButton(){
        // Player
        playerPositive10.setOnClickListener {
            playerModifyLife(Value.MAX.VALUE, Operator.PLUS.VALUE)
        }

        playerPositive5.setOnClickListener {
            playerModifyLife(Value.MIN.VALUE, Operator.PLUS.VALUE)
        }

        playerNegative10.setOnClickListener {
            playerModifyLife(Value.MAX.VALUE, Operator.MIN.VALUE)
        }

        playerNegative5.setOnClickListener {
            playerModifyLife(Value.MIN.VALUE, Operator.MIN.VALUE)
        }

        // Opponent
        opponentPositive10.setOnClickListener {
            opponentModifyLife(Value.MAX.VALUE, Operator.PLUS.VALUE)
        }

        opponentPositive5.setOnClickListener {
            opponentModifyLife(Value.MIN.VALUE, Operator.PLUS.VALUE)
        }

        opponentNegative10.setOnClickListener {
            opponentModifyLife(Value.MAX.VALUE, Operator.MIN.VALUE)
        }

        opponentNegative5.setOnClickListener {
            opponentModifyLife(Value.MIN.VALUE, Operator.MIN.VALUE)
        }
    }

    // FUNCTIONS
    private fun playerModifyLife(number: Int, operator: String){
        playerLife.text = playerLife.text.toString().countResult(operator, number)
        playerLife.setBackgroundResource(playerLife.text.toString().returnLifeColor())
    }

    private fun opponentModifyLife(number: Int, operator: String){
        opponentLife.text = opponentLife.text.toString().countResult(operator, number)
        opponentLife.setBackgroundResource(opponentLife.text.toString().returnLifeColor())
    }

    private fun showAlert(){
        val mDialogView = LayoutInflater.from(this).inflate(R.layout.alert_change_life, null)
        val builder = AlertDialog.Builder(this)
            .setView(mDialogView)
        val dialog =  builder.show()
        mDialogView.lifeEditText.text = Editable.Factory.getInstance().newEditable(Value.BASELIFE.VALUE.toString())

        mDialogView.buttonInit.setOnClickListener {
            playerLife.text = mDialogView.lifeEditText.text
            playerLife.setBackgroundResource(playerLife.text.toString().returnLifeColor())
            opponentLife.text = mDialogView.lifeEditText.text
            opponentLife.setBackgroundResource(opponentLife.text.toString().returnLifeColor())
            dialog.dismiss()
        }

        mDialogView.buttonCancel.setOnClickListener {
            dialog.dismiss()
        }
        dialog.show()
    }

    // TOOLBAR
    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.menu_restart, menu)
        return true
    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_restart -> {
                showAlert()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }
}
