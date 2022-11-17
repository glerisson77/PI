# PI
webview code

public class MainActivity extends AppCompatActivity {

    Button btOpenUrl;
    EditText editText;
    WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btOpenUrl = findViewById(R.id.openurl);
        editText = findViewById(R.id.editTextTextPersonName);
        webView = findViewById(R.id.webview);

    }
    public void execute(View v){
        String url = "https://openai.com/dall-e-2/";
        webView.getSettings().setLoadsImagesAutomatically(true);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.setScrollBarStyle(btOpenUrl.SCROLLBARS_INSIDE_OVERLAY);
        webView.loadUrl(url);
    }
}
