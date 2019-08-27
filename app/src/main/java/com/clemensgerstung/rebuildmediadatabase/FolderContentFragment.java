package com.clemensgerstung.rebuildmediadatabase;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class FolderContentFragment extends Fragment {

	private String _parentPath;
	private View.OnClickListener _clickListener;

	public void setParentPath(String currentPath) {
		_parentPath = currentPath;
	}

	public String getParentPath() {
		return _parentPath;
	}

	public void setClickListener(View.OnClickListener clickListener) {
		_clickListener = clickListener;
	}

	@Nullable
	@Override
	public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		RecyclerView root = (RecyclerView) inflater.inflate(R.layout.foldercontent, container, false);

		root.setLayoutManager(new LinearLayoutManager(getContext()));
		root.setAdapter(new FolderContentAdapter(getContext(), _parentPath, _clickListener));

		return root;
	}
}
