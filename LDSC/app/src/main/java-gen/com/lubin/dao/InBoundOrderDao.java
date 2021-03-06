package com.lubin.dao;

import android.database.Cursor;
import android.database.sqlite.SQLiteStatement;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.Property;
import org.greenrobot.greendao.internal.DaoConfig;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.database.DatabaseStatement;

import com.lubin.bean.InBoundOrder;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.
/** 
 * DAO for table "IN_BOUND_ORDER".
*/
public class InBoundOrderDao extends AbstractDao<InBoundOrder, Long> {

    public static final String TABLENAME = "IN_BOUND_ORDER";

    /**
     * Properties of entity InBoundOrder.<br/>
     * Can be used for QueryBuilder and for referencing column names.
     */
    public static class Properties {
        public final static Property Id = new Property(0, Long.class, "id", true, "_id");
        public final static Property Code = new Property(1, String.class, "code", false, "CODE");
        public final static Property OrderId = new Property(2, String.class, "orderId", false, "ORDER_ID");
        public final static Property FormWarehouseName = new Property(3, String.class, "formWarehouseName", false, "FORM_WAREHOUSE_NAME");
        public final static Property ToWarehouseCode = new Property(4, String.class, "toWarehouseCode", false, "TO_WAREHOUSE_CODE");
        public final static Property InboundMode = new Property(5, String.class, "inboundMode", false, "INBOUND_MODE");
        public final static Property Date = new Property(6, String.class, "date", false, "DATE");
        public final static Property DateAtLocation = new Property(7, java.util.Date.class, "dateAtLocation", false, "DATE_AT_LOCATION");
        public final static Property Qty = new Property(8, Integer.class, "qty", false, "QTY");
        public final static Property OperateQty = new Property(9, Integer.class, "operateQty", false, "OPERATE_QTY");
        public final static Property Status = new Property(10, Integer.class, "status", false, "STATUS");
    }

    private DaoSession daoSession;


    public InBoundOrderDao(DaoConfig config) {
        super(config);
    }
    
    public InBoundOrderDao(DaoConfig config, DaoSession daoSession) {
        super(config, daoSession);
        this.daoSession = daoSession;
    }

    /** Creates the underlying database table. */
    public static void createTable(Database db, boolean ifNotExists) {
        String constraint = ifNotExists? "IF NOT EXISTS ": "";
        db.execSQL("CREATE TABLE " + constraint + "\"IN_BOUND_ORDER\" (" + //
                "\"_id\" INTEGER PRIMARY KEY ," + // 0: id
                "\"CODE\" TEXT," + // 1: code
                "\"ORDER_ID\" TEXT," + // 2: orderId
                "\"FORM_WAREHOUSE_NAME\" TEXT," + // 3: formWarehouseName
                "\"TO_WAREHOUSE_CODE\" TEXT," + // 4: toWarehouseCode
                "\"INBOUND_MODE\" TEXT," + // 5: inboundMode
                "\"DATE\" TEXT," + // 6: date
                "\"DATE_AT_LOCATION\" INTEGER," + // 7: dateAtLocation
                "\"QTY\" INTEGER," + // 8: qty
                "\"OPERATE_QTY\" INTEGER," + // 9: operateQty
                "\"STATUS\" INTEGER);"); // 10: status
    }

    /** Drops the underlying database table. */
    public static void dropTable(Database db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "\"IN_BOUND_ORDER\"";
        db.execSQL(sql);
    }

    @Override
    protected final void bindValues(DatabaseStatement stmt, InBoundOrder entity) {
        stmt.clearBindings();
 
        Long id = entity.getId();
        if (id != null) {
            stmt.bindLong(1, id);
        }
 
        String code = entity.getCode();
        if (code != null) {
            stmt.bindString(2, code);
        }
 
        String orderId = entity.getOrderId();
        if (orderId != null) {
            stmt.bindString(3, orderId);
        }
 
        String formWarehouseName = entity.getFormWarehouseName();
        if (formWarehouseName != null) {
            stmt.bindString(4, formWarehouseName);
        }
 
        String toWarehouseCode = entity.getToWarehouseCode();
        if (toWarehouseCode != null) {
            stmt.bindString(5, toWarehouseCode);
        }
 
        String inboundMode = entity.getInboundMode();
        if (inboundMode != null) {
            stmt.bindString(6, inboundMode);
        }
 
        String date = entity.getDate();
        if (date != null) {
            stmt.bindString(7, date);
        }
 
        java.util.Date dateAtLocation = entity.getDateAtLocation();
        if (dateAtLocation != null) {
            stmt.bindLong(8, dateAtLocation.getTime());
        }
 
        Integer qty = entity.getQty();
        if (qty != null) {
            stmt.bindLong(9, qty);
        }
 
        Integer operateQty = entity.getOperateQty();
        if (operateQty != null) {
            stmt.bindLong(10, operateQty);
        }
 
        Integer status = entity.getStatus();
        if (status != null) {
            stmt.bindLong(11, status);
        }
    }

