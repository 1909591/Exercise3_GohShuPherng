package com.example.exercise3_gohshupherng


import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.Button
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import org.w3c.dom.Text
import java.util.*

class MainActivity : AppCompatActivity(), AdapterView.OnItemSelectedListener {

    override fun onNothingSelected(parent: AdapterView<*>?) {
    }

    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //Handling item selected listener for the spinner
        spinnerAge.onItemSelectedListener = this

        buttonCalculate.setOnClickListener{
            calculatePremium()
        }

        buttonReset.setOnClickListener{
            reset()
        }

    }

    private fun calculatePremium(){
        //get the age group
        val age: Int =spinnerAge.selectedItemPosition

        var premium = when(age){
            0 -> 60
            1 -> 70
            2 -> 90
            3 -> 120
            4 -> 150
            else ->150

        }
        var extraMale=0
        var extraSmoker=0
        val gender=radioGroupGender.checkedRadioButtonId

        if(gender == R.id.radioButtonMale){
            //calculate premium for male
            extraMale = when(age){
                0 -> 0//less than 17
                1 -> 50//from 17 to 25
                2 -> 100//from 26 to 30
                3 -> 150//from 31 to 40
                else ->200//more than 41
            }

        }else{
            //calculate premium for female

        }

        //Determine smoker or non-smoker
        val smoker: Boolean = checkBoxSmoker.isChecked
        if(smoker){
            //calculate the premium for smoker
            extraSmoker = when(age){
                0 -> 0//less than 17
                1 -> 100//from 17 to 25
                2 -> 150//from 26 to 30
                3 -> 200//from 31 to 40
                4 -> 250//from 41 to 55
                else -> 300//more than 55
            }
            //val symbol=Currency.getInstance(Locale.getDefault()).symbol
            textViewPremium.text = String.format("%s %d","RM",premium)

        }
        if(gender == R.id.radioButtonMale) {
            textViewPremium.text = String.format(
                "Premium =%s %d \nExtra payment for male=%s %d \n Extra Smoker payment=%s %d\nTotal Payment=%s %d",
                "RM",premium,
                "RM",extraMale,
                "RM",extraSmoker,
                "RM",(premium+extraMale+extraSmoker)
            )
        }
        else{
            textViewPremium.text = String.format(
                "Premium =%s %d \n Extra Smoker payment=%s %d\n" + "Total Payment=%s %d",
                "RM",premium,
                "RM",extraSmoker,
                "RM",(premium +extraSmoker)
            )
        }

    }

    fun reset(){
        spinnerAge.setSelection(0)
        if(checkBoxSmoker.isChecked){
            checkBoxSmoker.isChecked = false
        }
        if(radioButtonMale.isChecked || radioButtonMale.isChecked ) {
            radioButtonMale.isChecked = false
            radioButtonFemale.isChecked = false
        }
        textViewPremium.setText("")
    }
}
