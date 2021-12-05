package es.unex.goenjoy.room;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;
import es.unex.goenjoy.model.Perfil;
import java.util.List;

@Dao
public interface PerfilDao {
    @Query("SELECT * FROM perfil")
    LiveData<List<Perfil>> get();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public void insert(Perfil perfil);
    @Update
    public void update(Perfil perfil);

    @Query("DELETE FROM perfil")
    public void delete();
}