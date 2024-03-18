package com.example.emonboadrding.rxJava.presentation

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.widget.doAfterTextChanged
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.emonboadrding.databinding.FragmentRxJavaBinding
import com.example.emonboadrding.rxJava.presentation.adapter.ReactiveAdapter
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import java.util.concurrent.TimeUnit
import kotlin.random.Random

class RxJavaFragment : Fragment() {

    private var _binding: FragmentRxJavaBinding? = null
    private val binding get() = _binding ?: throw NullPointerException("FragmentRxJavaBinding is null")

    private val viewModel by lazy { ViewModelProvider(this)[ReactiveViewModel::class.java] }

    private val onEditTextChangedObservable by lazy {
        Observable.create<String> { subscriber ->
            binding.editText.doAfterTextChanged { subscriber.onNext(it.toString()) }
        }.debounce(3, TimeUnit.SECONDS)
    }

    private val reactiveAdapter = ReactiveAdapter()

    @SuppressLint("CheckResult")
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentRxJavaBinding.inflate(inflater, container, false)
        return binding.root
    }

    @SuppressLint("CheckResult")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //1
        binding.button.setOnClickListener {
            viewModel.performSomeTask(Random.nextLong(1_000, 1_000_000))
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe {someFetchedString ->
                    binding.textView.text = someFetchedString
                }
        }


        //2
        with(binding) {
            recycler.adapter = reactiveAdapter
            reactiveAdapter.onItemClickPublish
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe {
                    Toast.makeText(requireContext(), it.name, Toast.LENGTH_SHORT).show()
                }
        }

        //3
        viewModel.getTimer()
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe {
                binding.textView2.text = it.toString()
            }

        //4
        onEditTextChangedObservable
            .subscribe { Log.d("Tag 3", it) }

        //5(a и б)
        viewModel.getDiscountCards(isAllDiscountCardRequired = false)
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe { list ->
                reactiveAdapter.submitList(list)
            }
    }
}
