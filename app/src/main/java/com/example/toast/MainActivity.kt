package com.example.toast

import android.app.Activity
import android.content.DialogInterface
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
        //宣告陣列
        val item = arrayOf("選項1","選項2","選項3","選項4","選項5")
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
                .setPositiveButton("繼續（按鈕）"){dialog, which ->

                }
                .setNegativeButton("中間"){dialog, which ->
                    Toast.makeText(this,"這是中間按鈕",Toast.LENGTH_SHORT).show()
                }
                .show()
        }
        //列表式對話框
        btndialog2.setOnClickListener(){
            AlertDialog.Builder(this)
                    .setTitle("列表式對話框")
                    .setItems(item){dialog: DialogInterface?, i: Int ->
                        Toast.makeText(this,"你選的是"+item[i],Toast.LENGTH_SHORT).show()
                    }
                    .show()
        }
        //單選式對話框
        btndialog3.setOnClickListener {
            var num:Int = 0;
            AlertDialog.Builder(this)
                    .setTitle("單選式對話框")
                    //監聽選的項目
                    .setSingleChoiceItems(item,0){dialog: DialogInterface?, i: Int ->
                        num = i;
                    }
                    //用Toast顯示出選擇的項目
                    .setPositiveButton("確定"){dialog, which ->
                        Toast.makeText(this,"你選的是"+ item[num],Toast.LENGTH_SHORT).show()
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