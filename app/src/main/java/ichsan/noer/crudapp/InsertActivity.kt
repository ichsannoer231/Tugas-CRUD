package ichsan.noer.crudapp

import android.app.AlertDialog
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_insert.*
import kotlinx.android.synthetic.main.component_button_insert_conf.*

class InsertActivity : AppCompatActivity(){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_insert)
        val dataHelper = DataHelper(this)
        b_insert.setOnClickListener{
            val alertDialogBuilder = AlertDialog.Builder(this)
            alertDialogBuilder.setTitle("Konfirmasi")
                .setMessage("Are you sure to insert it?")
                .setCancelable(true)
                .setPositiveButton("No"){dialog,which->
                }
                .setNegativeButton("Yes"){dialog,which->
                    val nisn = et_nisn.text.toString()
                    val name = et_name.text.toString()
                    val kelas = et_kelas.text.toString()
                    dataHelper.addStudent(Student(nisn,name,kelas))
                    et_name.setText("")
                    et_nisn.setText("")
                    et_kelas.setText("")
                }
            val alertDialog = alertDialogBuilder.create()
            alertDialog.show()
        }
    }
}