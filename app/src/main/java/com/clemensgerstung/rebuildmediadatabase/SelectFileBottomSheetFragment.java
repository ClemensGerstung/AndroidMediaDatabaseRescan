package com.clemensgerstung.rebuildmediadatabase;

import android.app.Dialog;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatButton;
import androidx.viewpager2.widget.ViewPager2;

import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

import java.io.File;
import java.util.Stack;

public class SelectFileBottomSheetFragment extends BottomSheetDialogFragment {

	private SelectFileFragmentAdapter _adapter;

	private static class OnCancelClickHandler implements View.OnClickListener {
		private SelectFileBottomSheetFragment _parent;

		OnCancelClickHandler(SelectFileBottomSheetFragment parent) {
			_parent = parent;
		}

		@Override
		public void onClick(View view) {
			_parent.dismiss();
		}
	}

	private static class OnSelectClickHandler implements View.OnClickListener {
		private SelectFileBottomSheetFragment _parent;

		public OnSelectClickHandler(SelectFileBottomSheetFragment parent) {
			_parent = parent;
		}

		@Override
		public void onClick(View view) {

			_parent.dismiss();
		}
	}

	private static class OnFolderSelectedClickHandler implements View.OnClickListener {

		private SelectFileBottomSheetFragment _parent;

		public OnFolderSelectedClickHandler(SelectFileBottomSheetFragment parent) {
			_parent = parent;
		}

		@Override
		public void onClick(View view) {
			String child = (String) view.getTag();
			if(child.equals("..")) {
				_parent.previousPage();
			} else {
				_parent.nextPage(child);
			}
		}
	}

	private ViewPager2 _viewPager;
	private TextView _textView;
	private File _file = new File("/storage/emulated/0");
	private Stack<Integer> _pages = new Stack<>();

	public String getCurrentPath() {
		return _file.getPath();
	}

	private void nextPage(String child) {
		_file = new File(_file, child);
		int index = _adapter.addSubFolder();
		_pages.push(_viewPager.getCurrentItem());
		_viewPager.setCurrentItem(index);
		_textView.setText(getCurrentPath());
	}

	private void previousPage() {
		if(!_file.getPath().equals("/storage/emulated/0")) {
			_file = _file.getParentFile();
			int index = _pages.pop();
			_viewPager.setCurrentItem(index);
			_textView.setText(getCurrentPath());
		}
	}

	@Override
	public void setupDialog(@NonNull Dialog dialog, int style) {
		super.setupDialog(dialog, style);

		View root = View.inflate(getContext(), R.layout.fileselector, null);
		AppCompatButton cancel = root.findViewById(R.id.select_file_cancel);
		cancel.setOnClickListener(new OnCancelClickHandler(this));

		_viewPager = root.findViewById(R.id.viewpager);
		_adapter = new SelectFileFragmentAdapter(this, new OnFolderSelectedClickHandler(this));

		_viewPager.setAdapter(_adapter);
		_viewPager.setUserInputEnabled(false);
		_viewPager.setOffscreenPageLimit(5);
		_viewPager.setCurrentItem(0);

		_textView = root.findViewById(R.id.select_file_path);
		_textView.setText(getCurrentPath());

		dialog.setContentView(root);
	}
}
