package ichsan.noer.crudapp

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.widget.Toast

class DataHelper(private val context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null,DATABASE_VERSION){
    companion object{
        private val DATABASE_NAME = "biodatadiri.db"
        private val DATABASE_VERSION = 3
        val TABLE_NAME = "STUDENT"
        val KEY_NISN = "nisn"
        val KEY_NAME = "name"
        val KEY_KELAS = "kelas"
    }

    override fun onCreate(db: SQLiteDatabase) {
        val CREATE_PRODUCTS_TABLE = ("CREATE TABLE " +
                TABLE_NAME + "("
                + "NISN" + " TEXT," +
                "NAME" + " TEXT," +
                "KELAS" + " TEXT)")
        db.execSQL(CREATE_PRODUCTS_TABLE)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL("DROP TABLE IF EXISTS "+ TABLE_NAME)
        onCreate(db)
    }

    fun addStudent(student: Student):Boolean{
        var db = this.writableDatabase
        var values = ContentValues()
        values.put(KEY_NISN,student.nisn)
        values.put(KEY_NAME,student.name)
        values.put(KEY_KELAS,student.kelas)
        val success = db.insert(TABLE_NAME,null,values)
        db.close()
        if (success.toInt() == -1){
            Toast.makeText(context,"Terjadi Kesalahan dalam Insert, Coba Lagi",Toast.LENGTH_SHORT).show()
            return false
        }
        else{
            Toast.makeText(context,"Berhasil Insert",Toast.LENGTH_SHORT).show()
            return true
        }
    }

    fun deleteStudent(student: Student){
        var db = this.writableDatabase
        val selectionArgs = arrayOf(student.nisn)
        db.delete(TABLE_NAME, KEY_NISN+" = ? ",selectionArgs)
    }

    fun getAllStudent():ArrayList<Student>{
        var db = this.writableDatabase
        var studentList : ArrayList<Student> = ArrayList()
        val selectAll = "SELECT * FROM "+ TABLE_NAME
        val cursor = db.rawQuery(selectAll,null)
        if (cursor.moveToFirst()){
            do{
                val student = Student()
                student.nisn = cursor.getString(0)
                student.name = cursor.getString(1)
                student.kelas = cursor.getString(2)
                studentList.add(student)
            } while (cursor.moveToNext())
        }
        return studentList
    }

}