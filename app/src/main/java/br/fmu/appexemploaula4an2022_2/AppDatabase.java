package br.fmu.appexemploaula4an2022_2;

import android.content.Context;

import androidx.lifecycle.MutableLiveData;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverters;

@Database(entities = {Funcionario.class}, version=1)
@TypeConverters(DateConverter.class)
abstract public class AppDatabase extends RoomDatabase {
    private static AppDatabase instance;
    public static final String DATABASE_NAME = "teste.db";
    public abstract FuncionarioDao funcionarioDao();
    private final MutableLiveData<Boolean> mIsInstanceCreated=new MutableLiveData<>();
    public static AppDatabase getInstance(final Context context) {
        if( instance == null ) {
            synchronized (AppDatabase.class) {
                if ( instance == null ) {
                    instance = buildDatabase(context.getApplicationContext());
                    instance.updateDatabaseCreated(context.getApplicationContext());
                }
            }
        }
        return instance;
    }

    private static AppDatabase buildDatabase(final Context context) {
        return Room.databaseBuilder(context,
                AppDatabase.class, DATABASE_NAME).allowMainThreadQueries().build();
    }
    private void updateDatabaseCreated(final Context context) {
        if(context.getDatabasePath(DATABASE_NAME).exists()) {
            mIsInstanceCreated.postValue(true);
        }
    }
}
