package com.example.views

import android.content.ContentValues.TAG
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.example.extensions.extensions.toast
import com.example.skrive.databinding.ActivityCreateaccountBinding
import com.example.utils.fbutils.firebaseAuth
import com.example.utils.fbutils.firebaseUser
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase


class Createaccount : AppCompatActivity() {
    private lateinit var binding: ActivityCreateaccountBinding
    private lateinit var userEmail: String
    private lateinit var userPassword: String
    private lateinit var createAccountInputsArray: Array<EditText>

    private var db = Firebase.firestore
    private lateinit var userId: String





    override fun onCreate(savedInstanceState: Bundle?) {
        installSplashScreen()
        super.onCreate(savedInstanceState)
        binding= ActivityCreateaccountBinding.inflate(layoutInflater)



        setContentView(binding.root)

        binding.loginadmin.setOnClickListener {
            startActivity(Intent(this, AdminLogin::class.java))
        }


        createAccountInputsArray = arrayOf(binding.email, binding.password, binding.confirmpassword)
        binding.register.setOnClickListener {
            signIn()

        }

        binding.loginstudent.setOnClickListener {
            startActivity(Intent(this, Login::class.java))
            toast("please sign into your account")
            finish()
        }
    }


    override fun onStart() {
        super.onStart()
        val user: String? = firebaseAuth.currentUser?.uid
        if(user!="bWRMnzeWTdVePrbKROq7utENhbi2"){
            user?.let {
                startActivity(Intent(this, HomeActivity::class.java))
                toast("welcome back")
            }
        }
        else{
            user.let {
                startActivity(Intent(this, AdminHome::class.java))
                toast("welcome back")
            }

        }

    }

    private fun notEmpty(): Boolean = binding.email.text.toString().trim().isNotEmpty() &&
            binding.password.text.toString().trim().isNotEmpty() &&
            binding.confirmpassword.text.toString().trim().isNotEmpty()

    private fun identicalPassword(): Boolean {
        var identical = false
        if (notEmpty() &&
            binding.password.text.toString().trim() == binding.confirmpassword.text.toString().trim()
        ) {
            identical = true
        } else if (!notEmpty()) {
            createAccountInputsArray.forEach { input ->
                if (input.text.toString().trim().isEmpty()) {
                    input.error = "${input.hint} is required"
                }
            }
        } else {
            toast("passwords are not matching !")
        }
        return identical
    }

