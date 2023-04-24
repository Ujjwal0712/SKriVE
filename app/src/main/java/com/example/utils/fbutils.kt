package com.example.utils

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser

object fbutils {
    val firebaseAuth: FirebaseAuth = FirebaseAuth.getInstance()
    val firebaseUser: FirebaseUser? = firebaseAuth.currentUser
}