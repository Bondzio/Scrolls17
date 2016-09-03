package in.silive.scrolls.Fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import java.util.regex.Pattern;

import in.silive.scrolls.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class QueryUs extends Fragment {
    //UI-Elements
    EditText email_query, message_query;
    Button submit_query;

    String usr_mail, usr_msg;


    public QueryUs() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View query_view = inflater.inflate(R.layout.fragment_query_us, container, false);
        email_query = (EditText) query_view.findViewById(R.id.email_query);
        message_query = (EditText) query_view.findViewById(R.id.message_query);
        submit_query = (Button) query_view.findViewById(R.id.submit_query);
        submit_query.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getUserQuery();

            }
        });
        return query_view;
    }

    public void getUserQuery() {
        usr_mail = email_query.getText().toString();
        if (!(Pattern.matches("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,}$", usr_mail))) {
            //To do.. dialog for invalid mail
            DialogInvalidMail dialogInvalidMail = new DialogInvalidMail();
            dialogInvalidMail.show(getChildFragmentManager(), "Invalid Mail");
        }


        usr_msg = message_query.getText().toString();
        if (usr_msg.length() == 0) {
            DialogEmptyQuery dialogEmptyQuery = new DialogEmptyQuery();
            dialogEmptyQuery.show(getChildFragmentManager(), "Empty query");
        }

    }

}