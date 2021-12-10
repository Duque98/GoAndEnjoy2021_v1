package es.unex.goenjoy.APITest;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import android.content.Context;

import org.junit.Test;

import java.util.List;

import es.unex.goenjoy.model.Museo;
import es.unex.goenjoy.repository.OnReposLoadedListener;
import es.unex.goenjoy.repository.ReposNetworkLoaderRunnable;

public class PeticionApiTest {

    @Test
    public void peticionTest(){
        Context context = null;
        new ReposNetworkLoaderRunnable(new OnReposLoadedListener() {
            @Override
            public void onReposLoaded(List<Museo> reposMuseo) {
                assertNotNull(reposMuseo);
                assertFalse(reposMuseo.isEmpty());
                assertTrue(reposMuseo.get(0).getTitle().equals("Museo Arqueológico Nacional"));
            }
        });
    }
}
