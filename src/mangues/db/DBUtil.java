package mangues.db;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import mangues.model.Student;


/**
 * 
 * @ClassName: DBUtil 
 * @Description: 数据处理类 
 * @author 许涛 
 * @date 2015-7-20
 */
public class DBUtil {
	 // 驱动程序名
    private static String driver = "oracle.jdbc.driver.OracleDriver";
    // URL指向要访问的数据库名scutcs
    private static String url = "jdbc:oracle:thin:@172.18.0.113:1521/orcl";
    // 配置时的用户名
    private static String user = "xutao"; 
    // 配置时的密码
    private static String password = "qq106472";
    private static Connection conn = null ;
    static{
            try { 
             // 加载驱动程序
             Class.forName(driver);
             // 连续数据库
              conn = DriverManager.getConnection(url, user, password);
             }catch (Exception e) {
				System.err.println("出错");
			}
    }
    /*
     * 查数据
     */
    public static List<Student> getList(String sql,Object param[]){
    	Student student = new Student();
    	List<Student> list = new ArrayList<Student>();
    	PreparedStatement pst = null;
    	ResultSet rs = null;
    	try {
    		pst = conn.prepareStatement(sql);
			for(int i = 0;i<param.length;i++){
				pst.setObject(i+1, param[i]);
			}
			rs = pst.executeQuery();
		    while (rs.next()) {
		    	student.setSno(rs.getString(1));
		    	student.setSname(rs.getString(2));
		    	student.setSage(rs.getInt(3));
		    	student.setSno(rs.getString(4));
				list.add(student);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			try {
				rs.close();
				pst.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
		}
		return list;
    	
    }
    
    /*
     * 查数据
     */
    public static List<Student> getList(String sql){
    	
    	List<Student> list = new ArrayList<Student>();
    	PreparedStatement pst = null;
    	ResultSet rs = null;
    	try {
    		pst = conn.prepareStatement(sql);
			rs = pst.executeQuery();
		    while (rs.next()) {
		    	Student student = new Student();
		    	student.setSno(rs.getString(1));
		    	student.setSname(rs.getString(2));
		    	student.setSage(rs.getInt(3));
		    	student.setSsex(rs.getString(4));
				list.add(student);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			try {
				rs.close();
				pst.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
		}
		return list;
    	
    }
    
    
    
    /**
     * 改数据,可以利用反射写
     * 
     */
    public static void update(Object object){
    	 Class c = object.getClass();  
         String className = c.getName();    //获得对象名
         String table = className.substring(className.lastIndexOf(".")+1,className.length());
    	
    	List var = new ArrayList();   //存储字段名
    	List values = new ArrayList();  //存储value值
    	 // 定义一个sql字符串  
        String sql = "update %s set %s where %s = %s";  
        // 得到对象的类  
        getSqlString(var, values, object);
        //设置
        String sql2="";
        for (int i = 0; i < var.size(); i++) { 
        	if(i<var.size()-1){
        		sql2 += var.get(i) + "=" + values.get(i)+",";   
        	}else{
        		sql2+=var.get(i) + "=" + values.get(i)+"";
        	}
        } 
        sql=sql.format(sql, table,sql2,var.get(0),values.get(0));
        dbCO(sql);
       
	}
    
    /**
     * 插入数据,可以利用反射写
     * 
     */
    public static void insert(Object object){
    	 Class c = object.getClass();  
         String className = c.getName();    //获得对象名
         String table = className.substring(className.lastIndexOf(".")+1,className.length());
    	
    	List var = new ArrayList();   //存储字段名
    	List values = new ArrayList();  //存储value值
    	 // 定义一个sql字符串  
        String sql = "insert into %s(";  
        // 得到对象的类  
        getSqlString(var, values, object);
        //设置
        for (int i = 0; i < var.size(); i++) {  
            if (i < var.size() - 1) {  
                sql += var.get(i) + ",";  
            } else {  
                sql += var.get(i) + ") values(";  
            }  
        }  
        for (int i = 0; i < values.size(); i++) {  
            if (i < values.size() - 1) {  
                sql += values.get(i) + ",";  
            } else {  
                sql += values.get(i) + ")";  
            }  
        }  
        sql=sql.format(sql, table);
        dbCO(sql);
       
	}
    
    
    /**
     * 删除数据,可以利用反射写
     * 
     */
    public static void delete(Object object){
    	 Class c = object.getClass();  
         String className = c.getName();    //获得对象名
         String table = className.substring(className.lastIndexOf(".")+1,className.length());
    	
    	List var = new ArrayList();   //存储字段名
    	List values = new ArrayList();  //存储value值
    	 // 定义一个sql字符串  
        String sql = "delete %s where %s";  
        // 得到对象的类  
        getSqlString(var, values, object);
        //设置
        String sql2="";
        for (int i = 0; i < var.size(); i++) { 
        	if(i<var.size()-1){
        		sql2 += var.get(i) + "=" + values.get(i)+" and ";   
        	}else{
        		sql2+=var.get(i) + "=" + values.get(i)+"";
        	}
        } 
        sql=sql.format(sql, table,sql2);
        dbCO(sql);
       
	}
    

    private static void getSqlString(List var,List values,Object object){
   	 Class c = object.getClass();  
        Method method[] = c.getMethods();  //获取所有方法
        String className = c.getName();    //获得对象名
        String table = className.substring(className.lastIndexOf(".")+1,className.length());
        for(Method m : method){ //遍历方法
        	  String mName = m.getName();   //方法名
              if (mName.startsWith("get") && !mName.startsWith("getClass")) {  
            	  try {
            		  //获取字段名
            		String fieldName = mName.substring(3, mName.length()).toLowerCase();  
                    var.add(fieldName);  
					Object value = m.invoke(object, null);//方法调用
                    if (value instanceof String) {  
                        values.add("'" + value + "'");  
                    } else {  
                    	values.add(value);  
                    }  
				} catch (IllegalAccessException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (IllegalArgumentException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (InvocationTargetException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} 
            	  
              }
        }
   }
   
   
   private static void dbCO(String sql){
   	 PreparedStatement pst = null;
		try {
			conn.setAutoCommit(false);
			pst = conn.prepareStatement(sql);
			pst.executeUpdate();
			conn.commit();
			conn.setAutoCommit(true);
		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			try {
				pst.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
   }
}
