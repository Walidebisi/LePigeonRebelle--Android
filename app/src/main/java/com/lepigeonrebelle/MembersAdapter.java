package com.lepigeonrebelle;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.amulyakhare.textdrawable.TextDrawable;
import com.lepigeonrebelle.models.UserGroup;

import java.util.List;

public class MembersAdapter extends ArrayAdapter<UserGroup> {

    // View lookup cache
    private static class ViewHolder {
        ImageView avatar;
        TextView name;
        EditText budget;
    }

    public MembersAdapter(Context context, List<UserGroup> groupMembers) {
        super(context, R.layout.list_item_member, groupMembers);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data item for this position
        UserGroup member = getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view
        ViewHolder viewHolder; // view lookup cache stored in tag
        if (convertView == null) {
            // If there's no view to re-use, inflate a brand new view for row
            viewHolder = new ViewHolder();
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.list_item_member, parent, false);
            viewHolder.avatar = (ImageView) convertView.findViewById(R.id.avatar_member);
            viewHolder.name = (TextView) convertView.findViewById(R.id.member_name);
            viewHolder.budget = (EditText) convertView.findViewById(R.id.edit_budget);
            // Cache the viewHolder object inside the fresh view
            convertView.setTag(viewHolder);
        } else {
            // View is being recycled, retrieve the viewHolder object from tag
            viewHolder = (ViewHolder) convertView.getTag();
        }
        // Populate the data from the data object via the viewHolder object
        // into the template view.
        // Check if user is default user
        String memberName = member.getUser().getName();
        if (member.getUser().isDefaultUser() == 1) {
            viewHolder.name.setText("Me"); //TODO: use String resource
        } else {
            viewHolder.name.setText(memberName);
        }
        int color = getContext().getColor(R.color.colorAccent);
        TextDrawable drawable = TextDrawable.builder().buildRound(String.valueOf(memberName.charAt(0)).toUpperCase(), color);
        viewHolder.avatar.setImageDrawable(drawable);

        // Return the completed view to render on screen
        return convertView;
    }
}
