package com.tiger.curious.guide.database;

import java.util.Map;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.AbstractDaoSession;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.identityscope.IdentityScopeType;
import org.greenrobot.greendao.internal.DaoConfig;

import com.tiger.curious.guide.model.Company;

import com.tiger.curious.guide.database.CompanyDao;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.

/**
 * {@inheritDoc}
 * 
 * @see org.greenrobot.greendao.AbstractDaoSession
 */
public class DaoSession extends AbstractDaoSession {

    private final DaoConfig companyDaoConfig;

    private final CompanyDao companyDao;

    public DaoSession(Database db, IdentityScopeType type, Map<Class<? extends AbstractDao<?, ?>>, DaoConfig>
            daoConfigMap) {
        super(db);

        companyDaoConfig = daoConfigMap.get(CompanyDao.class).clone();
        companyDaoConfig.initIdentityScope(type);

        companyDao = new CompanyDao(companyDaoConfig, this);

        registerDao(Company.class, companyDao);
    }
    
    public void clear() {
        companyDaoConfig.clearIdentityScope();
    }

    public CompanyDao getCompanyDao() {
        return companyDao;
    }

}
