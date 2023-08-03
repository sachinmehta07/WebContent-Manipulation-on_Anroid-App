package com.example.webviewpractice;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.webkit.JavascriptInterface;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private WebView webView;
    private Button submitButton;

    @SuppressLint("SetJavaScriptEnabled")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        webView = findViewById(R.id.webView);
        submitButton = findViewById(R.id.submitButton);

        // Load the HTML form
        webView.getSettings().setJavaScriptEnabled(true);
        webView.addJavascriptInterface(new WebAppInterface(), "AndroidInterface");
        webView.loadUrl("file:///android_asset/student_form.html");

        // Handle Submit button click
        submitButton.setOnClickListener(v -> {
            // Execute JavaScript code in the WebView to retrieve form data

            //"javascript:onSubmitButtonClicked()": This is the URL that the WebView will load.
            // However, it's not a standard URL but rather a JavaScript command.
            // The URL begins with the "javascript:" prefix, which tells the WebView
            // that the content should be interpreted as JavaScript code rather than a typical web URL.


            webView.loadUrl("javascript:onSubmitButtonClicked()");
        });

        // Ensure that links open in the WebView instead of external browsers

        //In summary,
        // setting a custom WebViewClient is not mandatory,
        // but it is a common practice to control the behavior of link handling in the WebView and ensure
        // that links open within the WebView itself rather than launching external web browsers.

        webView.setWebViewClient(new WebViewClient());
    }

    // JavaScript interface to communicate with Android code
    public class WebAppInterface {
        @JavascriptInterface
        public void showToast(String message) {
            // Display a Toast message with the form data
            Toast.makeText(MainActivity.this, message, Toast.LENGTH_SHORT).show();
        }
    }
}