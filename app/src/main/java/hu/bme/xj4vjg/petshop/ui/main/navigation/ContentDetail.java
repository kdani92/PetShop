package hu.bme.xj4vjg.petshop.ui.main.navigation;

import android.os.Bundle;

public class ContentDetail {
	private Integer index;
	private String title;
	private String fragmentTag;
	private Bundle fragmentArgs;

	public ContentDetail() {
	}

	public ContentDetail(Integer index, String title, String fragmentTag, Bundle fragmentArgs) {
		this.index = index;
		this.fragmentTag = fragmentTag;
		this.title = title;
		this.fragmentArgs = fragmentArgs;
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

	public Bundle getFragmentArgs() {
		return fragmentArgs;
	}

	public void setFragmentArgs(Bundle fragmentArgs) {
		this.fragmentArgs = fragmentArgs;
	}
}
