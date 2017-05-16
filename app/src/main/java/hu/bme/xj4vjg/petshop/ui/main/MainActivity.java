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
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;
import hu.bme.xj4vjg.petshop.R;
import hu.bme.xj4vjg.petshop.model.Settings;
import hu.bme.xj4vjg.petshop.ui.BaseActivity;
import hu.bme.xj4vjg.petshop.ui.Navigator;
import hu.bme.xj4vjg.petshop.ui.addpet.AddPetFragment;
import hu.bme.xj4vjg.petshop.ui.main.navigation.ContentDetail;
import hu.bme.xj4vjg.petshop.ui.petdetail.PetDetailFragment;
import hu.bme.xj4vjg.petshop.ui.petlist.PetListFragment;

import static hu.bme.xj4vjg.petshop.PetShopApplication.injector;

public class MainActivity extends BaseActivity implements
		NavigationView.OnNavigationItemSelectedListener,
		PetListFragment.OnPetListFragmentInteractionListener {
	@Inject
	Settings settings;

	@Bind(R.id.drawer_layout)
	DrawerLayout drawerLayout;
	@Bind(R.id.navigation_view)
	NavigationView navigationView;
	@Bind(R.id.toolbar)
	Toolbar toolbar;
	TextView usernameTextView;

	private ContentDetail petsContentDetail;
	private ContentDetail addPetContentDetail;
	private ContentDetail petDetailContentDetail;
	private ContentDetail logoutContentDetail;
	private ContentDetail currentContentDetail;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		injector.inject(this);
		ButterKnife.bind(this);

		setToolbar(toolbar);
		setNavigationHeader();
		setContentDetails();
		setNavigationView();
		updateContent(currentContentDetail);
	}

	private void setNavigationHeader() {
		usernameTextView = (TextView) navigationView.getHeaderView(0).findViewById(R.id.username_text_view);
		usernameTextView.setText(settings.getUsername());
	}

	private void setContentDetails() {
		petsContentDetail = new ContentDetail(0, getString(R.string.fragment_pet_list_title), PetListFragment.TAG, new Bundle());
		addPetContentDetail = new ContentDetail(1, getString(R.string.fragment_add_pet_title), AddPetFragment.TAG, new Bundle());
		petDetailContentDetail = new ContentDetail(null, getString(R.string.fragment_pet_detail_title), PetDetailFragment.TAG, new Bundle());
		logoutContentDetail = new ContentDetail(null, null, null, null);
		currentContentDetail = petsContentDetail;
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
				if (currentContentDetail != petsContentDetail) {
					changeContent = true;
					currentContentDetail = petsContentDetail;
				} else {
					changeContent = false;
				}
				break;

			case R.id.nav_menu_add_pet:
				if (currentContentDetail != addPetContentDetail) {
					changeContent = true;
					currentContentDetail = addPetContentDetail;
				} else {
					changeContent = false;
				}
				break;

			case R.id.nav_menu_logout:
				changeContent = false;
				currentContentDetail = logoutContentDetail;
				Navigator.navigateToLogin(this);
				break;

			default:
				changeContent = true;
				currentContentDetail = petsContentDetail;
		}

		if (changeContent) {
			updateContent(currentContentDetail);
		}

		return true;
	}

	public void updateTitle(String title) {
		getSupportActionBar().setTitle(title);
	}

	private void updateContent(ContentDetail contentDetail) {
		selectNavigationMenu(contentDetail.getIndex());
		loadFragment(contentDetail.getFragmentTag(), currentContentDetail.getFragmentArgs());
	}

	private void selectNavigationMenu(Integer index) {
		if (index != null) {
			navigationView.getMenu().getItem(index).setChecked(true);
		}
	}

	private void unselectNavigationMenu(Integer index) {
		if (index != null) {
			navigationView.getMenu().getItem(index).setChecked(false);
		}
	}

	private void loadFragment(String fragmentTag, Bundle fragmentArgs) {
		if (fragmentTag != null) {
			FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
			Fragment fragment;

			switch (fragmentTag) {
				case PetListFragment.TAG:
					fragment = PetListFragment.newInstance();
					break;

				case AddPetFragment.TAG:
					fragment = AddPetFragment.newInstance();
					break;

				case PetDetailFragment.TAG:
					fragment = PetDetailFragment.newInstance(fragmentArgs.getString(PetDetailFragment.PET_ID_ARG));
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
		unselectNavigationMenu(currentContentDetail.getIndex());

		Bundle args = new Bundle();
		args.putString(PetDetailFragment.PET_ID_ARG, petId);
		petDetailContentDetail.setFragmentArgs(args);
		currentContentDetail = petDetailContentDetail;

		loadFragment(currentContentDetail.getFragmentTag(), currentContentDetail.getFragmentArgs());
	}

	@Override
	public void onBackPressed() {
		if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
			drawerLayout.closeDrawers();
		} else {
			if (currentContentDetail == petsContentDetail) {
				Navigator.navigateToLogin(this);
			} else {
				currentContentDetail = petsContentDetail;
				updateContent(currentContentDetail);
			}
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		if (currentContentDetail == petsContentDetail) {
			MenuInflater inflater = getMenuInflater();
			inflater.inflate(R.menu.menu_pet_list, menu);
			return true;
		}
		return super.onCreateOptionsMenu(menu);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
			case R.id.menu_add_pet:
				currentContentDetail = addPetContentDetail;
				updateContent(currentContentDetail);
				return true;

			case R.id.menu_filter:
				showFilterPetsDialog();
				return true;

			default:
				return super.onOptionsItemSelected(item);
		}
	}

	public void showFilterPetsDialog() {

	}
}
