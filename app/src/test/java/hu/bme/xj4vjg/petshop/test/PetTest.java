package hu.bme.xj4vjg.petshop.test;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.annotation.Config;

import java.util.ArrayList;
import java.util.List;

import hu.bme.xj4vjg.petshop.BuildConfig;
import hu.bme.xj4vjg.petshop.model.Species;
import hu.bme.xj4vjg.petshop.ui.addpet.AddPetPresenter;
import hu.bme.xj4vjg.petshop.ui.addpet.AddPetScreen;
import hu.bme.xj4vjg.petshop.ui.login.LoginPresenter;
import hu.bme.xj4vjg.petshop.ui.login.LoginScreen;
import hu.bme.xj4vjg.petshop.ui.petdetail.PetDetailPresenter;
import hu.bme.xj4vjg.petshop.ui.petdetail.PetDetailScreen;
import hu.bme.xj4vjg.petshop.ui.petlist.PetListPresenter;
import hu.bme.xj4vjg.petshop.ui.petlist.PetListScreen;
import hu.bme.xj4vjg.petshop.utils.RobolectricDaggerTestRunner;

import static hu.bme.xj4vjg.petshop.TestHelper.setTestInjector;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.internal.verification.VerificationModeFactory.times;

@RunWith(RobolectricDaggerTestRunner.class)
@Config(constants = BuildConfig.class, sdk = 21)
public class PetTest {
	private LoginPresenter loginPresenter;
	private PetListPresenter petListPresenter;
	private PetDetailPresenter petDetailPresenter;
	private AddPetPresenter addPetPresenter;

	@Before
	public void setup() throws Exception {
		setTestInjector();
		loginPresenter = new LoginPresenter();
		petListPresenter = new PetListPresenter();
		petDetailPresenter = new PetDetailPresenter();
		addPetPresenter = new AddPetPresenter();

		LoginScreen loginScreen = mock(LoginScreen.class);
		loginPresenter.attachScreen(loginScreen);
		loginPresenter.login("user", "pass");
		verify(loginScreen, times(1)).naviageToPetList();
	}

	@Test
	public void testListPets() {
		PetListScreen petListScreen = mock(PetListScreen.class);
		petListPresenter.attachScreen(petListScreen);

		petListPresenter.updateSpecies();
		petListPresenter.updatePets();
		verify(petListScreen, times(1)).refreshPets();
		assertTrue(petListPresenter.getSpeciesList().size() > 0);
		assertTrue(petListPresenter.getPetList().size() > 0);
	}

	@Test
	public void testFilterPets() {
		PetListScreen petListScreen = mock(PetListScreen.class);
		petListPresenter.attachScreen(petListScreen);

		petListPresenter.updateSpecies();
		petListPresenter.updatePets();
		verify(petListScreen, times(1)).refreshPets();
		assertTrue(petListPresenter.getSpeciesList().size() > 0);
		assertTrue(petListPresenter.getPetList().size() > 0);

		List<Species> filters = new ArrayList<>();
		filters.add(petListPresenter.getSpeciesList().get(0));
		filters.add(petListPresenter.getSpeciesList().get(1));
		petListPresenter.applySpeciesFilters(filters);
		verify(petListScreen, times(2)).refreshPets();
		assertTrue(petListPresenter.getPetList().size() > petListPresenter.getFilteredPetList().size());
	}

	@Test
	public void testGetPetDetail() {
		String petId = "pet1";
		PetDetailScreen petDetailScreen = mock(PetDetailScreen.class);
		petDetailPresenter.attachScreen(petDetailScreen);

		petDetailPresenter.setPetId(petId);
		petDetailPresenter.updatePetDetail();
		verify(petDetailScreen, times(1)).refreshPetDetail();
		assertTrue(petDetailPresenter.getPet() != null);
		assertTrue(petDetailPresenter.getPet().getPetId().equals(petId));
	}

	@Test
	public void testGetUnknownPetDetail() {
		String petId = "unknown_pet_id";
		PetDetailScreen petDetailScreen = mock(PetDetailScreen.class);
		petDetailPresenter.attachScreen(petDetailScreen);

		petDetailPresenter.setPetId(petId);
		petDetailPresenter.updatePetDetail();
		verify(petDetailScreen, times(1)).showUnknownPetMessage();
		verify(petDetailScreen, never()).refreshPetDetail();
	}

	@Test
	public void testAddPet() {
		AddPetScreen addPetScreen = mock(AddPetScreen.class);
		addPetPresenter.attachScreen(addPetScreen);

		addPetPresenter.addPet("dog", "white", System.currentTimeMillis(), 6000, "");
		verify(addPetScreen, times(1)).navigateToPetList();
	}

	@Test
	public void testAddPetWithoutSpecies() {
		AddPetScreen addPetScreen = mock(AddPetScreen.class);
		addPetPresenter.attachScreen(addPetScreen);

		addPetPresenter.addPet(null, "white", System.currentTimeMillis(), 6000, "");
		verify(addPetScreen, times(1)).showWrongPetDetailMessage();
		verify(addPetScreen, never()).navigateToPetList();
	}

	@After
	public void tearDown() {
		loginPresenter.detachScreen();
		petListPresenter.detachScreen();
		petDetailPresenter.detachScreen();
		addPetPresenter.detachScreen();
	}
}
