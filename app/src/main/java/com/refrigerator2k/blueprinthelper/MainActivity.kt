package com.refrigerator2k.blueprinthelper

import android.content.SharedPreferences
import android.os.Bundle
import android.view.inputmethod.InputMethodManager
import androidx.appcompat.app.AppCompatActivity
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.*
import com.google.android.material.navigation.NavigationView
import com.refrigerator2k.blueprinthelper.fontcalculator.FontCalculatorFactory

class MainActivity : AppCompatActivity() {
    private lateinit var navController: NavController

    private lateinit var prefs: SharedPreferences
    private lateinit var keyFontSize: String

    private lateinit var imm: InputMethodManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        prefs = getSharedPreferences(getString(R.string.preference_file_key_default), MODE_PRIVATE)
        keyFontSize = getString(R.string.preference_font_size_str)

        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        navController = navHostFragment.navController

        NavigationUI.setupActionBarWithNavController(this, navController)

        imm = getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager

        if (savedInstanceState == null) {
            setupFont(prefs)
            LetterWidth.init()
            TypefaceManagerProvider.init(assets)
        }

        navController.addOnDestinationChangedListener {_, _, _ ->
            hideKeyboard()
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp() || super.onSupportNavigateUp()
    }

    private fun setupFont(prefs: SharedPreferences) {
        val fontSize = prefs.getFloat(keyFontSize, 10.0f).toDouble()
        val fontCalculator = FontCalculatorFactory.create(0)
        val fontProperties = fontCalculator.calculate(fontSize)
        FontProvider.font.properties = fontProperties
    }

    override fun onStop() {
        super.onStop()

        prefs.edit()
            .putFloat(keyFontSize, FontProvider.font.properties.size.toFloat())
            .apply()
    }

    fun hideKeyboard() {
        imm.hideSoftInputFromWindow(window.decorView.rootView.windowToken, 0)
    }
}