package io.joydeep.banker.core.permissions;

import com.github.davidmoten.rx.jdbc.Database;
import com.github.davidmoten.rx.jdbc.annotations.Column;
import com.github.davidmoten.rx.jdbc.annotations.Query;
import io.joydeep.banker.core.database.SQLiteConnection;

import java.sql.Connection;

@Query("SELECT permission_name, permission_level FROM permissions")
public interface Permission {
    @Column
    String permissionName();

    @Column
    int permissionLevel();
}
