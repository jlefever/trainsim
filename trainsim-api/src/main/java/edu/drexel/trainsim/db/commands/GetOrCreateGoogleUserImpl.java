package edu.drexel.trainsim.db.commands;

import com.google.gson.Gson;
import com.google.inject.Inject;

import edu.drexel.trainsim.db.models.User;
import org.sql2o.Sql2o;

public class GetOrCreateGoogleUserImpl implements GetOrCreateGoogleUser {
    private final Sql2o db;
    private final Gson gson;

    @Inject
    public GetOrCreateGoogleUserImpl(Sql2o db, Gson gson) {
        this.db = db;
        this.gson = gson;
    }


    @Override
    public int call(User user) {
        String query_sql =
                "SELECT id FROM users WHERE email = :input_email";
        try (var con = this.db.open()) {
            var list = con.createQuery(query_sql)
                    .addParameter("input_email", user.getEmail())
                    .executeAndFetch(Integer.class);
            if (list.isEmpty()) {
                String insert_sql =
                        "INSERT INTO users(email)\n" +
                                "VALUES(:input_email)";
                return (int) con.createQuery(insert_sql, true)
                        .addParameter("input_email", user.getEmail())
                        .executeUpdate()
                        .getKey();
            } else {
                return list.get(0);
            }
        }
    }
}
