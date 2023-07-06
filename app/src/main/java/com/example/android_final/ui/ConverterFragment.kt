package com.example.android_final.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Toast
import com.example.android_final.R
import com.example.android_final.databinding.FragmentConverterBinding
import com.example.android_final.ui.currency.Currency
import com.example.android_final.ui.currency.CurrencyList

class ConverterFragment : Fragment() {

    private var _binding: FragmentConverterBinding? = null
    private val binding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentConverterBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val toCurrencyList = ArrayAdapter(requireContext(), R.layout.list_currencies, resources.getStringArray(R.array.currency_codes))
        val fromCurrencyList = ArrayAdapter(requireContext(), R.layout.list_currencies, arrayListOf("GEL"))

        binding.fromCurrenciesDropDown1.apply {
            setText("GEL")
            setAdapter(fromCurrencyList)
        }
        binding.toCurrenciesDropDown2.apply {
            setText("USD")
            setAdapter(toCurrencyList)
        }
        binding.convertIv.setOnClickListener{
            var rate = 1.0
            var result = 0.00
            for (currency in CurrencyList.currencies){
                if (currency.currencyName == binding.toCurrenciesDropDown2.text.toString()){
                    rate = currency.currencyRate
                }
            }
            if (binding.amountToConvertEt.text.toString() != "0.00"){
                result = binding.amountToConvertEt.text.toString().toInt() * rate
            }
            binding.convertedMoneyEt.setText(result.toString())


        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}