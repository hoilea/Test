package com.example.test


import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.os.Bundle
import android.provider.MediaStore
import android.text.Editable
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import kotlin.math.log


class MainActivity : AppCompatActivity() {

    val Gallery1 = 0
    val Gallery2 = 1
    val Gallery3 = 2
    val Gallery4 = 3

    var items = arrayOf("카테고리", "의류", "신발", "가전", "스포츠", "뷰티", "가방/잡화", "식품")
    var itemsbgs = arrayOf("고정기간","1개월", "3개월", "9개월", "12개월")


    //image
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //set listener on button
        imagepreView1.setOnClickListener{ loadImage() }
        imagepreView2.setOnClickListener{ loadImage1() }
        imagepreView3.setOnClickListener{ loadImage2() }
        imagepreView4.setOnClickListener{ loadImage3() }

        //spinner
        val myAdapter = ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, items)
        val myAdapterbgs = ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item, itemsbgs)
        spinner_spn.adapter = myAdapter
        spinner_spn.onItemSelectedListener = object:AdapterView.OnItemSelectedListener{
            override fun onNothingSelected(p0: AdapterView<*>?) {

            }

            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                println(p2)
                items[p2]
            }
        }
        spn_bgs.adapter = myAdapterbgs
        spn_bgs.onItemSelectedListener = object:AdapterView.OnItemSelectedListener{
            override fun onNothingSelected(p0: AdapterView<*>?) {

            }

            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                println(p2)
                items[p2]
            }
        }
        //EndOfSpinner


        //가격 입력 할인가, 할인율

        checkDiscount.setOnCheckedChangeListener{ compoundButton, b ->
            if(b == true) {
                //할인가
                if(et_prdSPrice.text.toString().length != 0 && et_prdSPricePercent.text.toString().length == 0) {
                    var prdPrice = et_prdPrice.text.toString()
                    var prdSPrice = et_prdSPrice.text.toString()

                    var price = prdPrice.toInt() - prdSPrice.toInt()

                    fun String.toEditable(): Editable = Editable.Factory.getInstance().newEditable(this)
                    var msg_price = "${price.toString().toEditable()} 원  (- ${prdSPrice}원 할인) "

                    et_price.text = msg_price.toEditable()
                    Log.d("asdasda","2222222")
                }
                //할인율
                else if(et_prdSPricePercent.text.toString().length != 0 && et_prdSPrice.text.toString().length == 0) {
                    var prdPrice = et_prdPrice.text.toString()
                    var prdSPrice = et_prdSPricePercent.text.toString()

                    var price : Double = prdPrice.toDouble()/100 * (prdSPrice.toDouble())
                    var priceEnd : Double = prdPrice.toDouble() - price

                    fun String.toEditable(): Editable = Editable.Factory.getInstance().newEditable(this)
                    var msg_price = "${priceEnd.toString().toEditable()} 원  (${price.toInt()}원, ${prdSPrice}% 할인) "

                    et_price.text = msg_price.toEditable()
                    Log.d("asdasda","44444")
                }
                else if(et_prdSPrice.text.toString().length != 0 && et_prdSPricePercent.text.toString().length != 0){
                    Toast.makeText(this, "할인가, 할인율 중 하나만 선택 가능합니다.", Toast.LENGTH_SHORT).show()
                }

            }
        }

        //가격 입력 끝
        





    }//endOfonCreate



    private fun loadImage(){
        val intent = Intent()
        intent.type = "image/*"
        intent.action = Intent.ACTION_GET_CONTENT

        startActivityForResult(Intent.createChooser(intent, "Load Picture"), Gallery1)
    }
    private fun loadImage1(){
        val intent = Intent()
        intent.type = "image/*"
        intent.action = Intent.ACTION_GET_CONTENT

        startActivityForResult(Intent.createChooser(intent, "Load Picture"), Gallery2)
    }
    private fun loadImage2(){
        val intent = Intent()
        intent.type = "image/*"
        intent.action = Intent.ACTION_GET_CONTENT

        startActivityForResult(Intent.createChooser(intent, "Load Picture"), Gallery3)
    }
    private fun loadImage3(){
        val intent = Intent()
        intent.type = "image/*"
        intent.action = Intent.ACTION_GET_CONTENT

        startActivityForResult(Intent.createChooser(intent, "Load Picture"), Gallery4)
    }





    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == Gallery1) {
            if (resultCode == Activity.RESULT_OK) {
                var dataUri = data?.data
                try {
                    var bitmap: Bitmap =
                        MediaStore.Images.Media.getBitmap(this.contentResolver, dataUri)
                    imagepreView1.setImageBitmap(bitmap)
                } catch (e: Exception) {
                    Toast.makeText(this, "$e", Toast.LENGTH_SHORT).show()
                }
            } else {
                Log.d("ssssssssssssskmkmkkerro", "error1")
            }

        }
        if (requestCode == Gallery2) {
            if (resultCode == Activity.RESULT_OK) {
                var dataUri = data?.data
                try {
                    var bitmap: Bitmap =
                        MediaStore.Images.Media.getBitmap(this.contentResolver, dataUri)
                    imagepreView2.setImageBitmap(bitmap)
                } catch (e: Exception) {
                    Toast.makeText(this, "$e", Toast.LENGTH_SHORT).show()
                }
            } else {
                Log.d("ssssssssssssskmkmkkerro", "error2")
            }

        }
        if (requestCode == Gallery3) {
            if (resultCode == Activity.RESULT_OK) {
                var dataUri = data?.data
                try {
                    var bitmap: Bitmap =
                        MediaStore.Images.Media.getBitmap(this.contentResolver, dataUri)
                    imagepreView3.setImageBitmap(bitmap)
                } catch (e: Exception) {
                    Toast.makeText(this, "$e", Toast.LENGTH_SHORT).show()
                }
            } else {
                Log.d("ssssssssssssskmkmkkerro", "error3")
            }

        }
        if (requestCode == Gallery4) {
            if (resultCode == Activity.RESULT_OK) {
                var dataUri = data?.data
                try {
                    var bitmap: Bitmap =
                        MediaStore.Images.Media.getBitmap(this.contentResolver, dataUri)
                    imagepreView4.setImageBitmap(bitmap)
                } catch (e: Exception) {
                    Toast.makeText(this, "$e", Toast.LENGTH_SHORT).show()
                }
            } else {
                Log.d("ssssssssssssskmkmkkerro", "error4")
            }

        }
    }
    //---------- img end





}