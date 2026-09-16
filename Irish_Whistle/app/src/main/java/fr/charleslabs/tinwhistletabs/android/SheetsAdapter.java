package fr.charleslabs.tinwhistletabs.android;

import android.content.Context;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import fr.charleslabs.tinwhistletabs.R;
import fr.charleslabs.tinwhistletabs.music.MusicSheet;

public class SheetsAdapter extends BaseAdapter implements Filterable {
    private static final String FAVORITES_PREFERENCES = "favorite_sheets";

    private final List<MusicSheet> sheets;
    private List<MusicSheet> sheetsFiltered;
    private final Context context;
    private final View noResult;
    private final SharedPreferences favoritePreferences;
    private String currentQuery = "";

    public SheetsAdapter(Context context, List<MusicSheet> sheets, View noResult) {
        this.context = context;
        this.noResult = noResult;
        this.favoritePreferences = context.getSharedPreferences(
                FAVORITES_PREFERENCES, Context.MODE_PRIVATE);
        this.sheets = new ArrayList<>(sheets);
        sortFavoritesFirst(this.sheets);
        this.sheetsFiltered = new ArrayList<>(this.sheets);
    }

    @Override
    public int getCount() {
        return sheetsFiltered.size();
    }

    @Override
    public Object getItem(int position) {
        return sheetsFiltered.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, @NonNull ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(context)
                    .inflate(R.layout.list_item_layout, parent, false);
        }

        ComponentViewHolder viewHolder = (ComponentViewHolder) convertView.getTag();
        if (viewHolder == null) {
            viewHolder = new ComponentViewHolder();
            viewHolder.favoriteToggle = convertView.findViewById(
                    R.id.mainActivity_favoriteToggle);
            viewHolder.sheetName = convertView.findViewById(R.id.mainActivity_SheetName);
            viewHolder.sheetDetails = convertView.findViewById(
                    R.id.mainActivity_SheetDetails);
            viewHolder.sheetImage = convertView.findViewById(
                    R.id.mainActivity_sheetPicture);
            convertView.setTag(viewHolder);
        }

        final MusicSheet sheet = sheetsFiltered.get(position);

        viewHolder.favoriteToggle.setOnCheckedChangeListener(null);
        viewHolder.favoriteToggle.setChecked(isFavorite(sheet));
        viewHolder.favoriteToggle.setOnCheckedChangeListener(
                new CompoundButton.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(CompoundButton buttonView,
                                                 boolean isChecked) {
                        favoritePreferences.edit()
                                .putBoolean(favoriteKey(sheet), isChecked)
                                .apply();
                        sortFavoritesFirst(sheets);
                        refreshFilteredSheets();
                    }
                });

        viewHolder.sheetName.setText(sheet.getTitle());
        viewHolder.sheetDetails.setText(context.getResources().getString(
                R.string.mainActivity_sheetDetails_string,
                sheet.getType(), sheet.getWhistle()));

        switch (sheet.getType()) {
            case "Reel":
                viewHolder.sheetImage.setImageResource(R.drawable.reel);
                break;
            case "Jig":
                viewHolder.sheetImage.setImageResource(R.drawable.jig);
                break;
            case "Slip Jig":
                viewHolder.sheetImage.setImageResource(R.drawable.slipjig);
                break;
            case "Slide":
                viewHolder.sheetImage.setImageResource(R.drawable.slide);
                break;
            case "Polka":
                viewHolder.sheetImage.setImageResource(R.drawable.polka);
                break;
            case "March":
                viewHolder.sheetImage.setImageResource(R.drawable.march);
                break;
            case "Hornpipe":
                viewHolder.sheetImage.setImageResource(R.drawable.hornpipe);
                break;
            case "Song":
                viewHolder.sheetImage.setImageResource(R.drawable.song);
                break;
            case "Waltz":
                viewHolder.sheetImage.setImageResource(R.drawable.waltz);
                break;
            case "Misc.":
            default:
                viewHolder.sheetImage.setImageResource(R.drawable.misc);
                break;
        }

        return convertView;
    }

    private boolean isFavorite(MusicSheet sheet) {
        return favoritePreferences.getBoolean(favoriteKey(sheet), false);
    }

    private String favoriteKey(MusicSheet sheet) {
        return sheet.getFile();
    }

    private void sortFavoritesFirst(List<MusicSheet> list) {
        Collections.sort(list, new Comparator<MusicSheet>() {
            @Override
            public int compare(MusicSheet left, MusicSheet right) {
                boolean leftFavorite = isFavorite(left);
                boolean rightFavorite = isFavorite(right);
                if (leftFavorite == rightFavorite) {
                    return 0;
                }
                return leftFavorite ? -1 : 1;
            }
        });
    }

    private List<MusicSheet> filterSheets(String query) {
        if (query.isEmpty()) {
            return new ArrayList<>(sheets);
        }

        List<MusicSheet> results = new ArrayList<>();
        for (MusicSheet sheet : sheets) {
            if (sheet.filter(query)) {
                results.add(sheet);
            }
        }
        return results;
    }

    private void refreshFilteredSheets() {
        sheetsFiltered = filterSheets(currentQuery);
        notifyDataSetChanged();
        updateNoResultVisibility();
    }

    private void updateNoResultVisibility() {
        if (noResult != null) {
            noResult.setVisibility(sheetsFiltered.isEmpty()
                    ? View.VISIBLE : View.GONE);
        }
    }

    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                String query = constraint == null
                        ? "" : constraint.toString().toLowerCase();
                List<MusicSheet> results = filterSheets(query);

                FilterResults filterResults = new FilterResults();
                filterResults.count = results.size();
                filterResults.values = results;
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence constraint,
                                          FilterResults results) {
                currentQuery = constraint == null
                        ? "" : constraint.toString().toLowerCase();
                sheetsFiltered = (List<MusicSheet>) results.values;
                notifyDataSetChanged();
                updateNoResultVisibility();
            }
        };
    }

    private static class ComponentViewHolder {
        CheckBox favoriteToggle;
        TextView sheetName;
        TextView sheetDetails;
        ImageView sheetImage;
    }
}
