package com.mooyz.listviewdemo

import android.app.Activity
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.annotation.IdRes
import android.view.View
import android.widget.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val listView : ListView = bind(R.id.mainListView)
        val myFamily = ArrayList<String>()
        myFamily.add("Rob")
        myFamily.add("Simon")
        myFamily.add("June")
        myFamily.add("Alexia")
        val arrayAdapter = ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, myFamily)
        listView.adapter = arrayAdapter as ListAdapter?

        listView.onItemClickListener = object: AdapterView.OnItemClickListener {
            override fun onItemClick(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                Toast.makeText(applicationContext, "Hello " + myFamily.get(p2), Toast.LENGTH_SHORT).show()
            }
        }
    }
}

fun <T : View> Activity.bind(@IdRes res : Int) : T {
    @Suppress("UNCHECKED_CAST")
    return findViewById(res) as T
}
