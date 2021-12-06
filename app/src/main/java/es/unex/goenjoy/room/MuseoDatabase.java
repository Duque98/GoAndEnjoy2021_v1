//*
package es.unex.goenjoy.room;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import es.unex.goenjoy.model.Museo;
import es.unex.goenjoy.model.Perfil;

import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


@Database(entities = {Museo.class, Perfil.class},version=1,exportSchema = false)
public abstract class MuseoDatabase extends RoomDatabase {
    public abstract MuseoDao museoDao();
    public abstract PerfilDao perfilDao();
    //public abstract ActualizarDao actualizarDao();

    private static volatile MuseoDatabase instance;
    private static final int NUMBER_OF_THREADS=4;

    public static final ExecutorService databaseWriteExecutor = Executors.newFixedThreadPool(NUMBER_OF_THREADS);

    public static MuseoDatabase getDatabase (final Context context){
        if(instance==null){
            synchronized (MuseoDatabase.class){
                if(instance == null)
                    instance = Room.databaseBuilder(context.getApplicationContext(), MuseoDatabase.class, "museos.db")
                            .build();
            }
        }
        return instance;
    }
/*
    static final Migration MIGRATION_2_3 = new Migration(2,3){
        @Override
        public void migrate(@NonNull SupportSQLiteDatabase database) {
            database.execSQL("CREATE TABLE 'actualizacion' ('id' INTEGER,'fechaActualizacion' INTEGER," +
                    " PRIMARY KEY ('id'))");
        }
    };*/
}