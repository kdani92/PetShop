package hu.bme.xj4vjg.petshop.test;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.annotation.Config;

import hu.bme.xj4vjg.petshop.BuildConfig;
import hu.bme.xj4vjg.petshop.ui.login.LoginPresenter;
import hu.bme.xj4vjg.petshop.ui.login.LoginScreen;
import hu.bme.xj4vjg.petshop.utils.RobolectricDaggerTestRunner;

import static hu.bme.xj4vjg.petshop.TestHelper.setTestInjector;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.internal.verification.VerificationModeFactory.times;

@RunWith(RobolectricDaggerTestRunner.class)
@Config(constants = BuildConfig.class, sdk = 21)
public class AuthTest {
	private LoginPresenter loginPresenter;

	@Before
	public void setup() throws Exception {
		setTestInjector();
		loginPresenter = new LoginPresenter();
	}

	@Test
	public void testRegister() {
		LoginScreen loginScreen = mock(LoginScreen.class);
		loginPresenter.attachScreen(loginScreen);

		loginPresenter.register("new_user", "pass");
		verify(loginScreen, times(1)).naviageToPetList();
	}

	@Test
	public void testRegisterWithExistingUsername() {
		LoginScreen loginScreen = mock(LoginScreen.class);
		loginPresenter.attachScreen(loginScreen);

		loginPresenter.register("user", "pass");
		verify(loginScreen, times(1)).showUsernameExistsMessage();
		verify(loginScreen, never()).naviageToPetList();
	}

	@Test
	public void testLogin() {
		LoginScreen loginScreen = mock(LoginScreen.class);
		loginPresenter.attachScreen(loginScreen);

		loginPresenter.login("user", "pass");
		verify(loginScreen, times(1)).naviageToPetList();
	}

	@Test
	public void testLoginWithUnknownUser() {
		LoginScreen loginScreen = mock(LoginScreen.class);
		loginPresenter.attachScreen(loginScreen);

		loginPresenter.login("unknown_user", "pass");
		verify(loginScreen, times(1)).showWrongCredentialsMessage();
		verify(loginScreen, never()).naviageToPetList();
	}

	@After
	public void tearDown() {
		loginPresenter.detachScreen();
	}
}
