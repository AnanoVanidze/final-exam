package com.example.android_final.ui.currency

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.android_final.databinding.CurrencyItemBinding

class CurrencyAdapter(private val currencies: List<Currency>) : RecyclerView.Adapter<CurrencyAdapter.ViewHolder>() {

    class ViewHolder(val binding: CurrencyItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(currency: Currency) {
            with(binding) {
                currencyNameTv.text = currency.currencyName
                currencyFullNameTv.text = currency.currencyFullName
                currencyValueTv.text = currency.currencyRate.toString()
            }
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        val binding =
            CurrencyItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(
            currencies[position]
        )
    }

    override fun getItemCount() = currencies.size

}
