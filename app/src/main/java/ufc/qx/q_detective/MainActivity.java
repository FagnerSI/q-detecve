package ufc.qx.q_detective;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;


public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    public void optionSelect(View view) {

        if (view.getId() == R.id.denunciar) {
            Intent intent = new Intent(this, DenunciaActivity.class);
            Bundle bundle = new Bundle();
            intent.putExtras(bundle);
            startActivity(intent);
        }
        if (view.getId() == R.id.minhasDenuncias) {
            Intent intent = new Intent(this, ListaMinhasDenuncias.class);
            Bundle bundle = new Bundle();
            intent.putExtras(bundle);
            startActivity(intent);
        }
        if (view.getId() == R.id.listaServer) {
            Intent intent = new Intent(this, ListaWebService.class);
            Bundle bundle = new Bundle();
            intent.putExtras(bundle);
            startActivity(intent);
        }
    }
}
