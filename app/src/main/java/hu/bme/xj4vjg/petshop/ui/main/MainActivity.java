package hu.bme.xj4vjg.petshop.ui.main;

import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import butterknife.Bind;
import butterknife.ButterKnife;
import hu.bme.xj4vjg.petshop.R;
import hu.bme.xj4vjg.petshop.ui.BaseActivity;
import hu.bme.xj4vjg.petshop.ui.Navigator;
import hu.bme.xj4vjg.petshop.ui.main.navigation.NavigationMenuDetail;
import hu.bme.xj4vjg.petshop.ui.petlist.PetListFragment;

public class MainActivity extends BaseActivity implements
		NavigationView.OnNavigationItemSelectedListener,
		PetListFragment.OnPetListFragmentInteractionListener {
	@Bind(R.id.drawer_layout)
	DrawerLayout drawerLayout;
	@Bind(R.id.navigation_view)
	NavigationView navigationView;
	@Bind(R.id.toolbar)
	Toolbar toolbar;
	TextView usernameTextView;

	private NavigationMenuDetail petsMenuDetail;
	private NavigationMenuDetail addPetMenuDetail;
	private NavigationMenuDetail logoutMenuDetail;
	private NavigationMenuDetail currentMenuDetail;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		ButterKnife.bind(this);
		usernameTextView = (TextView) navigationView.getHeaderView(0).findViewById(R.id.username_text_view);
		usernameTextView.setText("Koós Dániel");

		setToolbar(toolbar);
		setNavigationViewMenuDetails();
		setNavigationView();
		updateContent(currentMenuDetail);
	}

	private void setNavigationViewMenuDetails() {
		petsMenuDetail = new NavigationMenuDetail(0, PetListFragment.TAG, getString(R.string.fragment_pet_list_title));
		addPetMenuDetail = new NavigationMenuDetail(1, PetListFragment.TAG, getString(R.string.fragment_add_pet_title));
		logoutMenuDetail = new NavigationMenuDetail(null, null, null);
		currentMenuDetail = petsMenuDetail;
	}

	private void setNavigationView() {
		navigationView.setNavigationItemSelectedListener(this);
		ActionBarDrawerToggle actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.openDrawer, R.string.closeDrawer) {
			@Override
			public void onDrawerClosed(View drawerView) {
				super.onDrawerClosed(drawerView);
			}

			@Override
			public void onDrawerOpened(View drawerView) {
				super.onDrawerOpened(drawerView);
			}
		};

		drawerLayout.addDrawerListener(actionBarDrawerToggle);
		actionBarDrawerToggle.syncState();
	}

	@Override
	public boolean onNavigationItemSelected(MenuItem menuItem) {
		boolean changeContent;
		switch (menuItem.getItemId()) {
			case R.id.nav_menu_pets:
				if (currentMenuDetail != petsMenuDetail) {
					changeContent = true;
					currentMenuDetail = petsMenuDetail;
				} else {
					changeContent = false;
				}
				break;

			case R.id.nav_menu_add_pet:
				if (currentMenuDetail != addPetMenuDetail) {
					changeContent = true;
					currentMenuDetail = addPetMenuDetail;
				} else {
					changeContent = false;
				}
				break;

			case R.id.nav_menu_logout:
				changeContent = false;
				currentMenuDetail = logoutMenuDetail;
				Navigator.navigateToLogin(this);
				break;

			default:
				changeContent = true;
				currentMenuDetail = petsMenuDetail;
		}

		if (changeContent) {
			updateContent(currentMenuDetail);
		}

		return true;
	}

	private void updateContent(NavigationMenuDetail navigationMenuDetail) {
		selectNavigationMenu(navigationMenuDetail.getIndex());
		updateTitle(navigationMenuDetail.getTitle());
		loadFragment(navigationMenuDetail.getFragmentTag());
	}

	private void selectNavigationMenu(Integer index) {
		if (index != null) {
			navigationView.getMenu().getItem(index).setChecked(true);
		}
	}

	private void updateTitle(String title) {
		if (title != null) {
			getSupportActionBar().setTitle(title);
		}
	}

	private void loadFragment(String fragmentTag) {
		if (fragmentTag != null) {
			FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
			Fragment fragment;

			switch (fragmentTag) {
				case PetListFragment.TAG:
					fragment = PetListFragment.newInstance();
					break;

				default:
					fragment = PetListFragment.newInstance();
					break;
			}

			transaction.replace(R.id.fragment_container, fragment);
			transaction.addToBackStack(null);
			transaction.commit();

			drawerLayout.closeDrawers();
			invalidateOptionsMenu();
		}
	}

	@Override
	public void onPetSelected(String petId) {
		showMessage("Pet selected " + petId);
	}

	@Override
	public void onBackPressed() {
		if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
			drawerLayout.closeDrawers();
		} else {
			super.onBackPressed();
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// TODO
		return super.onCreateOptionsMenu(menu);
	}
}