    private fun signIn() {
        if (identicalPassword()) {

            userEmail = binding.email.text.toString().trim()
            userPassword = binding.password.text.toString().trim()


            firebaseAuth.createUserWithEmailAndPassword(userEmail, userPassword)
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        toast("created account successfully !")
                        sendEmailVerification()
                        userId = FirebaseAuth.getInstance().currentUser?.uid.toString()
                        setdata()
                        startActivity(Intent(this, HomeActivity::class.java))
                        finish()
                    } else {
                        toast("failed to Authenticate !")
                    }
                }
        }
    }

    private fun sendEmailVerification() {

        firebaseUser?.let {

            it.sendEmailVerification().addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    toast("email sent to $userEmail")
                }
            }

        }
    }

    private val vinay = hashMapOf(
        "Name" to "Vinay Kumar ",
        "Roll No" to "22261",
        "Semester" to "2",
        "Branch"    to "ECE",
        "contact"   to "9982382363",
        "econtact"  to "9982382363",
        "address"   to "Bhadra, Hanumangarh, Rajasthan",
        "email" to "22261@iiitu.ac.in"
    )
    private val deep = hashMapOf(
        "Name" to "Deep",
        "Roll No" to "22124",
        "Semester" to "2",
        "Branch"    to "CSE",
        "contact"   to "8168920736",
        "econtact"  to "8168920736",
        "address"   to "Dev vatika, Hisar ",
        "email" to "22124@iiitu.ac.in"
    )
    private val malyaj = hashMapOf(
        "Name" to "Malayaj",
        "Roll No" to "22138",
        "Semester" to "2",
        "Branch"    to "CSE",
        "contact"   to "9829689255",
        "econtact"  to "9829689255",
        "address"   to "Sikar, Rajasthan ",
        "email" to "22138@iiitu.ac.in"
    )
    private val shrey = hashMapOf(
        "Name" to "SHREY",
        "Roll No" to "22252",
        "Semester" to "2",
        "Branch"    to "ECE",
        "contact"   to "9521732881",
        "econtact"  to "9521732881",
        "address"   to "Bhiwadi, Rajasthan",
        "email" to "22252@iiitu.ac.in"
    )
    private val aditya = hashMapOf(
        "Name" to "Aditya Bagherwal ",
        "Roll No" to "22103",
        "Semester" to "2",
        "Branch"    to "CSE",
        "contact"   to "6377268277",
        "econtact"  to "6377268277",
        "address"   to "Damami mohalla, Shahpura 311404",
        "email" to "22103@iiitu.ac.in "
    )
    private val aryan = hashMapOf(
        "Name" to "Aryan Arya",
        "Roll No" to "22312",
        "Semester" to "2",
        "Branch"    to "IT",
        "contact"   to "7719436134",
        "econtact"  to "7719436134",
        "address"   to "122 New Vikaspuri , Jalandhar",
        "email" to "22312@iiitu.ac.in"
    )
    private val sangharsh = hashMapOf(
        "Name" to "Sangharsh Verma",
        "Roll No" to "22247",
        "Semester" to "2",
        "Branch"    to "ECE",
        "contact"   to "9058603477",
        "econtact"  to "9058603477",
        "address"   to "Shivlok colony haridwar\n" +
                "L28 V phase 3",
        "email" to "22247@iiitu.ac.in"
    )
    private val deepesh = hashMapOf(
        "Name" to "Deepesh Rathore ",
        "Roll No" to "22317",
        "Semester" to "2",
        "Branch"    to "IT",
        "contact"   to "9406839235",
        "econtact"  to "7724931408",
        "address"   to "M no.128,ward no 04, khardon Kalan, district shajapur,mp",
        "email" to "22317@iiitu.ac.in"
    )
    private val akshat = hashMapOf(
        "Name" to "Akshat Gupta ",
        "Roll No" to "22204",
        "Semester" to "2",
        "Branch"    to "ECE",
        "contact"   to "9252908070",
        "econtact"  to "9252908070",
        "address"   to "Jaipur, Rajasthan ",
        "email" to "22204@iiitu.ac.in"
    )
    private val ansh = hashMapOf(
        "Name" to "Ansh Gudibanda",
        "Roll No" to "22207",
        "Semester" to "2",
        "Branch"    to "ECE",
        "contact"   to "7993406901",
        "econtact"  to "7768986901",
        "address"   to "Kowkoor, Yapral, Hyderabad ",
        "email" to "22207@iiitu.ac.in"
    )
    private val ayush = hashMapOf(
        "Name" to "Ayush Kumar Vyas ",
        "Roll No" to "22212",
        "Semester" to "2",
        "Branch"    to "ECE",
        "contact"   to "9024324581",
        "econtact"  to "9460580194",
        "address"   to "Sadar bazar agucha,shahpura , Rajasthan ",
        "email" to "22212@iiitu.ac.in"
    )
    private val vansh = hashMapOf(
        "Name" to "Vansh Dawra",
        "Roll No" to "22360",
        "Semester" to "2",
        "Branch"    to "IT",
        "contact"   to "6378661815",
        "econtact"  to "9983232050",
        "address"   to "Ward no.9, Kesrsinghpur, Ganganagar, Rajasthan",
        "email" to "22360@iiitu.ac.in"
    )

    private fun setdata(){
        val ref =  db.collection("user").document(userId)
        val uem=binding.email.text.toString()


        if(uem=="22261@iiitu.ac.in"){
            ref.set(vinay)
                .addOnSuccessListener { Log.d(TAG, "DocumentSnapshot successfully written!") }
                .addOnFailureListener { e -> Log.w(TAG, "Error writing document", e) }

        }
        if(uem=="22124@iiitu.ac.in"){
            ref.set(deep)
                .addOnSuccessListener { Log.d(TAG, "DocumentSnapshot successfully written!") }
                .addOnFailureListener { e -> Log.w(TAG, "Error writing document", e) }

        }
        if(uem=="22138@iiitu.ac.in"){
            ref.set(malyaj)
                .addOnSuccessListener { Log.d(TAG, "DocumentSnapshot successfully written!") }
                .addOnFailureListener { e -> Log.w(TAG, "Error writing document", e) }

        }
        if(uem=="22252@iiitu.ac.in"){
            ref.set(shrey)
                .addOnSuccessListener { Log.d(TAG, "DocumentSnapshot successfully written!") }
                .addOnFailureListener { e -> Log.w(TAG, "Error writing document", e) }

        }
        if(uem=="22103@iiitu.ac.in "){
            ref.set(aditya)
                .addOnSuccessListener { Log.d(TAG, "DocumentSnapshot successfully written!") }
                .addOnFailureListener { e -> Log.w(TAG, "Error writing document", e) }

        }
        if(uem=="22312@iiitu.ac.in"){
            ref.set(aryan)
                .addOnSuccessListener { Log.d(TAG, "DocumentSnapshot successfully written!") }
                .addOnFailureListener { e -> Log.w(TAG, "Error writing document", e) }

        }
        if(uem=="22247@iiitu.ac.in"){
            ref.set(sangharsh)
                .addOnSuccessListener { Log.d(TAG, "DocumentSnapshot successfully written!") }
                .addOnFailureListener { e -> Log.w(TAG, "Error writing document", e) }

        }
        if(uem=="22317@iiitu.ac.in"){
            ref.set(deepesh)
                .addOnSuccessListener { Log.d(TAG, "DocumentSnapshot successfully written!") }
                .addOnFailureListener { e -> Log.w(TAG, "Error writing document", e) }

        }
        if(uem=="22204@iiitu.ac.in"){
            ref.set(akshat)
                .addOnSuccessListener { Log.d(TAG, "DocumentSnapshot successfully written!") }
                .addOnFailureListener { e -> Log.w(TAG, "Error writing document", e) }

        }
        if(uem=="22207@iiitu.ac.in"){
            ref.set(ansh)
                .addOnSuccessListener { Log.d(TAG, "DocumentSnapshot successfully written!") }
                .addOnFailureListener { e -> Log.w(TAG, "Error writing document", e) }

        }
        if(uem=="22212@iiitu.ac.in"){
            ref.set(ayush)
                .addOnSuccessListener { Log.d(TAG, "DocumentSnapshot successfully written!") }
                .addOnFailureListener { e -> Log.w(TAG, "Error writing document", e) }

        }
        if(uem=="22360@iiitu.ac.in"){
            ref.set(vansh)
                .addOnSuccessListener { Log.d(TAG, "DocumentSnapshot successfully written!") }
                .addOnFailureListener { e -> Log.w(TAG, "Error writing document", e) }

        }

    }

}




