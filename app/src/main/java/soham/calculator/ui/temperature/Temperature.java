package soham.calculator.ui.temperature;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import soham.calculator.databinding.FragmentTemperatureBinding;
import soham.calculator.utils.TemperatureUtil;

public class Temperature extends Fragment {

    private FragmentTemperatureBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentTemperatureBinding.inflate(inflater, container, false);
        initListeners();
        return binding.getRoot();
    }

    private void initListeners() {
        this.binding.calculateBtn.setOnClickListener(l->{
            String f = this.binding.fahrenheitInput.getText().toString(),
                    c = this.binding.celsiusInput.getText().toString(),
                    k = this.binding.kelvinInput.getText().toString();
            if(f.length()>0) {
                double temp = Double.parseDouble(f);
                updateCelsius(TemperatureUtil.C2F(temp,true));
                updateKelvin(TemperatureUtil.K2F(temp,true));
            } else if(k.length()>0){
                double temp = Double.parseDouble(k);
                updateCelsius(TemperatureUtil.C2K(temp,true));
                updateFahrenheit(TemperatureUtil.K2F(temp,false));
            } else if(c.length()>0){
                double temp = Double.parseDouble(c);
                updateFahrenheit(TemperatureUtil.C2F(temp,false));
                updateKelvin(TemperatureUtil.C2K(temp,false));
            } else Toast.makeText(this.getContext(),"Enter value in any one.",Toast.LENGTH_SHORT).show();
        });
        this.binding.clearBtn.setOnClickListener(l->{
            this.binding.fahrenheitInput.setText("");
            this.binding.celsiusInput.setText("");
            this.binding.kelvinInput.setText("");
        });
    }

    private void updateFahrenheit(double temp){
        if(this.binding.fahrenheitInput.getText().toString().equals(temp+"") ) return;
        Log.d("test","Updating Fahrenheit");
        this.binding.fahrenheitInput.setText(temp+"");
    }
    private void updateCelsius(double temp){
        if(this.binding.celsiusInput.getText().toString().equals(temp+"") ) return;
        Log.d("test","Updating Celsius");
        this.binding.celsiusInput.setText(temp+"");
    }
    private void updateKelvin(double temp){
        if(this.binding.kelvinInput.getText().toString().equals(temp+"") ) return;
        Log.d("test","Updating Kelvin");
        this.binding.kelvinInput.setText(temp+"");
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }


}