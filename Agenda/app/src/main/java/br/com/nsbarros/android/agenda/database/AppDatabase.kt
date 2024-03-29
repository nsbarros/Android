package br.com.nsbarros.android.agenda.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import br.com.nsbarros.android.agenda.database.dao.AlunoDaoI
import br.com.nsbarros.android.agenda.database.dao.usuario.UsuarioDaoI
import br.com.nsbarros.android.agenda.model.Aluno
import br.com.nsbarros.android.agenda.model.Usuario


@Database(
    entities = [Aluno::class,
        Usuario::class],
    version = 14,
    exportSchema = true
)
//@TypeConverters(Converters::class)
abstract class AppDatabase : RoomDatabase() {

    abstract fun daoAluno(): AlunoDaoI
    abstract fun daoUsuario(): UsuarioDaoI

    companion object {

        @Volatile
        private lateinit var db: AppDatabase

        fun instance(context: Context): AppDatabase {

            if (::db.isInitialized) return db

            return Room.databaseBuilder(context, AppDatabase::class.java, "agenda.db")
                //.allowMainThreadQueries()
                .fallbackToDestructiveMigration()
                .addMigrations(MIGRATION_13_14)
                .build().also { db = it };
        }
    }

}