    @Override
    protected final void bindValues(SQLiteStatement stmt, InBoundOrder entity) {
        stmt.clearBindings();
 
        Long id = entity.getId();
        if (id != null) {
            stmt.bindLong(1, id);
        }
 
        String code = entity.getCode();
        if (code != null) {
            stmt.bindString(2, code);
        }
 
        String orderId = entity.getOrderId();
        if (orderId != null) {
            stmt.bindString(3, orderId);
        }
 
        String formWarehouseName = entity.getFormWarehouseName();
        if (formWarehouseName != null) {
            stmt.bindString(4, formWarehouseName);
        }
 
        String toWarehouseCode = entity.getToWarehouseCode();
        if (toWarehouseCode != null) {
            stmt.bindString(5, toWarehouseCode);
        }
 
        String inboundMode = entity.getInboundMode();
        if (inboundMode != null) {
            stmt.bindString(6, inboundMode);
        }
 
        String date = entity.getDate();
        if (date != null) {
            stmt.bindString(7, date);
        }
 
        java.util.Date dateAtLocation = entity.getDateAtLocation();
        if (dateAtLocation != null) {
            stmt.bindLong(8, dateAtLocation.getTime());
        }
 
        Integer qty = entity.getQty();
        if (qty != null) {
            stmt.bindLong(9, qty);
        }
 
        Integer operateQty = entity.getOperateQty();
        if (operateQty != null) {
            stmt.bindLong(10, operateQty);
        }
 
        Integer status = entity.getStatus();
        if (status != null) {
            stmt.bindLong(11, status);
        }
    }

    @Override
    protected final void attachEntity(InBoundOrder entity) {
        super.attachEntity(entity);
        entity.__setDaoSession(daoSession);
    }

    @Override
    public Long readKey(Cursor cursor, int offset) {
        return cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0);
    }    

    @Override
    public InBoundOrder readEntity(Cursor cursor, int offset) {
        InBoundOrder entity = new InBoundOrder( //
            cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0), // id
            cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1), // code
            cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2), // orderId
            cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3), // formWarehouseName
            cursor.isNull(offset + 4) ? null : cursor.getString(offset + 4), // toWarehouseCode
            cursor.isNull(offset + 5) ? null : cursor.getString(offset + 5), // inboundMode
            cursor.isNull(offset + 6) ? null : cursor.getString(offset + 6), // date
            cursor.isNull(offset + 7) ? null : new java.util.Date(cursor.getLong(offset + 7)), // dateAtLocation
            cursor.isNull(offset + 8) ? null : cursor.getInt(offset + 8), // qty
            cursor.isNull(offset + 9) ? null : cursor.getInt(offset + 9), // operateQty
            cursor.isNull(offset + 10) ? null : cursor.getInt(offset + 10) // status
        );
        return entity;
    }
     
    @Override
    public void readEntity(Cursor cursor, InBoundOrder entity, int offset) {
        entity.setId(cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0));
        entity.setCode(cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1));
        entity.setOrderId(cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2));
        entity.setFormWarehouseName(cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3));
        entity.setToWarehouseCode(cursor.isNull(offset + 4) ? null : cursor.getString(offset + 4));
        entity.setInboundMode(cursor.isNull(offset + 5) ? null : cursor.getString(offset + 5));
        entity.setDate(cursor.isNull(offset + 6) ? null : cursor.getString(offset + 6));
        entity.setDateAtLocation(cursor.isNull(offset + 7) ? null : new java.util.Date(cursor.getLong(offset + 7)));
        entity.setQty(cursor.isNull(offset + 8) ? null : cursor.getInt(offset + 8));
        entity.setOperateQty(cursor.isNull(offset + 9) ? null : cursor.getInt(offset + 9));
        entity.setStatus(cursor.isNull(offset + 10) ? null : cursor.getInt(offset + 10));
     }
    
    @Override
    protected final Long updateKeyAfterInsert(InBoundOrder entity, long rowId) {
        entity.setId(rowId);
        return rowId;
    }
    
    @Override
    public Long getKey(InBoundOrder entity) {
        if(entity != null) {
            return entity.getId();
        } else {
            return null;
        }
    }

    @Override
    public boolean hasKey(InBoundOrder entity) {
        return entity.getId() != null;
    }

    @Override
    protected final boolean isEntityUpdateable() {
        return true;
    }
    
}
