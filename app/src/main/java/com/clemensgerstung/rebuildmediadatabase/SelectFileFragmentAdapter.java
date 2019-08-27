package com.clemensgerstung.rebuildmediadatabase;

import android.view.View;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import java.util.LinkedHashMap;

public class SelectFileFragmentAdapter extends FragmentStateAdapter {

	private static final String TAG = SelectFileFragmentAdapter.class.getSimpleName();
	private SelectFileBottomSheetFragment _parent;
	private LinkedHashMap<String, Fragment> _fragments;
	private View.OnClickListener _clickListener;

	public SelectFileFragmentAdapter(@NonNull SelectFileBottomSheetFragment parent, View.OnClickListener clickListener) {
		super(parent);
		_parent = parent;
		_clickListener = clickListener;
		_fragments = new LinkedHashMap<>();

		addSubFolder();
	}

	public int addSubFolder() {
		String path = _parent.getCurrentPath();

		if(!_fragments.containsKey(path)) {
			FolderContentFragment fragment = new FolderContentFragment();
			fragment.setParentPath(path);
			fragment.setClickListener(_clickListener);

			_fragments.put(path, fragment);
			notifyDataSetChanged();

			return _fragments.size() - 1;
		}

		int index = 0;
		for(String key : _fragments.keySet()) {
			if(path.equals(key)) {
				return index;
			}
			index++;
		}

		return -1;
	}

	@NonNull
	@Override
	public Fragment createFragment(int position) {
		return _fragments.get(_parent.getCurrentPath());
	}

	@Override
	public int getItemCount() {
		return _fragments.size();
	}
}
