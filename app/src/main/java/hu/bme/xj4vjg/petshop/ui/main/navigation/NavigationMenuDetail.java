package hu.bme.xj4vjg.petshop.ui.main.navigation;

public class NavigationMenuDetail {
	private Integer index;
	private String title;
	private String fragmentTag;

	public NavigationMenuDetail() {
	}

	public NavigationMenuDetail(Integer index, String fragmentTag, String title) {
		this.index = index;
		this.fragmentTag = fragmentTag;
		this.title = title;
	}

	public Integer getIndex() {
		return index;
	}

	public void setIndex(Integer index) {
		this.index = index;
	}

	public String getFragmentTag() {
		return fragmentTag;
	}

	public void setFragmentTag(String fragmentTag) {
		this.fragmentTag = fragmentTag;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
}
