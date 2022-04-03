package com.gokkalsoft.VKI

import android.content.Context
import android.content.DialogInterface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        txtSonuc.text = null
        txtKilo.text = null
        txtBoy.text = null



    }



    fun Hesapla(view:View){
        try {
            var boyStr  = txtBoy.text.toString()
            var kilo = txtKilo.text.toString().toDoubleOrNull()
            var boy : String? = null
            if(boyStr.length<3){
                boy = (boyStr[0]+"."+boyStr[1]).toString()
            }else if(boyStr.length>=3) {
                boy = (boyStr[0] + "." + boyStr[1] + boyStr[2]).toString()
            }

            var sonuc: Double? = null
            if(boy == null || kilo == null){
                val uyari = AlertDialog.Builder(this)
                uyari.setTitle("Tanımsız değer")
                uyari.setMessage("Lütfen boy(cm) ve kilo alanlarını doldurunuz\n En doğru sonuc için boyunuzu cm cinsinden giriniz")
                uyari.setPositiveButton("Tamam",DialogInterface.OnClickListener{dialogInterface,i ->
                    Toast.makeText(this,"Tüm alanları doldurun",Toast.LENGTH_SHORT).show()

                    txtSonuc.text = null
                    txtKilo.text = null
                    txtBoy.text = null

                })
                uyari.show()
            }else{

                var boyKare = (boy.toDouble() * boy.toDouble())
                sonuc = kilo / boyKare
                if(sonuc<18.5f){
                    txtSonuc.text = "Vücut kitle indexiniz : " + sonuc.toInt().toString() +"\nDurumunuz : Zayıf"
                }else if((sonuc<24.9)&&(sonuc>18.5f)){
                    txtSonuc.text = "Vücut kitle indexiniz : " + sonuc.toInt().toString() +"\nDurumunuz : Normal"
                }else if((sonuc<29.9)&&(sonuc>25f)){
                    txtSonuc.text = "Vücut kitle indexiniz : " + sonuc.toInt().toString() +"\nDurumunuz : Fazla Kilolu"
                }else if((sonuc<39.9)&&(sonuc>30f)){
                    txtSonuc.text = "Vücut kitle indexiniz : " + sonuc.toInt().toString() +"\nDurumunuz : Obezite"
                }else if(sonuc>40){
                    txtSonuc.text = "Vücut kitle indexiniz : " + sonuc.toInt().toString() +"\nDurumunuz : Morbid Obez\nDoktorunuza başvurun"
                }

                txtBoy.text = null
                txtKilo.text = null

                val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                imm.hideSoftInputFromWindow(view.windowToken, 0)
            }

        }catch (e:Exception){
            Toast.makeText(this, "Kritik hata ! : yeniden deneyin !", Toast.LENGTH_SHORT).show()

        }





    }
}