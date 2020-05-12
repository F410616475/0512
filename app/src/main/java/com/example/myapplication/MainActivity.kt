package com.example.myapplication

import android.database.sqlite.SQLiteDatabase
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import java.lang.Exception

class MainActivity : AppCompatActivity() {

    var db : SQLiteDatabase?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
try {
    db = SQLiteDatabase.openDatabase("/data/data/com.example.myapplication/mydb2", null, SQLiteDatabase.CREATE_IF_NECESSARY)
    db?.execSQL("create table tbIAMIGO(" + " recID integer PRIMARY KEY autoincrement, " + " name text, "+ " phone text ); " );
    db?.execSQL("insert into tbIAMIGO(name,phone)values('AAA','555');")
    db?.execSQL("insert into tbIAMIGO(name,phone)values('BBB','777');")
    db?.execSQL("insert into tbIAMIGO(name,phone)values('CCC','999');")
    var mycur =  db?.rawQuery("select*from tbIAMIGO",null)

    var c1 = mycur?.getColumnIndex("recID")
    var c2 = mycur?.getColumnIndex("name")
    var c3 = mycur?.getColumnIndex("phone")

    var msg:String=""

    while ( mycur!!.moveToNext()){

        var t1 = Integer.toString(mycur?.getInt(c1!!))
        var t2 =mycur?.getString(c2!!)
        var t3 =mycur?.getString(c3!!)

        msg+=t1+t2+t3+"\n"

        Toast.makeText(this,t1+t2+t3,Toast.LENGTH_SHORT).show()
    }
    textView.setText(msg)

    db?.close()
}catch (err :Exception){
Toast.makeText(this,err.toString(),Toast.LENGTH_LONG).show()

}
//SQLiteDatabase
    }
}
