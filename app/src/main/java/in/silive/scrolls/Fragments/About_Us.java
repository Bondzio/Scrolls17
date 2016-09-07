package in.silive.scrolls.Fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ramotion.foldingcell.FoldingCell;

import in.silive.scrolls.R;

/**
 * Created by akriti on 26/8/16.
 */
public class About_Us extends Fragment {
    FoldingCell fc,fcell,folding_cell_tech,folding_cell_stud_coor,folding_cell_web,folding_cell_app,folding_cell_organising_head;

    public About_Us() {
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_about_us, container, false);
         fc = (FoldingCell)view.findViewById(R.id.folding_cell_patron);
        fc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fc.toggle(false);
            }
        });
         fcell = (FoldingCell)view.findViewById(R.id.folding_cell_advisry_head);
        fcell.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fcell.toggle(false);
            }
        });
        folding_cell_app = (FoldingCell)view.findViewById(R.id.folding_cell_app);
        folding_cell_app.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                folding_cell_app.toggle(false);
            }
        });
        folding_cell_organising_head = (FoldingCell)view.findViewById(R.id.folding_cell_organising_head);
        folding_cell_organising_head.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                folding_cell_organising_head.toggle(false);
            }
        });
        folding_cell_stud_coor = (FoldingCell)view.findViewById(R.id.folding_cell_stud_coor);
        folding_cell_stud_coor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                folding_cell_stud_coor.toggle(false);
            }
        });
        folding_cell_tech = (FoldingCell)view.findViewById(R.id.folding_cell_tech);
        folding_cell_tech.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                folding_cell_tech.toggle(false);
            }
        });
        folding_cell_web = (FoldingCell)view.findViewById(R.id.folding_cell_web);
        folding_cell_web.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                folding_cell_web.toggle(false);
            }
        });

        return view;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

}
