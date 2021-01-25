package com.example.toast

import android.app.Activity
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Gravity
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import kotlin.system.exitProcess

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //定義所有元件
        val btnToast = findViewById<Button>(R.id.btntoast);
        val btncustom = findViewById<Button>(R.id.btncustom);
        val btndialog1 = findViewById<Button>(R.id.btndialog1);
        val btndialog2 = findViewById<Button>(R.id.btndialog2);
        val btndialog3 = findViewById<Button>(R.id.btndialog3);
        //按鈕監聽
        //預設Toast
        btnToast.setOnClickListener(){
            Toast.makeText(this,"這是預設Toast",Toast.LENGTH_SHORT).show();
        }
        //自定義Toast
        var cishu:Int = 0
        var zuobiao:Int = 50
        btncustom.setOnClickListener(){
            if (cishu == 5){
                Toast.makeText(this,"已達上限五個",Toast.LENGTH_SHORT).show();
                cishu = 0;
                zuobiao = 50
            }
            else{
                cishu += 1;
                toast(zuobiao);
                zuobiao += 100;
            }
        }
        //對話框
        btndialog1.setOnClickListener(){
            AlertDialog.Builder(this)
                .setTitle("離開程式嘛")
                .setMessage("內容")
                .setNeutralButton("離開（左按鈕）"){dialog, which ->
                    exitProcess(0)
                }
                .setNegativeButton("繼續（按鈕）"){dialog, which ->

                }
                .setPositiveButton("中間"){dialog, which ->
                    Toast.makeText(this,"這是中間按鈕",Toast.LENGTH_SHORT).show()
                }
                .show()
        }

    }
    fun toast(y:Int){
        //宣告Toast
        val toast = Toast(this);
        toast.setGravity(Gravity.TOP,0,y);
        toast.duration = Toast.LENGTH_LONG;
        //放入自定義的畫面（toast.xml）
        toast.view = layoutInflater.inflate(R.layout.toast,null)
        //顯示畫面
        toast.show()
    }
}