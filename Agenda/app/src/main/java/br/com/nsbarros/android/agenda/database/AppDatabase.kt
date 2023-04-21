package br.com.nsbarros.android.agenda.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import br.com.nsbarros.android.agenda.database.dao.AlunoDaoI
import br.com.nsbarros.android.agenda.model.Aluno


@Database(entities = [Aluno::class], version = 3)
//@TypeConverters(Converters::class)
abstract class AppDatabase : RoomDatabase() {

    abstract fun daoAluno(): AlunoDaoI

    companion object {

        @Volatile
        private lateinit var db: AppDatabase
        fun instance(context: Context) : AppDatabase {

            if(::db.isInitialized) return  db

            return Room.databaseBuilder(context, AppDatabase::class.java, "agenda.db")
                //.allowMainThreadQueries()
                .build().also { db = it };
        }
    }

}