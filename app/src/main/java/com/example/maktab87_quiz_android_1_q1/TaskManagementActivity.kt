package com.example.maktab87_quiz_android_1_q1

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.maktab87_quiz_android_1_q1.databinding.ActivityMainBinding
import com.example.maktab87_quiz_android_1_q1.databinding.FragmentToDoTasksBinding
import com.google.android.material.bottomnavigation.BottomNavigationView

class TaskManagementActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var bottomNav: BottomNavigationView
    private lateinit var viewModel: TaskViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        replaceFragment(ToDoTasksFragment())
        binding.bottomNav.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.bottom_doing -> replaceFragment(DoingTasksFragment())
                R.id.bottom_done -> replaceFragment(DoneTasksFragment())
                R.id.bottom_todo -> replaceFragment(ToDoTasksFragment())
            }
            true
        }
        binding.btnNewTask.setOnClickListener {
            NewTaskSheet(null).show(supportFragmentManager, "newTaskTag")
        }
        setContentView(binding.root)
    }

    private fun replaceFragment(fragment: Fragment) {
        val fragmentManager: FragmentManager = supportFragmentManager
        val fragmentTransaction: FragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.main_layout, fragment)
        fragmentTransaction.commit()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater: MenuInflater = menuInflater
        inflater.inflate(R.menu.menu_sign_out, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.menuSignOut -> {
                signOut()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun signOut() {
        val manager = PreferencesManager(this)
        manager.setFirstTrue()
        startActivity(Intent(this, LoginSignupActivity::class.java))
        finish()
    }


    private fun setRecyclerView()
    {
        val mainActivity = this
        viewModel.tasksItems.observe(this){
            findViewById<RecyclerView>(R.id.todoListRecyclerView).apply {
                layoutManager = LinearLayoutManager(applicationContext)
             //   adapter = TaskItemAdapter(it, mainActivity)
            }
        }
    }

    fun editTaskItem(taskItem: TaskItem)
    {
        NewTaskSheet(taskItem).show(supportFragmentManager,"newTaskTag")
    }

    fun completeTaskItem(taskItem: TaskItem)
    {
        viewModel.setCompleted(taskItem)
    }
}