package com.example.enaktubes

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.etebarian.meowbottomnavigation.MeowBottomNavigation
import com.example.enaktubes.Fragments.*
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_profile.*

class MainActivity : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)




        addFragment(HomeFragment.newInstance())
        bottomNavigation.show(0)
        bottomNavigation.add(MeowBottomNavigation.Model(0,R.drawable.ic_outline_home_24))
        bottomNavigation.add(MeowBottomNavigation.Model(1,R.drawable.ic_outline_search_24))
        bottomNavigation.add(MeowBottomNavigation.Model(2,R.drawable.ic_outline_add_24))
        bottomNavigation.add(MeowBottomNavigation.Model(3,R.drawable.ic_outline_store_24))
        bottomNavigation.add(MeowBottomNavigation.Model(4,R.drawable.ic_outline_person_24))

        bottomNavigation.setOnClickMenuListener {
            when(it.id){
                0 -> {
                    replaceFragment(HomeFragment.newInstance())
                }
                1 -> {
                    replaceFragment(SearchFragment.newInstance())
                }
                2 -> {
                    replaceFragment(AddFragment.newInstance())
                }
                3 -> {
                    replaceFragment(StoreFragment.newInstance())
                }
                4 -> {
                    replaceFragment(ProfileFragment.newInstance())
                }
                else -> {
                    replaceFragment(HomeFragment.newInstance())
                }
            }
        }
    }

    private fun replaceFragment(fragment:Fragment){
        val fragmentTransition = supportFragmentManager.beginTransaction()
        fragmentTransition.replace(R.id.fragmentContainer,fragment).addToBackStack(Fragment::class.java.simpleName).commit()
    }

    private fun addFragment(fragment:Fragment){
        val fragmentTransition = supportFragmentManager.beginTransaction()
        fragmentTransition.add(R.id.fragmentContainer,fragment).addToBackStack(Fragment::class.java.simpleName).commit()
    }



}