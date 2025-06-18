package za.ac.iie.storeandrecievecontactnamesusingamap

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import android.widget.EditText
import android.widget.Button
import android.widget.TextView
import android.widget.Toast

class MainActivity : AppCompatActivity() {

    private val contactMap = HashMap<String,String>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        val nameInput = findViewById<EditText>(R.id.nameInput)
        val phoneInput = findViewById<EditText>(R.id.phoneInput)
        val saveButton = findViewById<Button>(R.id.saveButton)

        val searchInput = findViewById<EditText>(R.id.searchInput)
        val searchButton = findViewById<Button>(R.id.resultButton)
        val resultView = findViewById<TextView>(R.id.resultView)

        saveButton.setOnClickListener {
            val name = nameInput.text.toString().trim()
            val phone = phoneInput.text.toString().trim()

            if (name.isNotEmpty()&&phone.isNotEmpty()) {
                contactMap[name] = phone
                Toast.makeText(this, "Contact Saved", Toast.LENGTH_SHORT).show()
                nameInput.text.clear()
                phoneInput.text.clear()
            } else{
                Toast.makeText(this, "Please enter both name and number", Toast.LENGTH_SHORT).show()
            }
        }
        searchButton.setOnClickListener {
            val searchName = searchInput.text.toString().trim()
            val result = contactMap[searchName]

            if (result != null) {
                resultView.text = "Phone Number: $result"
            } else{
                resultView.text = "Contact not found"
            }
        }





        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}