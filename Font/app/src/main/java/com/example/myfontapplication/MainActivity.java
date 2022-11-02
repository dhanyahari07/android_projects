package com.example.myfontapplication;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;

import info.androidhive.fontawesome.FontDrawable;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FloatingActionButton fab = findViewById(R.id.fab);

// using paper plane icon for FAB
        FontDrawable drawable = new FontDrawable(this, R.string.fa_paper_plane_solid, true, false);

// white color to icon
        drawable.setTextColor(ContextCompat.getColor(this, android.R.color.white));
        fab.setImageDrawable(drawable);
        intDrawerLayout();
    }

    private void intDrawerLayout() {


            NavigationView navigationView = findViewById(R.id.nav_view);

            navigationView.setNavigationItemSelectedListener((NavigationView.OnNavigationItemSelectedListener) getApplicationContext());

            ImageView iconHeader = navigationView.getHeaderView(0).findViewById(R.id.nav_header_icon);
            FontDrawable drawable = new FontDrawable(this, R.string.fa_font_awesome, false, true);
            drawable.setTextColor(ContextCompat.getColor(this, android.R.color.white));
            drawable.setTextSize(50);
            iconHeader.setImageDrawable(drawable);

            int[] icons = {
                    R.string.fa_home_solid, R.string.fa_calendar_alt_solid, R.string.fa_user_solid,
                    R.string.fa_heart_solid, R.string.fa_comment_solid, R.string.fa_dollar_sign_solid, R.string.fa_gift_solid
            };
            renderMenuIcons(navigationView.getMenu(), icons, true, false);

            int[] iconsSubmenu = {R.string.fa_cog_solid, R.string.fa_sign_out_alt_solid};

            renderMenuIcons(navigationView.getMenu().getItem(7).getSubMenu(), iconsSubmenu, true, false);
        }

        /**
         * Looping through menu icons are applying font drawable
         */
        private void renderMenuIcons(Menu menu, int[] icons, boolean isSolid, boolean isBrand) {
            for (int i = 0; i < menu.size(); i++) {
                MenuItem menuItem = menu.getItem(i);
                if (!menuItem.hasSubMenu()) {
                    FontDrawable drawable = new FontDrawable(this, icons[i], isSolid, isBrand);
                    drawable.setTextColor(ContextCompat.getColor(this, R.color.icon_nav_drawer));
                    drawable.setTextSize(22);
                    menu.getItem(i).setIcon(drawable);
                }
            }
        }


    }
