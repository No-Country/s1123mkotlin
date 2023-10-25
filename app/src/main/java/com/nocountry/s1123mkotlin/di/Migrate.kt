import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase



val MIGRATION_1_2: Migration = object : Migration(1, 2) {
    override fun migrate(database: SupportSQLiteDatabase) {
        // Define las operaciones de migración aquí
        // Por ejemplo, si deseas agregar una nueva columna a una tabla, puedes hacerlo aquí.
        database.execSQL("ALTER TABLE reminder ADD COLUMN nueva_columna TEXT")
    }
}
