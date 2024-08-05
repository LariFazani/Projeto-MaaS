package br.com.fiap.projetomaas

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.content.ContentValues
import android.database.Cursor

class DataBaseManager(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {
    companion object {
        private const val DATABASE_NAME = "userTransport"
        private const val DATABASE_VERSION = 1

        private const val TABLE_NAME = "tbl_users"
        private const val COLUMN_ID = "id"
        const val COLUMN_NAME = "name"
        const val COLUMN_EMAIL = "email"
        private const val COLUMN_PASSWORD = "password"
        const val COLUMN_MOBILITY = "mobility"
        const val COLUMN_BUS = "bus"
        const val COLUMN_METRO = "metro"
        const val COLUMN_TRAIN = "train"
        const val COLUMN_CAR = "car"
        const val COLUMN_SCOOTER = "scooter"
        const val COLUMN_BIKE = "bike"
    }

    override fun onCreate(db: SQLiteDatabase) {
        val CREATE_TABLE_QUERY = """
            CREATE TABLE $TABLE_NAME (
                $COLUMN_ID INTEGER PRIMARY KEY AUTOINCREMENT,
                $COLUMN_NAME TEXT,
                $COLUMN_EMAIL TEXT,
                $COLUMN_PASSWORD TEXT,
                $COLUMN_MOBILITY INTEGER,
                $COLUMN_BUS INTEGER,
                $COLUMN_METRO INTEGER,
                $COLUMN_TRAIN INTEGER,
                $COLUMN_CAR INTEGER,
                $COLUMN_SCOOTER INTEGER,
                $COLUMN_BIKE INTEGER
            )
        """.trimIndent()

        db.execSQL(CREATE_TABLE_QUERY)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL("DROP TABLE IF EXISTS $TABLE_NAME")
        onCreate(db)
    }

    fun addUser(name: String, email: String, password: String) {
        val db = this.writableDatabase

        val values = ContentValues().apply {
            put(COLUMN_NAME, name)
            put(COLUMN_EMAIL, email)
            put(COLUMN_PASSWORD, password)
        }

        db.insert(TABLE_NAME, null, values)
        db.close()
    }

    fun updateUser(name: String, email: String, mobility: Int, bus: Int, metro: Int, train: Int, car: Int, scooter: Int, bike: Int) {
        val db = this.writableDatabase

        val values = ContentValues().apply {
            put(COLUMN_NAME, name)
            put(COLUMN_EMAIL, email)
            put(COLUMN_MOBILITY, mobility)
            put(COLUMN_BUS, bus)
            put(COLUMN_METRO, metro)
            put(COLUMN_TRAIN, train)
            put(COLUMN_CAR, car)
            put(COLUMN_SCOOTER, scooter)
            put(COLUMN_BIKE, bike)
        }

        db.update(TABLE_NAME, values, "$COLUMN_EMAIL = ?", arrayOf(email.toString()))
        db.close()
    }

    fun deleteUser(id: Int) {
        val db = this.writableDatabase
        db.delete(TABLE_NAME, "$COLUMN_ID = ?", arrayOf(id.toString()))
        db.close()
    }

    fun checkUser(email: String, password: String): Boolean {
        val db = this.readableDatabase


        val query = "SELECT * FROM $TABLE_NAME WHERE $COLUMN_EMAIL = ? AND $COLUMN_PASSWORD = ?"
        val cursor = db.rawQuery(query, arrayOf(email, password))

        val userExists = cursor.moveToFirst()
        cursor.close()
        db.close()

        return userExists
    }

    fun getUserByEmail(email: String): Cursor? {
        val db = this.readableDatabase
        return db.rawQuery("SELECT * FROM ${DataBaseManager.TABLE_NAME} WHERE ${DataBaseManager.COLUMN_EMAIL} = ?", arrayOf(email))

    }
}