package strosoft.app.util;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.apache.ibatis.session.SqlSession;

import strosoft.app.common.MyBatisManager;


public class MySqlHelper {
	// 定义要使用的变量
	private static Connection conn = null;
	private static PreparedStatement ps = null;
	private static ResultSet rs = null;
	private static CallableStatement cs = null;

	private static String driver = "";
	private static String url = "";
	private static String user = "";
	private static String password = "";

	private static Properties pp = null;
	private static FileInputStream fis = null;

	public static Connection getConn() {
		return conn;
	}

	public static PreparedStatement getPs() {
		return ps;
	}

	public static ResultSet getRs() {
		return rs;
	}

	public static CallableStatement getCs() {
		return cs;
	}

	// 加载驱动，只需要一次
	static {
		try {
			// 从配置文件dbinfo.properties中读取配置信息
			pp = new Properties();
			fis = new FileInputStream("config.properties");
			pp.load(fis);
			driver = pp.getProperty("driver");
			url = pp.getProperty("url");
			user = pp.getProperty("user");
			password = pp.getProperty("password");

			Class.forName(driver);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (fis != null)
				try {
					fis.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			fis = null;

		}
	}

	// 得到连接
	public static Connection getConnection() {
		try {
			conn = DriverManager.getConnection(url, user, password);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return conn;
	}

	// 处理多个update/delete/insert
	public static void executeUpdateMultiParams(String[] sql,
			Object[][] parameters) {
		try {
			// 获得连接
			conn = getConnection();
			// 可能传多条sql语句
			conn.setAutoCommit(false);
			for (int i = 0; i < sql.length; i++) {
				if (parameters[i] != null) {
					ps = conn.prepareStatement(sql[i]);
					for (int j = 0; j < parameters[i].length; j++) {
						Object parameter = parameters[i][j];
						if (parameter instanceof Integer) {
							int value = ((Integer) parameter).intValue();
							ps.setInt(j + 1, value);
						} else if (parameter instanceof String) {
							String s = (String) parameter;
							ps.setString(j + 1, s);
						} else if (parameter instanceof Double) {
							double d = ((Double) parameter).doubleValue();
							ps.setDouble(j + 1, d);
						} else if (parameter instanceof Float) {
							float f = ((Float) parameter).floatValue();
							ps.setFloat(j + 1, f);
						} else if (parameter instanceof Long) {
							long l = ((Long) parameter).longValue();
							ps.setLong(j + 1, l);
						} else if (parameter instanceof Boolean) {
							boolean b = ((Boolean) parameter).booleanValue();
							ps.setBoolean(j + 1, b);
						} else if (parameter instanceof Date) {
							Date d = (Date) parameter;
							ps.setDate(j + 1, d);
						}
					}
				}

				ps.executeUpdate();
			}
			conn.commit();
		} catch (Exception e) {
			e.printStackTrace();
			try {
				conn.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			throw new RuntimeException(e.getMessage());
		} finally {
			// 关闭资源
			close(rs, ps, conn);
		}
	}

	// update/delete/insert
	// sql格式:UPDATE tablename SET columnn = ? WHERE column = ?
	public static int executeUpdate(String sql, String[] parameters) {
		try {
			// 1.创建一个ps
			conn = getConnection();
			ps = conn.prepareStatement(sql);
			// 给？赋值
			if (parameters != null)
				for (int i = 0; i < parameters.length; i++) {
					Object parameter = parameters[i];
					if (parameter instanceof Integer) {
						int value = ((Integer) parameter).intValue();
						ps.setInt(i + 1, value);
					} else if (parameter instanceof String) {
						String s = (String) parameter;
						ps.setString(i + 1, s);
					} else if (parameter instanceof Double) {
						double d = ((Double) parameter).doubleValue();
						ps.setDouble(i + 1, d);
					} else if (parameter instanceof Float) {
						float f = ((Float) parameter).floatValue();
						ps.setFloat(i + 1, f);
					} else if (parameter instanceof Long) {
						long l = ((Long) parameter).longValue();
						ps.setLong(i + 1, l);
					} else if (parameter instanceof Boolean) {
						boolean b = ((Boolean) parameter).booleanValue();
						ps.setBoolean(i + 1, b);
					} else if (parameter instanceof Date) {
						Date d = (Date) parameter;
						ps.setDate(i + 1, d);
					}
				}
			// 执行
			return ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();// 开发阶段
			throw new RuntimeException(e.getMessage());
		} finally {
			// 关闭资源
			close(rs, ps, conn);
		}
	}

	// select
	public static ResultSet executeQuery(String sql, Object[] parameters) {
		ResultSet rs = null;
		try {
			conn = getConnection();
			ps = conn.prepareStatement(sql);
			if (parameters != null) {
				for (int i = 0; i < parameters.length; i++) {
					Object parameter = parameters[i];
					if (parameter instanceof Integer) {
						int value = ((Integer) parameter).intValue();
						ps.setInt(i + 1, value);
					} else if (parameter instanceof String) {
						String s = (String) parameter;
						ps.setString(i + 1, s);
					} else if (parameter instanceof Double) {
						double d = ((Double) parameter).doubleValue();
						ps.setDouble(i + 1, d);
					} else if (parameter instanceof Float) {
						float f = ((Float) parameter).floatValue();
						ps.setFloat(i + 1, f);
					} else if (parameter instanceof Long) {
						long l = ((Long) parameter).longValue();
						ps.setLong(i + 1, l);
					} else if (parameter instanceof Boolean) {
						boolean b = ((Boolean) parameter).booleanValue();
						ps.setBoolean(i + 1, b);
					} else if (parameter instanceof Date) {
						Date d = (Date) parameter;
						ps.setDate(i + 1, d);
					}
				}
			}
			rs = ps.executeQuery();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException(e.getMessage());
		} finally {

		}
		return rs;
	}

	// 调用无返回值存储过程
	// 格式： call procedureName(parameters list)
	public static void callProc(String sql, String[] parameters) {
		try {
			conn = getConnection();
			cs = conn.prepareCall(sql);
			// 给？赋值
			if (parameters != null) {
				for (int i = 0; i < parameters.length; i++)
					cs.setObject(i + 1, parameters[i]);
			}
			cs.execute();
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e.getMessage());
		} finally {
			// 关闭资源
			close(rs, cs, conn);
		}
	}

	// 调用带有输入参数且有返回值的存储过程
	public static CallableStatement callProcInput(String sql,
			String[] inparameters) {
		try {
			conn = getConnection();
			cs = conn.prepareCall(sql);
			if (inparameters != null)
				for (int i = 0; i < inparameters.length; i++)
					cs.setObject(i + 1, inparameters[i]);
			cs.execute();
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e.getMessage());
		} finally {

		}
		return cs;
	}

	// 调用有返回值的存储过程
	public static CallableStatement callProcOutput(String sql,
			Integer[] outparameters) {
		try {
			conn = getConnection();
			cs = conn.prepareCall(sql);
			// 给out参数赋值
			if (outparameters != null)
				for (int i = 0; i < outparameters.length; i++)
					cs.registerOutParameter(i + 1, outparameters[i]);
			cs.execute();
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e.getMessage());
		} finally {

		}
		return cs;
	}

	public static void close(ResultSet rs, Statement ps, Connection conn) {
		if (rs != null)
			try {
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		rs = null;
		if (ps != null)
			try {
				ps.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		ps = null;
		if (conn != null)
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		conn = null;
	}
}
