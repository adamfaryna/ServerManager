package org.farynaa.servermanager;

import java.io.File;

import javax.sql.DataSource;

import org.h2.jdbcx.JdbcConnectionPool;
import org.h2.jdbcx.JdbcDataSource;
import org.springframework.beans.factory.config.AbstractFactoryBean;

/**
 * @author devil
 *
 */
public class DerbyDataSourceFactoryBean extends
		AbstractFactoryBean<JdbcDataSource> {

	private String url;
	private String databaseName;
	private String user;
	private String password;

	@Override
	protected JdbcDataSource createInstance() throws Exception {
//		File dbfile = new File(DATABASE_DIR);
//		
//		if (!dbfile.exists() || !dbfile.isFile()) {
//			createDb();
//		}
		
		
		
		JdbcDataSource connectionPool = new JdbcDataSource();
				
//				.create(url, user, password);
		
		// embeddedDataSource.setUser(arg0);
		// embeddedDataSource.setPassword(arg0);
		// embeddedDataSource.setDatabaseName(arg0);
		// embeddedDataSource.setCreateDatabase(arg0);

		return connectionPool;
	}

	private void createDb() {
		// TODO Auto-generated method stub
		
	}

	public void setDatabaseName(String databaseName) {
		this.databaseName = databaseName;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public Class<?> getObjectType() {
		return DataSource.class;
	}

}
