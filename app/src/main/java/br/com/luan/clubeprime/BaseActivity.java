package br.com.luan.clubeprime;

import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.LayoutRes;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.FrameLayout;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

import br.com.luan.clubeprime.adapter.MenuAdapter;
import br.com.luan.clubeprime.model.LeftMenu;
import de.hdodenhof.circleimageview.CircleImageView;


public class BaseActivity extends AppCompatActivity {

    private int currentPosition = 2;
    public DrawerLayout drawerLayout;
    public ListView drawerListView;
    public FrameLayout frameLayout;
    public ArrayList<LeftMenu> leftMenus;
    public ActionBarDrawerToggle drawerToggle;
    public int lastPosition = 2;
    public int icons[] = {
            R.drawable.ic_account,
            R.drawable.ic_calendar,
            R.drawable.ic_heart_menu,
            R.drawable.ic_promo,
            R.drawable.ic_clover,
            R.drawable.ic_ticket,
            R.drawable.ic_add_friend,
            R.drawable.ic_input,
    };
    public int icons_active[] = {
            R.drawable.ic_account_active,
            R.drawable.ic_calendar_active,
            R.drawable.ic_heart_menu_active,
            R.drawable.ic_promo_active,
            R.drawable.ic_clover_active,
            R.drawable.ic_ticket_active,
            R.drawable.ic_add_friend_active,
            R.drawable.ic_input_active,
    };

    public MenuAdapter menuAdapter;
    View thisView;
    @Override
    public void setContentView(@LayoutRes int layoutResID) {
        super.setContentView(R.layout.drawer_layout);

        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        frameLayout = (FrameLayout) findViewById(R.id.content_frame);

        drawerListView = (ListView) findViewById(R.id.left_drawer);
        drawerLayout.setScrimColor(Color.TRANSPARENT);

        drawerToggle = new ActionBarDrawerToggle(this,drawerLayout,R.drawable.menu,R.string.navigation_drawer_open,R.string.navigation_drawer_close) {
            float lastTranslate = 0.0f;
            @Override
            public void onDrawerSlide(View drawerView, float slideOffset) {
                //int moveFactor = (int) (slideOffset * drawerListView.getWidth());
//                TranslateAnimation anim = new TranslateAnimation(lastTranslate, moveFactor, 0.0f, 0.0f);
//                anim.setDuration(0);
//                anim.setFillAfter(true);
//                frameLayout.startAnimation(anim);
//                lastTranslate = moveFactor;
                super.onDrawerSlide(drawerView, slideOffset);
            }
        };
        drawerToggle.setDrawerIndicatorEnabled(true);
        // getActionBar().setDisplayHomeAsUpEnabled(true);
        // getActionBar().setHomeButtonEnabled(true);
        drawerToggle.setHomeAsUpIndicator(R.drawable.menu);
        drawerLayout.post(new Runnable() {
            @Override
            public void run() {
                drawerToggle.syncState();
            }
        });

        drawerLayout.addDrawerListener(drawerToggle);
        thisView = getLayoutInflater().inflate(layoutResID, frameLayout);

        setUp(drawerListView, frameLayout);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);


    }

    public void setUp(final ListView drawerListView, FrameLayout frameLayout){


        if(leftMenus == null)
            leftMenus = new ArrayList<>();

        leftMenus.add(new LeftMenu(1,"Perfil", icons[0],icons_active[0],true));
        leftMenus.add(new LeftMenu(2,"Eventos", icons[1],icons_active[1],false));
        leftMenus.add(new LeftMenu(3,"Favoritos",icons[2],icons_active[2],false));
        leftMenus.add(new LeftMenu(4,"Sorteios", icons[3],icons_active[3],false));
        leftMenus.add(new LeftMenu(5,"Ingresso Digital",icons[4],icons_active[4],false));
        leftMenus.add(new LeftMenu(6,"Convide um Amigo", icons[5],icons_active[5],false));
        leftMenus.add(new LeftMenu(7,"Deslogar", icons[6],icons_active[6],false));


        try{
            if(getIntent().hasExtra("position"))
                currentPosition = leftMenus.get(getPosition(getIntent().getExtras().getInt("position"))).getId();
        }catch (Exception ex){
            currentPosition = 2;
        }

        drawerListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                selectItem(((LeftMenu) parent.getItemAtPosition(position)).getId());
                goToActivity(((LeftMenu) parent.getItemAtPosition(position)).getId());
            }
        });


        menuAdapter = new MenuAdapter(this,leftMenus);
        View header = View.inflate(this, R.layout.drawer_layout_header, null);
        // View footer = View.inflate(this, R.layout.drawer_layout_footer, null);
        drawerListView.addHeaderView(header, null, false);

        CircleImageView imageView = (CircleImageView) header.findViewById(R.id.cirleimageView);
        TextView nome = (TextView) header.findViewById(R.id.nome);

        drawerListView.setAdapter(menuAdapter);
        selectItem(currentPosition);
    }

    public int getPosition(int id){
        for (int i = 0; i < leftMenus.size(); i++) {
            if(leftMenus.get(i).getId() == id) {
                return i;
            }
        }
        return 0;
    }

    public void selectItem(int id){
        int position = getPosition(id);
        for (int i = 0; i < leftMenus.size(); i++) {
            leftMenus.get(i).setActive(false);
            leftMenus.get(i).setActive(false);

        }
        leftMenus.get(position).setActive(true);
        menuAdapter.notifyDataSetChanged();
    }
    public void goToActivity(final int i) {
        drawerLayout.closeDrawer(Gravity.LEFT);
        //finish();
        switch (i) {
            case 1:
                Intent perfil = new Intent(getApplication(), PerfilActivity.class).putExtra("position", i);
                perfil.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(perfil);
                break;
            case 2:
                Intent eventos= new Intent(getApplication(), MainActivity.class).putExtra("position", i);
                eventos.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(eventos);
                break;
            case 3:
                Intent favoritos= new Intent(getApplication(), FavotirosActivity.class).putExtra("position", i);
                favoritos.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(favoritos);
                break;
            case 4:
                Intent sorteio= new Intent(getApplication(), SorteioActivity.class).putExtra("position", i);
                sorteio.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(sorteio);
                break;
            case 5:
                Intent ingresso = new Intent(getApplication(), IngressoActivity.class).putExtra("position", i);;
                ingresso.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(ingresso);
                break;
            case 6:
                Intent intentCalendario = new Intent(getApplication(), ConvidarActivity.class).putExtra("position", i);
                intentCalendario.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intentCalendario);
                break;
            case 7:
                Intent logout  = new Intent(getApplication(), LoginActivity.class).putExtra("position", i);
                logout.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                finishAffinity();
                startActivity(logout);
                break;
            default:

                break;
        }
        //overridePendingTransition(R.anim.activity_in, R.anim.activity_out);
       /*
       new Handler().postDelayed(new Runnable() {
            public void run() { }
       },200);
       */
    }

    public void myFinish(){
        new Handler().postDelayed(new Runnable() {
            public void run() {
                BaseActivity.this.finish();
            }
        },400);
    }


    public void myDrawerLayout(){
        new Handler().postDelayed(new Runnable() {
            public void run() {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        drawerLayout.closeDrawer(Gravity.LEFT);
                    }
                });

            }
        },400);
    }


    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
           // selectItem(lastPosition);
            super.onBackPressed();
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (drawerToggle.onOptionsItemSelected(item)) {
            return true;
        }
        if(id == android.R.id.home){
            onBackPressed();
        }
        return super.onOptionsItemSelected(item);

    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        drawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        drawerToggle.onConfigurationChanged(newConfig);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

    }


}
