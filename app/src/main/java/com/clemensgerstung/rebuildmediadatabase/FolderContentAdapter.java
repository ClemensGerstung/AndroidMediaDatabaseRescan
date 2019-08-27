package com.clemensgerstung.rebuildmediadatabase;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;

public class FolderContentAdapter extends RecyclerView.Adapter<FolderContentAdapter.FolderContentViewHolder> {

	public static class FolderContentViewHolder extends RecyclerView.ViewHolder {
		public FolderContentViewHolder(@NonNull View itemView) {
			super(itemView);
		}

		void setText(String text) {
			TextView view = itemView.findViewById(R.id.folderentry_name);
			view.setText(text);
			itemView.setTag(text);
		}
	}

	private String _path;
	private Context _context;
	private File _file;
	private ArrayList<String> _children = new ArrayList<>();
	private View.OnClickListener _clickListener;

	public FolderContentAdapter(Context context, String path, View.OnClickListener onItemClickListener) {
		_context = context;
		_path = path;
		_file = new File(path);
		_clickListener = onItemClickListener;

		if(!path.equals("/storage/emulated/0")) {
			_children.add("..");
		}

		if(_file.isDirectory() && _file.exists()) {
			File[] files = _file.listFiles();

			if(files == null) {
				return;
			}

			for(File file : files) {
				if(file.isDirectory()) {
					_children.add(file.getName());
				}
			}
		}

		Collections.sort(_children);
	}

	@NonNull
	@Override
	public FolderContentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
		View v = LayoutInflater.from(_context).inflate(R.layout.folderentry, parent, false);
		v.setClickable(true);
		v.setOnClickListener(_clickListener);
		return new FolderContentViewHolder(v);
	}

	@Override
	public void onBindViewHolder(@NonNull FolderContentViewHolder holder, int position) {
		holder.setText(_children.get(position));
	}

	@Override
	public int getItemCount() {
		return _children.size();
	}
}
