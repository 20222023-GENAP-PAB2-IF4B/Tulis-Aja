package masterous.if4b.tulisaja;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import masterous.if4b.tulisaja.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {
    private ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
    }
}