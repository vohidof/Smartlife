package Auth

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.vohidov.smartlife.R
import com.vohidov.smartlife.databinding.ActivitySignUpBinding

class SignUpActivity : AppCompatActivity() {

    lateinit var binding: ActivitySignUpBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignUpBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.txtSignIn.setOnClickListener {
            val intent = Intent(this, SignInActivity::class.java)
            startActivity(intent)
        }

        binding.btnSignUp.setOnClickListener {
            var txt_email = binding.edtEmailSignUp.text.toString().trim()
            var txt_password = binding.edtPassword.text.toString().trim()
            var txt_password_again = binding.edtPasswordRepeat.text.toString().trim()

            if (txt_email != "" && android.util.Patterns.EMAIL_ADDRESS.matcher(txt_email).matches()){
                if (txt_password != "" && txt_password.length >= 8 && txt_password_again != "" && txt_password_again == txt_password){

                    val intent = Intent(this, UserDetailsActivity::class.java)
                    intent.putExtra("email", txt_email)
                    intent.putExtra("password", txt_password)
                    startActivity(intent)

                } else{
                    Toast.makeText(this, "Invalid password!", Toast.LENGTH_SHORT).show()
                    txt_password = ""
                    binding.edtPassword.setText(txt_password)
                    txt_password_again = ""
                    binding.edtPasswordRepeat.setText(txt_password_again)
                }
            } else {
                Toast.makeText(this, "Invalid email!", Toast.LENGTH_SHORT).show()
                txt_email = ""
                binding.edtEmailSignUp.setText(txt_email)
            }
        }
    }
}