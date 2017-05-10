package com.tiger.curious.guide.database;

import android.database.Cursor;
import android.database.sqlite.SQLiteStatement;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.Property;
import org.greenrobot.greendao.internal.DaoConfig;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.database.DatabaseStatement;

import com.tiger.curious.guide.model.Company;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.
/** 
 * DAO for table "COMPANY".
*/
public class CompanyDao extends AbstractDao<Company, Long> {

    public static final String TABLENAME = "COMPANY";

    /**
     * Properties of entity Company.<br/>
     * Can be used for QueryBuilder and for referencing column names.
     */
    public static class Properties {
        public final static Property Id = new Property(0, long.class, "id", true, "_id");
        public final static Property Name = new Property(1, String.class, "name", false, "NAME");
        public final static Property EnglishName = new Property(2, String.class, "englishName", false, "ENGLISH_NAME");
        public final static Property RoomNumber = new Property(3, int.class, "roomNumber", false, "ROOM_NUMBER");
        public final static Property Floor = new Property(4, int.class, "floor", false, "FLOOR");
    }

    private DaoSession daoSession;


    public CompanyDao(DaoConfig config) {
        super(config);
    }
    
    public CompanyDao(DaoConfig config, DaoSession daoSession) {
        super(config, daoSession);
        this.daoSession = daoSession;
    }

    /** Creates the underlying database table. */
    public static void createTable(Database db, boolean ifNotExists) {
        String constraint = ifNotExists? "IF NOT EXISTS ": "";
        db.execSQL("CREATE TABLE " + constraint + "\"COMPANY\" (" + //
                "\"_id\" INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL ," + // 0: id
                "\"NAME\" TEXT," + // 1: name
                "\"ENGLISH_NAME\" TEXT," + // 2: englishName
                "\"ROOM_NUMBER\" INTEGER NOT NULL ," + // 3: roomNumber
                "\"FLOOR\" INTEGER NOT NULL );"); // 4: floor
    }

    /** Drops the underlying database table. */
    public static void dropTable(Database db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "\"COMPANY\"";
        db.execSQL(sql);
    }

    @Override
    protected final void bindValues(DatabaseStatement stmt, Company entity) {
        stmt.clearBindings();
        stmt.bindLong(1, entity.getId());
 
        String name = entity.getName();
        if (name != null) {
            stmt.bindString(2, name);
        }
 
        String englishName = entity.getEnglishName();
        if (englishName != null) {
            stmt.bindString(3, englishName);
        }
        stmt.bindLong(4, entity.getRoomNumber());
        stmt.bindLong(5, entity.getFloor());
    }

    @Override
    protected final void bindValues(SQLiteStatement stmt, Company entity) {
        stmt.clearBindings();
        stmt.bindLong(1, entity.getId());
 
        String name = entity.getName();
        if (name != null) {
            stmt.bindString(2, name);
        }
 
        String englishName = entity.getEnglishName();
        if (englishName != null) {
            stmt.bindString(3, englishName);
        }
        stmt.bindLong(4, entity.getRoomNumber());
        stmt.bindLong(5, entity.getFloor());
    }

    @Override
    protected final void attachEntity(Company entity) {
        super.attachEntity(entity);
        entity.__setDaoSession(daoSession);
    }

    @Override
    public Long readKey(Cursor cursor, int offset) {
        return cursor.getLong(offset + 0);
    }    

    @Override
    public Company readEntity(Cursor cursor, int offset) {
        Company entity = new Company( //
            cursor.getLong(offset + 0), // id
            cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1), // name
            cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2), // englishName
            cursor.getInt(offset + 3), // roomNumber
            cursor.getInt(offset + 4) // floor
        );
        return entity;
    }
     
    @Override
    public void readEntity(Cursor cursor, Company entity, int offset) {
        entity.setId(cursor.getLong(offset + 0));
        entity.setName(cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1));
        entity.setEnglishName(cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2));
        entity.setRoomNumber(cursor.getInt(offset + 3));
        entity.setFloor(cursor.getInt(offset + 4));
     }
    
    @Override
    protected final Long updateKeyAfterInsert(Company entity, long rowId) {
        entity.setId(rowId);
        return rowId;
    }
    
    @Override
    public Long getKey(Company entity) {
        if(entity != null) {
            return entity.getId();
        } else {
            return null;
        }
    }

    @Override
    public boolean hasKey(Company entity) {
        throw new UnsupportedOperationException("Unsupported for entities with a non-null key");
    }

    @Override
    protected final boolean isEntityUpdateable() {
        return true;
    }
    
}
