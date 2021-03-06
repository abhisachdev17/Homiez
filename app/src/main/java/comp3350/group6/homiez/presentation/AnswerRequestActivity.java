package comp3350.group6.homiez.presentation;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.Nullable;

import comp3350.group6.homiez.R;
import comp3350.group6.homiez.application.Shared.QueryResult;
import comp3350.group6.homiez.business.AccessMatches;
import comp3350.group6.homiez.business.AccessPostings;
import comp3350.group6.homiez.business.AccessRequests;
import comp3350.group6.homiez.business.AccessUser;
import comp3350.group6.homiez.business.Matching;
import comp3350.group6.homiez.objects.Posting;
import comp3350.group6.homiez.objects.User;

public class AnswerRequestActivity extends Activity {
    private AccessRequests accessRequests ;
    private AccessMatches accessMatches ;
    private String requestUserId;
    private String mainUser;
    private String postingId;
    private AccessPostings accessPostings;
    private AccessUser accessUser;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        accessRequests = new AccessRequests();
        accessPostings = new AccessPostings();
        accessUser = new AccessUser();
        accessMatches = new AccessMatches();

        super.onCreate(savedInstanceState);
        setContentView(R.layout.request);

        Bundle b = getIntent().getExtras();
        requestUserId = b.getString("requestUserId");
        User u = accessUser.getUser(requestUserId);
        postingId = b.getString("postingId");
        mainUser = b.getString("userID");
        Posting p = accessPostings.getPostingById(postingId);
        TextView editID = (TextView)findViewById(R.id.userInfoText);
        editID.setText("   " + u.getName());
        editID = (TextView) findViewById(R.id.PostingInfoText);
        editID.setText(" Sent a Match Request for:  " + p.getTitle());
    }

    public void openProfile(View view) {
        Intent startIntent = new Intent(AnswerRequestActivity.this, PublicProfileActivity.class);
        Bundle bundle = getIntent().getExtras();
        bundle.putString("profileID", bundle.getString("requestUserId"));
        startIntent.putExtras(bundle);
        startActivity(startIntent);

    }

    public void acceptRequest (View view) {
        QueryResult result = Matching.AcceptRequest(accessRequests,accessMatches,requestUserId,postingId);
        if (result == QueryResult.FAILURE) {
           Messages.fatalError(this, "Failure while accepting requests " );
        }
        else {
            finish();
        }
    }
    public void declineRequest (View view) {
        QueryResult result = Matching.DeclineRequest(accessRequests,requestUserId,postingId);
        if (result == QueryResult.FAILURE) {
            Messages.fatalError(this, "Failure while declining requests ");
        }
        else {
            finish();
        }
    }
}
