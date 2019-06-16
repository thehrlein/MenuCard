package com.tobiapplications.menu.di.module

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.firestore.FirebaseFirestore
import dagger.Module
import dagger.Provides
import javax.inject.Singleton


@Module
class FirebaseModule {

    @Provides
    @Singleton
    fun provideFirebaseAuth() : FirebaseAuth {
        return FirebaseAuth.getInstance()
    }

    @Provides
    @Singleton
    fun provideFirebaseDatabase() : FirebaseDatabase {
        return FirebaseDatabase.getInstance()
    }

    @Provides
    @Singleton
    fun provideFirebaseDatabaseReference(firebaseDatabase: FirebaseDatabase) : DatabaseReference {
        return firebaseDatabase.reference
    }

    @Provides
    @Singleton
    fun provideFirebaseFireStore() : FirebaseFirestore {
        return FirebaseFirestore.getInstance()
    }

}