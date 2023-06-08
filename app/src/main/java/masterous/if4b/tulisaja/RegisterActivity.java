package masterous.if4b.tulisaja;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Toast;

import masterous.if4b.tulisaja.databinding.ActivityRegisterBinding;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterActivity extends AppCompatActivity {
    private ActivityRegisterBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityRegisterBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = binding.etUsername.getText().toString();
                String password = binding.etPassword.getText().toString();
                String konfirmasiPassword = binding.etKonfirmasiPassword.getText().toString();

                boolean bolehRegister = true;
                if (TextUtils.isEmpty(username)) {
                    bolehRegister = false;
                    binding.etUsername.setError("Username tidak boleh kosong!");
                }
                if (TextUtils.isEmpty(password)) {
                    bolehRegister = false;
                    binding.etPassword.setError("Password tidak boleh kosong!");
                }
                if (TextUtils.isEmpty(konfirmasiPassword)) {
                    bolehRegister = false;
                    binding.etKonfirmasiPassword.setError("Konfirmasi Password tidak boleh kosong!");
                }
                if (!password.equals(konfirmasiPassword)) {
                    bolehRegister = false;
                    binding.etPassword.setError("Konfirmasi password tidak sama dengan password!");
                }
                if (password.length() < 6) {
                    bolehRegister = false;
                    binding.etPassword.setError("Password minimal 6 karakter!");
                }

                if (bolehRegister) {
                    register(username, password);
                }
            }
        });

        binding.btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }

    private void register(String username, String password) {
        binding.progressBar.setVisibility(View.VISIBLE);
        APIService api = Utility.getRetrofit().create(APIService.class);
        Call<ValueData<User>> call = api.register(username, password);
        call.enqueue(new Callback<ValueData<User>>() {
            @Override
            public void onResponse(Call<ValueData<User>> call, Response<ValueData<User>> response) {
                binding.progressBar.setVisibility(View.GONE);
                if (response.code() == 200) {
                    int success = response.body().getSuccess();
                    String message = response.body().getMessage();

                    if (success == 1) {
                        Toast.makeText(RegisterActivity.this, message, Toast.LENGTH_SHORT).show();
                        Utility.setValue(RegisterActivity.this, "xUserId", response.body().getData().getId());
                        Intent intent = new Intent(RegisterActivity.this, MainActivity.class);
                        startActivity(intent);
                        finish();
                    } else {
                        Toast.makeText(RegisterActivity.this, message, Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(RegisterActivity.this, "Response " + response.code(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ValueData<User>> call, Throwable t) {
                binding.progressBar.setVisibility(View.GONE);
                System.out.println("Retrofit Error : " + t.getMessage());
                Toast.makeText(RegisterActivity.this, "Retrofit Error : " + t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}