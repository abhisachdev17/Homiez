package comp3350.group6.homiez.persistence;

import java.util.List;

import comp3350.group6.homiez.objects.Match;
import comp3350.group6.homiez.objects.Posting;
import comp3350.group6.homiez.objects.Request;
import comp3350.group6.homiez.objects.User;

public interface DataAccess {
    void open(String string);

    void close();

    //USER STUFF
    String getUser(User user);

    String getAllUsers(List<User> userList);

    String getUserById(User user, String userId);

    String getUsersByAge(List<User> userList, int minAge, int maxAge);

    String insertUser(User user);

    String updateUser(User user);

    String deleteUser();


    //POSTING STUFF
    String getAllPostings(List<Posting> postingList);

    String getPostingById(String postingId);

    String getPosting(Posting posting);

    String getPostingBysByUser(List<Posting> postingsList, User user);

    String insertPosting(Posting posting);

    String deletePosting(Posting posting);

    String updatePosting(Posting p);


    //MATCH STUFF
    String getMatchesForUser(List<Match> matchList, String userId);

    String getMatchesForPosting(List<Match> matchList, String postingId);

    String insertMatch(Match m);

    String deleteMatch(Match m);


    //REQUEST STUFF
    String getRequests(List<Request> requests, String postingId);

    String insertRequest(Request request);

    String deleteRequest(Request request);
}
