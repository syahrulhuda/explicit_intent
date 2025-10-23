package com.example.explicit_intent

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.explicit_intent.ui.theme.ExplicitintentTheme

class SecondActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val message = intent.getStringExtra(MainActivity.EXTRA_MESSAGE) ?: "No message found"

        setContent {
            ExplicitintentTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    SecondScreen(
                        message = message,
                        onReplyClicked = { replyText ->
                            val replyIntent = Intent()
                            replyIntent.putExtra(EXTRA_REPLY, replyText)
                            setResult(Activity.RESULT_OK, replyIntent)
                            finish()
                        }
                    )
                }
            }
        }
    }

    companion object {
        const val EXTRA_REPLY = "com.example.explicit_intent.EXTRA_REPLY"
    }
}

@ExperimentalMaterial3Api
@Composable
fun SecondScreen(message: String, onReplyClicked: (String) -> Unit) {
    var replyText by remember { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Received message:")
        Text(text = message, style = MaterialTheme.typography.headlineSmall)
        Spacer(modifier = Modifier.height(24.dp))
        OutlinedTextField(
            value = replyText,
            onValueChange = { replyText = it },
            label = { Text("Type your reply") }
        )
        Spacer(modifier = Modifier.height(16.dp))
        Button(onClick = { onReplyClicked(replyText) }) {
            Text("Balas")
        }
    }
}