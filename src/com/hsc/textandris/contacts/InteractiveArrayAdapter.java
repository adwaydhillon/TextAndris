package com.hsc.textandris.contacts;

import java.util.List;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import com.hsc.textandris.R;

public class InteractiveArrayAdapter extends ArrayAdapter<Model> {

	private List<Model> list = null;
	
	public List<Model> getList() {
		return list;
	}


	private final Activity context;

	public InteractiveArrayAdapter(Activity context, List<Model> list) {
		super(context, R.layout.contactlist, list);
		this.context = context;
		this.list = list;
	}

	static class ViewHolder {
		private TextView text;
		private CheckBox checkbox;
	}
	

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		View view = null;

		if (convertView == null) {
			LayoutInflater inflator = context.getLayoutInflater();
			view = inflator.inflate(R.layout.contactlist, null);
			final ViewHolder viewHolder = new ViewHolder();
			viewHolder.text = (TextView) view.findViewById(R.id.label);

			viewHolder.checkbox = (CheckBox) view.findViewById(R.id.check);
			viewHolder.checkbox
					.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
						@Override
						public void onCheckedChanged(CompoundButton buttonView,
								boolean isChecked) {
							Model element = (Model) viewHolder.checkbox
									.getTag();
							element.setSelected(buttonView.isChecked());
						}
					});

			view.setTag(viewHolder);
			viewHolder.checkbox.setTag(list.get(position));
		} else {
			view = convertView;
			((ViewHolder) view.getTag()).checkbox.setTag(list.get(position));
		}

		ViewHolder holder = (ViewHolder) view.getTag();
		holder.text.setText(list.get(position).getName());

		holder.checkbox.setChecked(list.get(position).isSelected());

		return view;
	}
}
