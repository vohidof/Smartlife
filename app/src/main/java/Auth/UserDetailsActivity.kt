package Auth

import Model.UserModel
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.SetOptions
import com.vohidov.smartlife.MainActivity
import com.vohidov.smartlife.R
import com.vohidov.smartlife.databinding.ActivityUserDetailsBinding

class UserDetailsActivity : AppCompatActivity() {

    lateinit var binding: ActivityUserDetailsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUserDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val firestoreDatabase = FirebaseFirestore.getInstance()

        val txt_email = intent.getStringExtra("email").toString()
        val txt_password = intent.getStringExtra("password").toString()

        binding.btnFinish.setOnClickListener {
            val txt_name = binding.edtName.text.toString().trim()
            val txt_surname = binding.edtSurname.text.toString().trim()
            val txt_phone = binding.edtPhone.text.toString().trim()

            if(txt_name != "" && txt_surname != "" && txt_phone != "" ){

                FirebaseAuth.getInstance().createUserWithEmailAndPassword(txt_email, txt_password).addOnCompleteListener{ task ->
                    if (task.isSuccessful){
                        Toast.makeText(this, "isSuccessful$txt_email", Toast.LENGTH_SHORT).show()
                        val firebaseUser : FirebaseUser = task.result!!.user!!
                        var uid = firebaseUser.uid
                        var model = UserModel(uid, txt_name, txt_surname, txt_phone, txt_email, txt_password, "")

                        //online database
                        firestoreDatabase.collection("Users").document(uid).set(model)
                        Toast.makeText(this@UserDetailsActivity, "Registered successfully!", Toast.LENGTH_SHORT).show()

                        val intent = Intent(this@UserDetailsActivity, MainActivity::class.java)
                        intent.putExtra("uid", uid)
                        startActivity(intent)
                        finish()
                    }
                    if (task.isCanceled){
                        Toast.makeText(this, "isCanceled$txt_email", Toast.LENGTH_SHORT).show()
                        Toast.makeText(this, "An error occurred!", Toast.LENGTH_SHORT).show()
                    }
                }
            } else {
                Toast.makeText(this, "Fill in all the information", Toast.LENGTH_SHORT).show()
            }
        }
    }
}