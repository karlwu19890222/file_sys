package cn.karlwu.common.utils;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

import org.nutz.dao.entity.annotation.Column;

@SuppressWarnings("unchecked")
public class ObjectCompare<T> {
	@SuppressWarnings("rawtypes")
	public String contrastObj(Object oldBean, Object newBean) {
		
        String str="";
        T pojo1 = (T) oldBean;
        T pojo2 = (T) newBean;
        
        try {
            Class clazz = pojo1.getClass();
            Field[] fields = pojo1.getClass().getDeclaredFields();
            for (Field field : fields) {
                if("serialVersionUID".equals(field.getName())){
                    continue;
                }
                try {
                	 
                	 PropertyDescriptor pd = new PropertyDescriptor(field.getName(), clazz);
                	 Method getMethod = pd.getReadMethod();
                     Object o1 = getMethod.invoke(pojo1);
                     Object o2 = getMethod.invoke(pojo2);
                     if(o1==null || o2 == null){
                         continue;
                     }
                     if (!o1.toString().equals(o2.toString())) {
                         if(o1.equals("") || o1==null){
                        	 o1="空值";
                         }
                         if(o2.equals("") || o2==null){
                        	 o2="空值";
                         }
                         Column column  = field.getAnnotation(Column.class );//得到属性上的注解   
                         if(column.value()!=null && !column.value().equals("")){ //有注解才记录
                        	 str+="更新项目【"+column.value()+"】,“"+o1+"”更新为“"+o2+"”;";
                         }
                     } 
				} catch (Exception e) {
					continue;
				}
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        System.out.println("------------------:"+str);
        return str;
    }
}