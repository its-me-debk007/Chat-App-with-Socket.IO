package task.internship.app.presentation

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.json.JSONObject
import task.internship.app.R
import task.internship.app.common.TAG
import task.internship.app.presentation.viewmodel.MyViewModel


@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var viewModel: MyViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewModel = ViewModelProvider(this)[MyViewModel::class.java]

        viewModel.addUser("Bob")

        viewModel.listenOnLogin {
            lifecycleScope.launch(Dispatchers.Main) {
                Log.d(TAG, "Logged in")
            }
        }

        viewModel.sendMessage("Hello World!")

        viewModel.listenNewMessage { args ->
            lifecycleScope.launch(Dispatchers.Main) {
                val data: JSONObject = args[0] as JSONObject

                val username: String = data.getString("username")
                val message: String = data.getString("message")

//                Log.d(TAG, "$username:- $message")
                Log.d(TAG, args[0].toString())
            }
        }
    }
}
