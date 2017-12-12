package cn.karlwu.common.shiro;
import java.io.Serializable;
import java.util.List;

/**
 * @description：自定义Authentication对象，使得Subject除了携带用户的登录名外还可以携带更多信息
 * @author：karlwu
 * @date：2015/10/1 14:51
 */
public class ShiroUser implements Serializable {

    private static final long serialVersionUID = -1373760761780840081L;
    public String id;
    public String loginName;
    public String name;
    public String organizationName;
    public List<String> roleList;

    public ShiroUser(String id, String loginName, String name,String organizationName, List<String> roleList) {
        this.id = id;
        this.loginName = loginName;
        this.name = name;
        this.organizationName = organizationName;
        this.roleList = roleList;
    }

    public String getId() {
        return id;
    }
    
    public String getName() {
        return name;
    }
    public String getOrganizationName() {
		return organizationName;
	}



    /**
     * 本函数输出将作为默认的<shiro:principal/>输出.
     */
    @Override
    public String toString() {
        return loginName;
    }
}