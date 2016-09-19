package com.gao.tabview.Activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;

import com.gao.tabview.R;
import com.mikepenz.materialdrawer.Drawer;
import com.mikepenz.materialdrawer.DrawerBuilder;
import com.mikepenz.materialdrawer.model.DividerDrawerItem;
import com.mikepenz.materialdrawer.model.PrimaryDrawerItem;
import com.mikepenz.materialdrawer.model.SecondaryDrawerItem;
import com.mikepenz.materialdrawer.model.interfaces.IDrawerItem;

/**
 * Created by GaoMatrix on 2016/9/19.
 */
public class MaterialDrawerActivity extends Activity{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_material_drawer);

        initDrawer();
    }

    private void initDrawer() {
        PrimaryDrawerItem item1 = new PrimaryDrawerItem().withIdentifier(1).withName("home");
        SecondaryDrawerItem item2 = new SecondaryDrawerItem().withIdentifier(2).withName("settings");

        Drawer result = new DrawerBuilder()
                .withActivity(this)
                //.withToolbar(toolbar)
                .addDrawerItems(
                        item1,
                        new DividerDrawerItem(),
                        item2,
                        new SecondaryDrawerItem().withName("settings")
                )
                .withOnDrawerItemClickListener(new Drawer.OnDrawerItemClickListener() {
                    @Override
                    public boolean onItemClick(View view, int position, IDrawerItem drawerItem) {
                        // do something with the clicked item :D
                        return true;
                    }
                })
                .build();
    }
}
