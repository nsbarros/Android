package br.com.nsbarros.android.agenda.database

import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase

val MIGRATION_1_13 = object : Migration(1, 13) {
    override fun migrate(database: SupportSQLiteDatabase) {
        database.execSQL("CREATE TABLE IF NOT EXISTS `Usuario` (`id` TEXT NOT NULL, `nome` TEXT NOT NULL, `senha` TEXT NOT NULL, PRIMARY KEY(`id`))")
    }
}

val MIGRATION_13_14 = object : Migration(13, 14){
    override fun migrate(database: SupportSQLiteDatabase) {
        database.execSQL("ALTER TABLE Aluno ADD COLUMN 'usuarioId' TEXT")
    }
}