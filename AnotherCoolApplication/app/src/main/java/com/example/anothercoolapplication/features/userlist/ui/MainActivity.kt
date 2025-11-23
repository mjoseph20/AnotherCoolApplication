package com.example.anothercoolapplication.features.userlist.ui

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.anothercoolapplication.R
import com.example.anothercoolapplication.core.data.db.UserEntity
import com.example.anothercoolapplication.databinding.ActivityMainBinding
import com.example.anothercoolapplication.features.userlist.ui.MainActivityViewModel
import com.example.anothercoolapplication.features.userlist.ui.RecyclerViewAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity(), RecyclerViewAdapter.RowClickListener {
    private lateinit var binding: ActivityMainBinding
    private val viewModel: MainActivityViewModel by viewModels()
    lateinit var recyclerViewAdapter: RecyclerViewAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.recyclerView.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            recyclerViewAdapter = RecyclerViewAdapter(this@MainActivity)
            adapter = recyclerViewAdapter
            val divider = DividerItemDecoration(applicationContext, DividerItemDecoration.VERTICAL)
            addItemDecoration(divider)
        }

        viewModel.getAllUsersObservers().observe(this, Observer {
            recyclerViewAdapter.setListData(ArrayList(it))
            recyclerViewAdapter.notifyDataSetChanged()
        })

        binding.saveButton.setOnClickListener {
            val name = binding.nameInput.text.toString()
            val email = binding.emailInput.text.toString()
            val phone = binding.phoneInput.text.toString()
            if (binding.saveButton.text.toString() == getString(R.string.save)) {
                val user = UserEntity(0, name, email, phone)
                viewModel.insertUserInfo(user)
            } else {
                val user = UserEntity(
                    binding.nameInput.getTag(binding.nameInput.id).toString().toInt(),
                    name,
                    email,
                    phone
                )
                viewModel.updateUserInfo(user)
                binding.saveButton.text = getString(R.string.save)
            }
            binding.nameInput.setText("")
            binding.emailInput.setText("")
            binding.phoneInput.setText("")
        }
    }

    override fun onDeleteUserClickListener(user: UserEntity) {
        viewModel.deleteUserInfo(user)
    }

    override fun onItemClickListener(user: UserEntity) {
        binding.nameInput.setText(user.name)
        binding.emailInput.setText(user.email)
        binding.phoneInput.setText(user.phone)
        binding.nameInput.setTag(binding.nameInput.id, user.id)
        binding.saveButton.text = getString(R.string.update)
    }
}