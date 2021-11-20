package es.unex.goenjoy.room;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import es.unex.goenjoy.model.Museo;
import java.util.List;

@Dao
public interface MuseoDao {
    @Query("SELECT * FROM museos")
    List<Museo> getAll();

    @Query("SELECT * FROM museos WHERE tipo=1")
    List<Museo> getAllMuseo();

    @Query("SELECT * FROM museos WHERE tipo=2")
    List<Museo> getAllParque();

    @Query("SELECT * FROM museos WHERE ruta=1")
    List<Museo> getAllRuta();

    @Query("SELECT * FROM museos WHERE fav=1")
    List<Museo> getAllFav();

    @Update
    public void update(Museo museo);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public void insert(Museo museo);

    @Query("DELETE FROM museos WHERE fav=0 AND deseo=0")
    public void deleteAll();

    @Query("SELECT * FROM museos WHERE title=:titulo")
    public Museo getMuseo(String titulo);

    @Query("SELECT * FROM museos WHERE deseo=1")
    List<Museo> getAllDes();

    @Delete
    public void delete(Museo museo);
}
